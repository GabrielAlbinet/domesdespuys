package fr.simplon.domesdespuys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.enums.Role;
import fr.simplon.domesdespuys.view.userView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(userView.Id.class)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(userView.Email.class)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonView(userView.Firstname.class)
    private String firstname;

    @Column(nullable = false)
    @JsonView(userView.Lastname.class)
    private String lastname;

    @Column(length = 30)
    @JsonView(userView.Phone.class)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonView(userView.Role.class)
    private Role role = Role.CUSTOMER;

    @Column(nullable = false, updatable = false)
    @JsonView(userView.CreatedAt.class)
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}