package ndgroups.mbrailway2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/**", "/reservations/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
//                       .defaultSuccessUrl("/", true)
                        .permitAll()
                );
//                .logout(logout -> logout.logoutUrl("/logout").permitAll());
//                .rememberMe(remember -> remember.key("kkeewwmm"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder()
//                .username("user")
//                .password("$2a$12$noIbLmvR/xYFG1QgFYiK0eqlLgQ3CTsfsDnqzbKb/ZPe9EXts9An2")
//                .roles("USER")
//                .build();
//        UserDetails adminDetails = User.builder()
//                .username("admin")
//                .password("$2a$12$noIbLmvR/xYFG1QgFYiK0eqlLgQ3CTsfsDnqzbKb/ZPe9EXts9An2")
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails, adminDetails);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }



}
