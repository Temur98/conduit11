package io.realworld.angular.conduit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtService {
    private final static String secretKey = "myBankingAppForSimpleBankServices";
    private final static Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username, String authority){
        String uuid = UUID.randomUUID().toString();
        String token =  Jwts.builder()
                .setSubject("FOR-LOGIN")
                .setIssuer("Medium")
                .claim("username",username)
                .claim("authorities",authority)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(key)
                .compact();
        System.out.println(token);
        return token;
    }
    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isExpired(String token){
        return getClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }
    public String getUsername(String token){
        return getClaims(token).get("username").toString();
    }
    public boolean isValidToken(String token){
        return token != null && !isExpired(token);
    }
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        Claims claims = getClaims(token);
        return Arrays.stream(claims.get("authorities", String.class)
                        .split(", ")).
                map(SimpleGrantedAuthority::new)
                .toList();
    }
}
