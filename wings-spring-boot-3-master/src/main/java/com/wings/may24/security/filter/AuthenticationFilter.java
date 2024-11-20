package com.wings.may24.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wings.may24.entity.UserPrincipal;
import com.wings.may24.model.request.UserDetailsModel;
import com.wings.may24.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            UserDetailsModel userDetailsModel = new ObjectMapper().readValue(request.getInputStream(), UserDetailsModel.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetailsModel.getUserId(),
                            userDetailsModel.getPassword()
                    )
            );

        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userId = ((UserPrincipal)authResult.getPrincipal()).getUsername();
        response.addHeader("Authorization", jwtService.generateToken(userId));
    }
}
