package com.example.springbootsec31.config;

import com.example.springbootsec31.service.JWTService;
import com.example.springbootsec31.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        final String jwt;

        final String userEmail;

        // there is one with org.springframework.util.StringUtils seems deprecated
        //and the tutorial doesn't use it
        //here for first StringUtils we are using import from micrometer

        if (StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            System.out.println("\033[0;31m" + "authHeader is empty or doesn't start with Bearer" + "\033[0m");
            return;
        }

        jwt = authHeader.substring(7);
        System.out.println("\033[0;32m" + "we got Bearer " + jwt + "\033[0m");
        userEmail = jwtService.extractUserName(jwt);
        System.out.println("\033[0;32m" + "we got userEmail : " + userEmail +  "\033[0m");

        if (!StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            System.out.println("\033[0;32m" + "we got userDetails : " + userDetails +  "\033[0m");

            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }

        }

        filterChain.doFilter(request, response);

    }
}
