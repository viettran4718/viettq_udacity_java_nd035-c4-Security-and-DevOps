package com.example.demo.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);


    public static String generateJWTToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
        return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiration).signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
    }


    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    // Lấy thông tin user từ jwt
    public static String getUserFromJWT(String token) {
        if (!validateToken(token)) {
            throw new JwtException("Invalid JWT");
        }
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


}
