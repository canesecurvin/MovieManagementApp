package com.example.x3.MovieManagementApp.config.JwtConfig;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${sec.jwt-secret}")
    private String jwtSecret;
    @Value("${sec.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    private Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));


    public String generateToken(Authentication authentication) {
        String jwt = "";
        if (authentication != null){
            String displayName = authentication.getName();
            Date currentDate = new Date();
            Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);
            jwt = Jwts.builder()
                    .setIssuer("Movie App Team")
                    .setSubject(displayName)
                    //.claim("displayName", displayName)
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(key)
                    .compact();
        }
        return jwt;
    }


    public boolean isTokenValid(String jwt) throws Exception {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            return true;
        }catch (MalformedJwtException ex) {
            throw new Exception("Invalid JWT");
        }  catch (ExpiredJwtException ex) {
            throw new Exception("Expired JWT");
        } catch (SignatureException ex){
            throw new Exception("Invalid JWT signature");
        }catch (UnsupportedJwtException ex) {
            throw new Exception("Unsupported JWT");
        } catch (IllegalArgumentException ex) {
            throw new Exception("JWT claims string is empty.");
        }
    }


    public String getDisplayNameFromJWT(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }

}
