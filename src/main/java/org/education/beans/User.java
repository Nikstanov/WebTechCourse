package org.education.beans;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String username;
    private String login;
    private String token;
    private String password;
    private int socialCredit = 0;
    private boolean banned = false;
    private Role role = Role.USER;
}

