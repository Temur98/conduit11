package io.realworld.angular.conduit.config.utility;

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
@Component
public class JwtUtility {
    private static final int expMs =6000*100;
    private static final String secretKeys = "MyAngularAppshshshshsrthsrthshshshstrhstrhshdjsthhjfyfukgyukfyukjd";
    private static final Key key =Keys.hmacShaKeyFor(secretKeys.getBytes(StandardCharsets.UTF_8));

    public String generate(String userName, String authorities ){
        return Jwts.builder()
                .setSubject("FOR-LOGIN")
                .setIssuer("Angular")
                .claim("userName", userName)
                .claim("authorities", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expMs))
                .signWith(key)
                .compact();
    }
    public boolean valiDate(String token){
        if(getUserName(token) != null && !isExpired(token) && getAuthorities(token).size() != 0) {
            return true;
        }
        return false;
    }

     public String getUserName(String token) {
         Claims claims = getClaim(token);
         return claims.get("username").toString();

     }
     public boolean isExpired(String token){
        Claims claims = getClaim(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));

     }
    private Claims getClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        Claims claims = getClaim(token);
        return Arrays.stream(claims.get("authorities", String.class)
                        .split(", ")).
                map(SimpleGrantedAuthority::new)
                .toList();
    }
}
