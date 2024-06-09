package com.example.topicproject.infrastructure.filter;

import com.example.topicproject.domain.entities.User;
import com.example.topicproject.domain.repository.UserRepository;
import com.example.topicproject.domain.service.JwtService;
import com.example.topicproject.exceptions.CustomExceptions.UserNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authorizationHeader = request.getHeader("Authorization");

        var isRouteToCreateUser =
                request.getRequestURL().toString().contains("/users/register") && request.getMethod().equals("POST");
        var isRouteToAuth = request.getRequestURL().toString().contains("/auth");


        if(isRouteToCreateUser || isRouteToAuth){
            filterChain.doFilter(request, response);
            return;
        }

        if (!validateAuthorizationHeader(request, response)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid Authorization Header");
            return;
        }

        String token = getToken(request);

        String subject = jwtService.validToken(token);

        if (!validateSubject(subject, response)) {
            return;
        }

        User user = getUserFromRepository(subject, response);

        setAuthenticationContext(request, user);

        filterChain.doFilter(request, response);
    }

    private String getToken(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }

    private boolean validateAuthorizationHeader(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    private String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader.replace("Bearer ", "");
    }

    private boolean validateSubject(String subject, HttpServletResponse response) {
        if (subject == null || !userRepository.existsById(subject)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    private User getUserFromRepository(String subject, HttpServletResponse response) {
        return userRepository.findById(subject).orElseThrow(() -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new UserNotFoundException("User not found");
        });
    }

    private void setAuthenticationContext(HttpServletRequest request, User user) {
        request.setAttribute("id", user.getId());
        var authorization = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authorization);
    }
}
