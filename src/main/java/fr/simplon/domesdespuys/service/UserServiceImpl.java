package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.UserPostDTO;
import fr.simplon.domesdespuys.dto.UserPutDTO;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.repository.UserRepository;
import fr.simplon.domesdespuys.service.interfaces.iUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public User update(String id, UserPutDTO dto) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
