package com.example.orderservice.orderservice.authentication;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility implements Serializable {

    private static final long serialVersionUID = 234234523523L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secretKey;

    //retrieve username from jwt token
    public String getusernameFromToken(String token) {
        return getClaimFronToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFronToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFronToken(String token, Function<Claims, T> claimsResolver) {
        System.out.println(token);
        final Claims claims = getAllClaimsFromToken(token);
        System.out.println("claims"+claims);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        System.out.println(token +"   "+ secretKey);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512,secretKey).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String userName = getusernameFromToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
