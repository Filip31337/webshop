package com.brajnovic.webshop.entity;

import com.brajnovic.webshop.model.ERole;
import com.brajnovic.webshop.model.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ROLE")
@SequenceGenerator(name = "SEQ_USER_ROLE", sequenceName = "SEQ_USER_ROLE", allocationSize = 1)
@Getter
@Setter
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ROLE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;

    public static Role convertToRole(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return Role.builder()
                .id(roleEntity.getId())
                .role(roleEntity.getRole().toString())
                .build();
    }
}
