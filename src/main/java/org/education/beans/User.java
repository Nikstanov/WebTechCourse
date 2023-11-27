package org.education.beans;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * @author nstah
 * @version 0.5
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String username;
    private String login;
    private String token;
    private String password;

    @Column(name = "socialcredit")
    private int socialCredit = 0;

    @Enumerated(value = EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviewList;
}

