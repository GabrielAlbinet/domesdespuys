package fr.simplon.domesdespuys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.view.addressView;
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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(addressView.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(addressView.Line.class)
    private String line;

    @JsonView(addressView.ExtraLine.class)
    private String extraLine;

    @Column(nullable = false)
    @JsonView(addressView.City.class)
    private String city;

    @Column(nullable = false, length = 20)
    @JsonView(addressView.ZipCode.class)
    private String zipCode;

    @Column(nullable = false)
    @JsonView(addressView.Country.class)
    private String country;

    @Column(length = 30)
    @JsonView(addressView.Phone.class)
    private String phone;

    @Column(nullable = false)
    @JsonView(addressView.Firstname.class)
    private String firstname;

    @Column(nullable = false)
    @JsonView(addressView.Lastname.class)
    private String lastname;

    @Column(nullable = false, updatable = false)
    @JsonView(addressView.CreatedAt.class)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}