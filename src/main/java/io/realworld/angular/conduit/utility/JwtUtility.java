package io.realworld.angular.conduit.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtUtility {
    private static final int expireInMs = 6000 * 1000;
    private static final String secretKey = "myAngularMediumConduitForSimpleBankServices";
    private static final Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    public String generate(String username, String authorities) {
        return Jwts.builder()
                .setSubject("FOR-LOGIN")
                .setIssuer("Medium")
                .claim("username", username)
                .claim("authorities", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireInMs))
                .signWith(key)
                .compact();
    }


    public boolean validate(String token) {
        return getUsername(token) != null && !isExpired(token) && getAuthorities(token).size() != 0;
    }

    public String getUsername(String token) {
        return getClaims(token).get("username").toString();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        return Arrays.stream(getClaims(token)
                .get("authorities", String.class)
                .split(", "))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
