package com.team4.prompt.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.team4.prompt.auth.model.PrincipalDetails;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private final String secret;
    private final int expirySeconds;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expirySeconds}") int expirySeconds) {

        this.secret = secret;
        this.expirySeconds = expirySeconds;
    }

    public String createToken(PrincipalDetails principalDetails) {
        Date now = new Date();
        return JWT.create()
                .withSubject(principalDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + expirySeconds))
                .withClaim("id", principalDetails.getEmployee().getId())
                .withClaim("userId", principalDetails.getUsername())
                .sign(Algorithm.HMAC512(secret));
    }

    public DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
    }
}
