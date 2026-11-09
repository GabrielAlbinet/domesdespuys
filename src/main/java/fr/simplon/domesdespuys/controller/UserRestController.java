package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.repository.UserRepository;
import fr.simplon.domesdespuys.view.userView;
import lombok.AllArgsConstructor;
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

    private final UserRepository userRepository;

    @GetMapping
    @JsonView(userView.UserListView.class)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(userView.UserDetailView.class)
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }
}