package com.zeel.expensetracker.expensetrackerbackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    // For JWT
    private static final String SECRET_KEY = "fdb2e6e469f07b6b3d18d767bba58504fcb29a541ad6f49f54ac09596877d2d4";

    // To sign token with SECRET_KEY
    private SecretKey getSignInKey() {
        // Decode the SECRET_KEY
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        // return HMAC-SHA KEY
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Don't need two generateToken
    public String generateToken(UserDetails userDetails) {
        // Passed new Hashmap as for new User it will be empty
        return generateToken(new HashMap<>(), userDetails);
    }

    // To generate JWT Token
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        // To build JWT in structured way
        return Jwts.builder()
                // to incorporate custom claims, which are key-value pairs
                // Here we are having empty as we don't want anything beside Username
                .claims(extraClaims)
                // Subject meaning one who identifies uniquely
                .subject(userDetails.getUsername())
                // When token is generated
                .issuedAt(new Date(System.currentTimeMillis()))
                // When token is expired
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                // To sign the token with SECRET_KEY
                .signWith(getSignInKey())
                // Complete creation by serializing into compact, URL safe string representation
                .compact();
    }

    // To get all claims from token
    private Claims extractAllClaims(String token) {
        // When creating token we build it now to deform it we parse it
        // parsing to deconstruct token
        // verifying with key which we used for signing
        // now as it is verified and genuine token
        // build it again
        // parseSignedClaims(token) splits token into header, payload, signature
        // getPayLoad() returns all payload (claims)
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

    // To get username from JWT
    public String extractUsername(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    // Extract expiration from token
    private Date extractExpiration(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
