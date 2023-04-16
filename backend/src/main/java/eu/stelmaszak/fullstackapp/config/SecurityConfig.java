package eu.stelmaszak.fullstackapp.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Log
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    private static final String[] AUTH_WHITELIST = {
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/v3/api-docs/**",
        "/api/public/**",
        "/api/public/authenticate",
        "/actuator/*",
        "/swagger-ui/**"
    };

    private final GrantedAuthoritiesExtractor grantedAuthoritiesExtractor;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        log.info("Set the following allowed CORS origins: %s".formatted(Arrays.toString(allowedOrigins)));
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Origin", "Content-Type",
            "Accept", "Authorization"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated())
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt()
                .jwtAuthenticationConverter(getJwtAuthenticationConverter()))
            .cors(Customizer.withDefaults())
            .build();
    }

    private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesExtractor);
        return jwtAuthenticationConverter;
    }

}
