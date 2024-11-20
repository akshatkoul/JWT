package com.wings.may24.security.filter;

import com.wings.may24.entity.UserEntity;
import com.wings.may24.entity.UserPrincipal;
import com.wings.may24.repository.UserRepository;
import com.wings.may24.security.JwtService;
import com.wings.may24.security.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private final UserRepository userRepository;

    public AuthorizationFilter( JwtService jwtService,
                                UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            String header = request.getHeader(SecurityConstants.AUTH_HEADER);

            if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)){
                chain.doFilter(request, response);
                return;
            }

            String token = header.replace(SecurityConstants.TOKEN_PREFIX, "");
            UserEntity userEntity = userRepository.findByUserId(jwtService.extractUserName(token));
            boolean isUserAuthenticated = jwtService.validateToken(token, userEntity);

            if(isUserAuthenticated){
                UserPrincipal user = new UserPrincipal(userEntity);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        user.getAuthorities()
                );
                // This sets the granted authorities / roles for filter chain
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                chain.doFilter(request, response);
            }

        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

}
