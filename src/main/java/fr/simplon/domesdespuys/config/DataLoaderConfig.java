package fr.simplon.domesdespuys.config;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import fr.simplon.domesdespuys.entity.*;
import fr.simplon.domesdespuys.repository.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@AllArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private BrandRepository brandRepository;
    private AddressRepository addressRepository;
    private ProductRepository productRepository;
    private ImageRepository imageRepository;

    @Override
    public void run(String @NonNull ... args) {
        createUsers();
        createBrands();
        createAddress();
        createProducts();
        createImages();
    }

    private void createUsers() {
        if (0 == userRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            Slugify slugify = Slugify.builder().build();
            int maxUser = 75;

            for (int i = 0; i < maxUser; i++) {
                User user = new User();
                user.setFirstname(faker.name().firstName());
                user.setLastname(faker.name().lastName());
                String email = slugify.slugify(user.getFirstname())
                        + "." + slugify.slugify(user.getLastname())
                        + "." + i
                        + "@" + slugify.slugify(faker.internet().domainName()) + ".fr";
                user.setEmail(email);
                user.setPassword(faker.internet().password(12, 20));
                userRepository.save(user);
            }
            userRepository.flush();
        }
    }

    private void createBrands() {
        if (0 == brandRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            Slugify slugify = Slugify.builder().build();
            Set<String> slugs = new HashSet<>();
            int maxBrand = 50;

            while (slugs.size() < maxBrand) {
                String label = faker.company().name();
                String slug = slugify.slugify(label);

                if (slugs.add(slug)) {
                    Brand brand = new Brand();
                    brand.setLabel(label);
                    brand.setSlug(slug);
                    brand.setLogo("https://picsum.photos/seed/" + slug + "/200/200");
                    brandRepository.save(brand);
                }
            }
            brandRepository.flush();
        }
    }

    private void createAddress() {
        if (0 == addressRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            List<User> users = userRepository.findAll();
            int maxAddress = 130;

            for (int i = 0; i < maxAddress; i++) {
                User user = users.get(i % users.size());

                Address address = new Address();
                address.setUser(user);
                address.setLine((1 + faker.random().nextInt(120)) + " " + faker.address().streetName());
                address.setCity(faker.address().cityName());
                address.setZipCode(faker.address().zipCode());
                address.setCountry("France");
                address.setFirstname(user.getFirstname());
                address.setLastname(user.getLastname());
                addressRepository.save(address);
            }
            addressRepository.flush();
        }
    }

    private void createProducts() {
        if (0 == productRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            Slugify slugify = Slugify.builder().build();
            List<Brand> brands = brandRepository.findAll();
            Set<String> slugs = new HashSet<>();
            int maxProduct = 150;

            while (slugs.size() < maxProduct) {
                String label = faker.commerce().productName();
                String slug = slugify.slugify(label);

                if (slugs.add(slug)) {
                    Product product = new Product();
                    product.setLabel(label);
                    product.setSlug(slug);
                    product.setDescription(faker.lorem().paragraph(3));
                    product.setPrice(BigDecimal.valueOf(500 + faker.random().nextInt(49500), 2));
                    product.setBrand(brands.get(faker.random().nextInt(brands.size())));
                    productRepository.save(product);
                }
            }
            productRepository.flush();
        }
    }

    private void createImages() {
        if (0 == imageRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            List<Product> products = productRepository.findAll();

            for (Product product : products) {
                int maxImage = 1 + faker.random().nextInt(3);

                for (int i = 0; i < maxImage; i++) {
                    Image image = new Image();
                    image.setProduct(product);
                    image.setPath("https://picsum.photos/seed/" + product.getSlug() + "-" + i + "/800/600");
                    image.setExtension("jpg");
                    image.setIsDefault(0 == i);
                    imageRepository.save(image);
                }
            }
            imageRepository.flush();
        }
    }
}