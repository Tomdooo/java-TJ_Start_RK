package cz.uhk.tj_start_rk.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import cz.uhk.tj_start_rk.service.JpaUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collection;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
// https://www.youtube.com/watch?v=KYNR5js2cXE
// https://www.youtube.com/watch?v=awcCiqBO36E
public class SecurityConfiguration {
    private final RsaKeyProperties rsaKeyProperties;
    private final JpaUserDetailService jpaUserDetailService;

    public SecurityConfiguration(RsaKeyProperties rsaKeyProperties, JpaUserDetailService jpaUserDetailService) {
        this.rsaKeyProperties = rsaKeyProperties;
        this.jpaUserDetailService = jpaUserDetailService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(
                        auth -> auth
                                    .regexMatchers("/token").permitAll()
                                    .mvcMatchers("/token").permitAll()
                                    .antMatchers("/token").permitAll()
                                    .anyRequest().authenticated()

                )
//                .addFilterBefore(new CookieAuthFilter(jwtDecoder()), BasicAuthenticationFilter.class)
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .oauth2ResourceServer(oauth2 -> oauth2
                                                    .jwt()
                                                    .jwtAuthenticationConverter(customJwtAuthenticationConverter()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   // Disable session (not needed here for this REST API)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .userDetailsService(jpaUserDetailService)
                .formLogin().and()
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    // CORS configure
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:5173").allowCredentials(true);
            }
        };
    }

    // JWT encoder/decoder
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.publicKey()).build();    // decode using RSA public key
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeyProperties.publicKey()).privateKey(rsaKeyProperties.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    // Password encoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authorities converters from JWT Bearer token for @PreAuthorize
    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("");
        return converter;
    }

    @Bean
    public JwtAuthenticationConverter customJwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return converter;
    }
}
