package com.api.gamerating.security;

import com.api.gamerating.dto.UserDto;
import com.api.gamerating.entity.Role;
import com.api.gamerating.entity.User;
import com.api.gamerating.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAuthProvider {

    ///@Value("${security.jwt.token.secret-key:secret-value}")
    private String secretKey = "b";

    private final UserService userService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto user){
        Date now = new Date();
        Date valid = new Date(now.getTime() + 3_600_000);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(valid)
                .withClaim("roles", user.getRoles().toString())
                .withClaim("firstName", user.getFirstName())
                .withClaim("lastName", user.getLastName())
                .sign(algorithm);

    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = UserDto.builder()
                .username(decoded.getIssuer())
                .roles((List<Role>) decoded.getClaim("roles"))
                .firstName(decoded.getClaim("firstName").asString())
                .lastName(decoded.getClaim("lastName").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }


}
