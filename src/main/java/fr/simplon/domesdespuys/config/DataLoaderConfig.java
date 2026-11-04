package fr.simplon.domesdespuys.config;

import com.github.javafaker.Faker;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
@AllArgsConstructor

public class DataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;

    @Override
    public void run(String @NonNull ... args) {
        createUsers();
    }

    private void createUsers() {
        if (0 == userRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            int maxUser = 20;

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

    private boolean hasProbability(int expected, int max) {
        Random random = new Random();
        return random.nextInt(max) <= expected;
    }
}