package edu.harvard.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    private static final String secret = "harvard";
    private static final Long expiration = 24 * 60 * 60 * 1000L; // 24 hours

    public static String generateJwt(Map<String, Object> claims) {
        return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public static Claims parseJwt(String jwt) {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
