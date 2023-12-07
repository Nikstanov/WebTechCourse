package org.education.config;

import org.education.beans.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/admin").hasAnyAuthority(Role.ROLE_ADMIN.name())
                        .requestMatchers("/admin/users/*").hasAnyAuthority(Role.ROLE_ADMIN.name())
                        .requestMatchers("/review").hasAnyAuthority(Role.ROLE_ADMIN.name(), Role.ROLE_USER.name())
                        .anyRequest().permitAll())
                .csrf(Customizer.withDefaults())
                .formLogin(configurer -> {
                    configurer
                            .loginPage("/reg/signInPage")
                            .usernameParameter("email")
                            .loginProcessingUrl("/reg/signIn")
                            .defaultSuccessUrl("/", true)
                            .failureUrl("/reg/signInPage?error");
                })
                .logout(configurer -> {
                    configurer
                            .permitAll()
                            .logoutUrl("/reg/signOut")
                            .logoutSuccessUrl("/")
                            .logoutRequestMatcher(new AntPathRequestMatcher("/reg/signOut", "GET"));
                });
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
