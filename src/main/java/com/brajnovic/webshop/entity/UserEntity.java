package com.brajnovic.webshop.entity;

import com.brajnovic.webshop.model.Role;
import com.brajnovic.webshop.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @Column(name = "ID", nullable = false)
    private Long id;

    private String username;

    private String password;

    private String email;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_USER_ROLE_RELATION",
        joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "USER_ROLE_ID", referencedColumnName = "ID"))
    private Set<RoleEntity> roles = new HashSet<>();

    private Boolean isEnabled() {
        return enabled;
    }

    public static User convertToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        Set<Role> rolesMapped = new HashSet<>();

        for (RoleEntity roleEntity: userEntity.getRoles()) {
            rolesMapped.add(RoleEntity.convertToRole(roleEntity));
        }

        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .fullName(userEntity.getFirstName() + " " + userEntity.getLastName())
                .password(userEntity.getPassword())
                .emailAddress(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .enabled(userEntity.isEnabled())
                .roles(rolesMapped)
                .build();
    }

}
