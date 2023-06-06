package edu.ap.backendspring.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {
    Dotenv dotenv = Dotenv.load();

    public String generateToken(Authentication authentication){
        String email = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + 86400000);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, dotenv.get("SECRET_KEY"))
                .compact();
    }
    public String getEmailFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey((dotenv.get("SECRET_KEY")))
                .parseClaimsJws(token)
                .getBody();
        return  claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(dotenv.get("SECRET_KEY")).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
