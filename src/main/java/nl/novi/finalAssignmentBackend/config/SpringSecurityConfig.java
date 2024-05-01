package nl.novi.finalAssignmentBackend.config;


import nl.novi.finalAssignmentBackend.Service.CustomUserDetailsService;
import nl.novi.finalAssignmentBackend.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }


    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {


        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth
//
                                .requestMatchers(HttpMethod.GET,"/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/games/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/games").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/games/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/games/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/movies/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/movies/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/movies/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/shoppinglists/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/shoppinglists/{id}").hasRole("USER")// werkt nog niet
                                .requestMatchers(HttpMethod.POST,"/shoppinglists").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/shoppinglists/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/shoppinglists/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("ADMIN","USER")
                                .requestMatchers(HttpMethod.POST, "/orders/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PUT, "/orders/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")

                                .requestMatchers("/authenticated").authenticated()
                                .requestMatchers("/authenticate").permitAll()
                                .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}