package org.education.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserPrincipal {
    private String email;
    private String username;
    private boolean banned;
    private int socialCredit;
    private Role role;
    private String token;

    public UserPrincipal(User user){
        this.email = user.getLogin();
        this.banned = user.isBanned();
        this.role = user.getRole();
        this.socialCredit = user.getSocialCredit();
        this.token = user.getToken();
        this.username = user.getUsername();
    }
}
