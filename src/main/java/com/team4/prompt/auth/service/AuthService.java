package com.team4.prompt.auth.service;

import com.team4.prompt.auth.controller.dto.LoginRequest;
import com.team4.prompt.auth.controller.dto.LoginResponse;
import com.team4.prompt.auth.jwt.JwtProvider;
import com.team4.prompt.auth.model.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final String TOKEN_TYPE = "Bearer";
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword(), null);
        Authentication authentication = authenticationManager.authenticate(authToken);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String token = jwtProvider.createToken(principal);
        String role = principal.getAuthorities().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("")).getAuthority();
        return new LoginResponse(TOKEN_TYPE, token, role);
    }
}
