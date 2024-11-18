package com.ntg.backend.jwt;
import com.ntg.backend.service.imp.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationContext context;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // Check if the authorization header is present and starts with "Bearer"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Extract the token from the Authorization header
            username = jwtService.extractUserName(token);  // Extract the username from the token
        }

        // Proceed only if username is not null and the user is not authenticated yet
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load the user details from the username
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

            // If the token is valid, authenticate the user
            if (jwtService.validateToken(token, userDetails)) {

                // Create an authentication token with the user details
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Set the details for the authentication token
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication token in the security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // Add the authenticated user to the request attributes
                request.setAttribute("authenticatedUser", userDetails);  // Adding the user to request attributes
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}