package dev.hectorgallego.springbootrestapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import dev.hectorgallego.springbootrestapi.service.User.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final MyUserDetailsService myUserDetailsService;
    private final RsaKeyProperties rsaKeys;
    public SecurityConfig(MyUserDetailsService myUserDetailsService, RsaKeyProperties rsaKeys) {
        this.myUserDetailsService = myUserDetailsService;
        this.rsaKeys = rsaKeys;
    }


    @Bean
    public AuthenticationManager authManager(MyUserDetailsService myUserDetailsService){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return new ProviderManager(authProvider);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
            .csrf(csrf -> csrf
                .disable()
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/login", "/api/register","/api/verification/**").permitAll()
                .requestMatchers("/api/users/**", "/api/admin","/api/roles/**").hasAnyAuthority("SCOPE_ADMIN")
                .requestMatchers("/api/categories/**").hasAnyAuthority("SCOPE_ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/products/**").hasAuthority("SCOPE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/products/**").hasAnyAuthority("SCOPE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAnyAuthority("SCOPE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER")
                .requestMatchers("/api/useradmin").hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER")
                .anyRequest().authenticated()
            )
            .userDetailsService(myUserDetailsService)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
            
        return httpSecurity.build();

    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(rsaKeys.PublicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeys.PublicKey()).build();
    }
    
    
}
