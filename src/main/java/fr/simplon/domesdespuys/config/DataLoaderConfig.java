package fr.simplon.domesdespuys.config;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import fr.simplon.domesdespuys.entity.*;
import fr.simplon.domesdespuys.enums.OrderStatus;
import fr.simplon.domesdespuys.repository.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
@AllArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {

    private static final int PERIOD_DAYS = 365;
    private static final int MAX_ITEMS_PER_ORDER = 4;
    private static final int MAX_QUANTITY = 5;
    private static final int PROBA_CANCELLED = 5;

    private UserRepository userRepository;
    private BrandRepository brandRepository;
    private AddressRepository addressRepository;
    private ProductRepository productRepository;
    private ImageRepository imageRepository;
    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;

    @Override
    public void run(String @NonNull ... args) {
        createUsers();
        createBrands();
        createAddress();
        createProducts();
        createImages();
        createOrders();
        createOrderProducts();
    }

    private void createUsers() {
        if (0 == userRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            Slugify slugify = Slugify.builder().build();
            int maxUser = 80;

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
                user.setCreatedAt(randomDate(LocalDateTime.now().minusDays(PERIOD_DAYS)));
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
            int maxBrand = 55;

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
                address.setCreatedAt(randomDate(user.getCreatedAt()));
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
                    product.setCreatedAt(randomDate(LocalDateTime.now().minusDays(PERIOD_DAYS)));
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

    private void createOrders() {
        if (0 == orderRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            List<Address> addresses = addressRepository.findAll();
            int year = Year.now().getValue();
            int maxOrder = 150;

            for (int i = 0; i < maxOrder; i++) {
                Address address = addresses.get(faker.random().nextInt(addresses.size()));
                LocalDateTime createdAt = randomDate(address.getCreatedAt());

                Order order = new Order();
                order.setNumber(String.format("CMD-%d-%06d", year, i + 1));
                order.setStatus(randomStatus(createdAt, faker));
                order.setAddress(address);
                order.setUser(address.getUser());
                order.setCreatedAt(createdAt);
                orderRepository.save(order);
            }
            orderRepository.flush();
        }
    }

    private void createOrderProducts() {
        if (0 == orderProductRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            List<Order> orders = orderRepository.findAll();
            List<Product> products = productRepository.findAll();

            for (Order order : orders) {
                int maxItems = 1 + faker.random().nextInt(MAX_ITEMS_PER_ORDER);
                int start = faker.random().nextInt(products.size());

                for (int i = 0; i < maxItems; i++) {
                    Product product = products.get((start + i) % products.size());

                    OrderProduct line = new OrderProduct();
                    line.setOrder(order);
                    line.setProduct(product);
                    line.setQuantity(1 + faker.random().nextInt(MAX_QUANTITY));
                    line.setPrice(product.getPrice());
                    orderProductRepository.save(line);
                }
            }
            orderProductRepository.flush();
        }
    }

    private OrderStatus randomStatus(LocalDateTime createdAt, Faker faker) {
        if (0 == faker.random().nextInt(PROBA_CANCELLED)) {
            return OrderStatus.CANCELLED;
        }

        double days = Duration.between(createdAt, LocalDateTime.now()).toMinutes() / 1440.0;
        List<OrderStatus> statuses = new ArrayList<>();

        if (days <= 1) {
            statuses.add(OrderStatus.PENDING);
        }
        if (days <= 2) {
            statuses.add(OrderStatus.PAID);
        }
        if (days >= 1 && days <= 14) {
            statuses.add(OrderStatus.SHIPPED);
        }
        if (days >= 3) {
            statuses.add(OrderStatus.DELIVERED);
        }

        return statuses.get(faker.random().nextInt(statuses.size()));
    }

    private LocalDateTime randomDate(LocalDateTime start) {
        long seconds = Duration.between(start, LocalDateTime.now()).getSeconds();
        return start.plusSeconds(ThreadLocalRandom.current().nextLong(Math.max(1, seconds)));
    }
}