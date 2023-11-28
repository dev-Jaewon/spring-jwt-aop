package com.devJaewon.springSecurity.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
    private String SECRET_KEY = "REQUIRE PRIVATE SECURITY KEY!!!!";
    private String token = "";

    public JwtProvider() {
    }

    public JwtProvider(String token) {
        this.token = token;
    }

    public String createAccessToken(String user) {
        return createToken(user, System.currentTimeMillis() + 1000 * 60 * 10);
    }

    public String createRefreshToken(String user) {
        return createToken(user, System.currentTimeMillis() + 1000 * 60 * 120);
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String userId, long exprieTime) {
        return Jwts.builder()
                // .claims(claims)
                .subject(userId)
                .issuer("dev-jaewon")
                .expiration(new Date(exprieTime))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String getUserName() {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(this.token)
                .getPayload()
                .getSubject();
    }
}
