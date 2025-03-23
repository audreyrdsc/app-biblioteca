package com.unifap.biblioteca.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import com.unifap.biblioteca.services.AppUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    //Security Filter Chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        http
                .requestCache(
                        cache -> {
                            cache.requestCache(requestCache);
                        }
                )
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/vendors/**", "/js/**", "/images/**", "/login").permitAll();
                            auth.anyRequest().authenticated();
                        }
                )
                .formLogin(
                        form -> {
                            form.loginPage("/login");
                            form.defaultSuccessUrl("/", true);
                            form.usernameParameter("username");
                            form.passwordParameter("password");
                            form.failureUrl("/login?error=true");
                        }
                )
                .logout(
                        logout -> {
                            logout.logoutSuccessUrl("/login");
                            logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                        }
                )
                .sessionManagement(
                        session -> {
                            session.maximumSessions(1);
                            session.invalidSessionUrl("/login");
                        }
                )
                .authenticationProvider(provider());
        return http.build();
    }

    //Authenticator provider
    public DaoAuthenticationProvider provider () {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //Password encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
