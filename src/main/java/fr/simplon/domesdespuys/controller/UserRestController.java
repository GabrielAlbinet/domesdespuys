package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.repository.UserRepository;
import fr.simplon.domesdespuys.service.interfaces.iUser;
import fr.simplon.domesdespuys.view.userView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private final iUser userService;

    @GetMapping(path = "/{id}")
    @JsonView(userView.UserDetailView.class)
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getById(id);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }
}