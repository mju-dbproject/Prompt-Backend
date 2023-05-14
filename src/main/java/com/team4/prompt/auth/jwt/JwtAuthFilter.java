package com.team4.prompt.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.team4.prompt.auth.model.PrincipalDetails;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            String token = getToken(request);
            if(token != null) {
                DecodedJWT verify = jwtProvider.verify(token);
                String userId = verify.getClaim("userId").toString();
                User user = userService.findByUserId(userId);
                PrincipalDetails principalDetails = new PrincipalDetails(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken != null){
            return authorizationToken.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}
