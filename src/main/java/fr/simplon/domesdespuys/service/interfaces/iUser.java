package fr.simplon.domesdespuys.service.interfaces;

import fr.simplon.domesdespuys.dto.UserPostDTO;
import fr.simplon.domesdespuys.dto.UserPutDTO;
import fr.simplon.domesdespuys.entity.User;

public interface iUser extends
        iCreate<User, UserPostDTO>,
        iUpdate<User, UserPutDTO>,
        iGetItem<User>,
        iDelete
{
}