package com.nikesh.todo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpirationMilliseconds;

    // Create generate Token utility method
    public String generateToken(Authentication authentication){

        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + jwtExpirationMilliseconds);


        return Jwts.builder()
                .subject(userName)
                .issuedAt(currentDate)
                .expiration(expiryDate)
                .signWith(getSecretKey())
                .compact();
    }

    private @NonNull Key getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Get username from token
    public String getUsernameFromToken(String token){

        return Jwts.parser().
                verifyWith((SecretKey) getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    //validate token

    public boolean validateToken(String token){

        Jwts.parser()
                .verifyWith((SecretKey) getSecretKey())
                .build()
                .parse(token);

        return true;
    }
}
