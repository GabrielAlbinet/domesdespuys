package fr.simplon.domesdespuys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.dto.UserPostDTO;
import fr.simplon.domesdespuys.dto.UserPutDTO;
import fr.simplon.domesdespuys.entity.User;
import fr.simplon.domesdespuys.repository.UserRepository;
import fr.simplon.domesdespuys.service.interfaces.iUser;
import fr.simplon.domesdespuys.view.userView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @JsonView(userView.UserDetailView.class)
    public ResponseEntity<User> createUser(@Valid @RequestBody UserPostDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    @PutMapping(path = "/{id}")
    @JsonView(userView.UserDetailView.class)
    public ResponseEntity<User> updateUser(@PathVariable String id, @Valid @RequestBody UserPutDTO dto) {
        User user = userService.update(id, dto);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        return userService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}