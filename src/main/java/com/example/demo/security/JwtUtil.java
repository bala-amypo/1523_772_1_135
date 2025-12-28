package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey;
    private int expirationMinutes;

    
    public JwtUtil() {
        this.secretKey = "mysecretkeymysecretkeymysecretkey";
        this.expirationMinutes = 60;
    }

    
    public JwtUtil(String secretKey, int expirationMinutes) {
        this.secretKey = secretKey;
        this.expirationMinutes = expirationMinutes;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    
    public String generateToken(Long userId, String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + expirationMinutes * 60L * 1000)
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    
    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    
    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


// // package com.example.demo.security;

// // import io.jsonwebtoken.Claims;
// // import io.jsonwebtoken.Jwts;
// // import io.jsonwebtoken.SignatureAlgorithm;

// // import java.util.Date;

// // public class JwtUtil {

// //     private final String secret;
// //     private final long validity;

// //     public JwtUtil(String secret, long validity) {
// //         this.secret = secret;
// //         this.validity = validity;
// //     }

// //     public String generateToken(Long userId, String email, String role) {
// //         return Jwts.builder()
// //                 .claim("userId", userId)
// //                 .claim("role", role)
// //                 .setSubject(email)
// //                 .setIssuedAt(new Date())
// //                 .setExpiration(new Date(System.currentTimeMillis() + validity))
// //                 .signWith(SignatureAlgorithm.HS256, secret)
// //                 .compact();
// //     }

// //     public boolean validateToken(String token) {
// //         try {
// //             getClaims(token);
// //             return true;
// //         } catch (Exception e) {
// //             return false;
// //         }
// //     }

// //     private Claims getClaims(String token) {
// //         return Jwts.parser()
// //                 .setSigningKey(secret)
// //                 .parseClaimsJws(token)
// //                 .getBody();
// //     }

// //     public String getUsernameFromToken(String token) {
// //         return getClaims(token).getSubject();
// //     }

// //     public String getRoleFromToken(String token) {
// //         return getClaims(token).get("role", String.class);
// //     }

// //     public Long getUserIdFromToken(String token) {
// //         return getClaims(token).get("userId", Long.class);
// //     }
// // }


// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// import java.util.Date;

// public class JwtUtil {

//     private final String secret;
//     private final long validity;

//     public JwtUtil(String secret, long validity) {
//         this.secret = secret;
//         this.validity = validity;
//     }

//     public String generateToken(Long userId, String email, String role) {
//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("userId", userId)
//                 .claim("role", role)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + validity))
//                 .signWith(SignatureAlgorithm.HS256, secret)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             getClaims(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     private Claims getClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(secret)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public String getUsernameFromToken(String token) {
//         return getClaims(token).getSubject();
//     }

//     public String getRoleFromToken(String token) {
//         return getClaims(token).get("role", String.class);
//     }

//     public Long getUserIdFromToken(String token) {
//         return getClaims(token).get("userId", Long.class);
//     }
// }

