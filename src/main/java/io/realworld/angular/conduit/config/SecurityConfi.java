package io.realworld.angular.conduit.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Service
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfi {
    private final JwtValidatorFilter jwtValidatorFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity h) throws Exception{
    h.cors(
            cors -> cors.configurationSource(request -> {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("http://localhost:4200");
                configuration.setAllowedMethods(List.of("*"));
                configuration.setAllowedHeaders(List.of("*"));
                configuration.setMaxAge(3600L);
                configuration.setExposedHeaders(List.of("Authorization"));
                configuration.setAllowCredentials(true);
                return configuration;
            })
    );

        h.authorizeHttpRequests(
                request -> request.requestMatchers("/profiles/**","/user")
                        .hasAuthority("user")
                        .anyRequest().permitAll()
        );

        h.addFilterBefore(jwtValidatorFilter, UsernamePasswordAuthenticationFilter.class);

        h.csrf(AbstractHttpConfigurer::disable);

        h.httpBasic(withDefaults());
        return h.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
