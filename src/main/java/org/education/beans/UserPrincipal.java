package org.education.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author nstah
 * @version 0.5
 */
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserPrincipal {
    private String email;
    private String username;
    private int socialCredit;
    private Role role;
    private String token;

    public UserPrincipal(User user){
        this.email = user.getLogin();
        this.role = user.getRole();
        this.socialCredit = user.getSocialCredit();
        this.token = user.getToken();
        this.username = user.getUsername();
    }
}
