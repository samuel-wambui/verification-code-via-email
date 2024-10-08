package HotelManagement.security;

import HotelManagement.jwt.JwtFilter;
import HotelManagement.jwt.JwtService;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    private final JwtFilter jwtFilter;  // Injecting the JwtFilter


    private final DetailsService detailsService;
    private final CustomUserDetailsPasswordService userDetailsPasswordService;

    public SecurityConfig(JwtFilter jwtFilter, DetailsService detailsService, CustomUserDetailsPasswordService userDetailsPasswordService) {
        this.jwtFilter = jwtFilter;  // Assign the JwtFilter
        this.detailsService = detailsService;
        this.userDetailsPasswordService = userDetailsPasswordService;
    }

    // SecurityFilterChain configuration

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/verification/send-email",
                                "/auth/register",
                                "/api/auth/**",
                                "/api/users/**",
                                "/verification/**",
                                "/user",
                                "/api/roles/**",

                                "/api/stock/**",
                                "/api/multiplier/**",

                                "/api/v1/tables/**",
                                "/api/v1/messages/**",
                                "/api/v1/orders/**",
                                "/api/v1/meals/**",
                                "/api/payments-methods/**",
                                "/api/bookings/**",
                                "/api/rooms/**"


                        ).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore((Filter) jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(detailsService);
        authenticationProvider.setUserDetailsPasswordService(userDetailsPasswordService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);  // Strength of 12 for BCrypt
    }
}
