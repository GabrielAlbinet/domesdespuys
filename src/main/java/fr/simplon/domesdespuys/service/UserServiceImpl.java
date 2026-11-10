package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.UserPostDTO;
import fr.simplon.domesdespuys.dto.UserPutDTO;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.service.interfaces.iUser;

public class UserServiceImpl implements iUser {
    @Override
    public User create(UserPostDTO dto) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public User update(String id, UserPutDTO dto) {
        return null;
    }
}
