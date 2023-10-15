package org.education.beans;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;
    private String login;
    private String password;

    private int socialCredit;

    private boolean status;
    private boolean banned;

    private Role role;
}

