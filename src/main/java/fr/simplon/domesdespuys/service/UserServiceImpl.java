package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.UserPostDTO;
import fr.simplon.domesdespuys.dto.UserPutDTO;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.repository.UserRepository;
import fr.simplon.domesdespuys.service.interfaces.iUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements iUser {

    private UserRepository userRepository;

    @Override
    public User getById(String id) {
        try {
            return userRepository
                    .findById(Long.parseLong(id))
                    .orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public User create(UserPostDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(String id, UserPutDTO dto) {
        User user = getById(id);
        if (user != null) {
            user.setFirstname(dto.getFirstname());
            user.setLastname(dto.getLastname());
            user.setPhone(dto.getPhone());
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    @Override
    public boolean delete(String id) {
        User user = getById(id);
        if (user == null) {
            return false;
        }
        user.setEmail("anonyme-" + user.getId() + "@anonyme.fr");
        user.setFirstname("Anonyme");
        user.setLastname("Anonyme");
        user.setPhone(null);
        user.setPassword(UUID.randomUUID().toString());
        userRepository.saveAndFlush(user);
        return true;
    }
}
