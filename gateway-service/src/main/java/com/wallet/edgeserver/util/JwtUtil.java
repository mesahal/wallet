package com.wallet.edgeserver.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    public void validateToken(String token, String jwtSecret) {
        if(isTokenExpired(token,jwtSecret)) {
            throw new ExpiredJwtException(null,null,"Token is expired");
        }
    }

    private boolean isTokenExpired(String token, String jwtSecret) {
        try {
            return extractExpiration(token, jwtSecret).before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private Date extractExpiration(String token, String jwtSecret) {
        return extractClaim(token, jwtSecret, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, String jwtSecret, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, jwtSecret);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, String jwtSecret) {
        return Jwts.parser()
                .setSigningKey(getSignInKey(jwtSecret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(String jwtSecret) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaimByKey(String token, String jwtSecret, String key, Class<T> classType) {
        return extractClaim(token,jwtSecret,claim ->
                claim.get(key, classType));
    }
}
