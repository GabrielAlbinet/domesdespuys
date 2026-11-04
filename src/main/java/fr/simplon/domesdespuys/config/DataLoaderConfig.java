package fr.simplon.domesdespuys.config;

import com.github.javafaker.Faker;
import fr.simplon.domesdespuys.entity.Address;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.repository.AddressRepository;
import fr.simplon.domesdespuys.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
@AllArgsConstructor

public class DataLoaderConfig implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private UserRepository userRepository;

    @Override
    public void run(String @NonNull ... args) {
        createUsers();
        createAddress();
    }

    private void createUsers() {
        if (0 == userRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            int maxUser = 50;

            for (int i = 0; i < maxUser; i++) {
                User user = new User();
                user.setFirstname(faker.name().firstName());
                user.setLastname(faker.name().lastName());
                String email = user.getLastname()
                        + "." + user.getFirstname()
                        + "@" + faker.internet().domainName() + ".fr";
                user.setEmail(email);
                user.setPassword(faker.internet().password(12, 20));
                userRepository.save(user);
            }
            userRepository.flush();
        }
    }

    private void createAddress() {
        if (0 == addressRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            List<User> users = userRepository.findAll();
            int maxAddress = 50;

            for (int i = 0; i < maxAddress; i++) {
                User user = users.get(i % users.size());

                Address address = new Address();
                address.setUser(user);
                address.setLine(faker.address().streetAddress());
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

    private boolean hasProbability(int expected, int max) {
        Random random = new Random();
        return random.nextInt(max) <= expected;
    }
}