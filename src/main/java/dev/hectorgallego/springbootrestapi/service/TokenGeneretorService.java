package dev.hectorgallego.springbootrestapi.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;



@Service
public class TokenGeneretorService {
    
    private final JwtEncoder tokenEncoder;
    public TokenGeneretorService(JwtEncoder tokenEncoder) {
        this.tokenEncoder = tokenEncoder;
    }

    public String GenerateToken(Authentication authentication){

        Instant now = Instant.now();
        String scope = authentication
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

            JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuer("self")
                .expiresAt(now.plus(1,ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope",scope)
                .build();

            return this.tokenEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }



}
