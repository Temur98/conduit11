package io.realworld.angular.conduit.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Service
public class JWTUtility {
    private static final Integer EXPIRE_IN_MS = 3600 * 1000;
    private static final String SECRET_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));


    public String generate(String username, String authorities) {
        return Jwts.builder()
                .setSubject("FOR-LOGIN")
                .setIssuer("Medium")
                .claim("usernmae",username)
                .claim("jwt-authorization", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_IN_MS))
                .signWith(KEY)
                .compact();
    }

    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isExpired(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token){
        Claims claims = getClaims(token);
        return Arrays.stream(claims.get("jwt-authorization", String.class)
                .split(", "))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    public boolean validate(String token) {

        return getUsername(token) != null &&
                !isExpired(token)
                && getAuthorities(token).size() > 0;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.get("username", String.class);
    }

}
