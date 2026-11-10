package fr.simplon.domesdespuys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDTO {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}