package com.example.CoronaApi.Cassandra.Login;

import com.example.CoronaApi.model.request.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import io.jsonwebtoken.Jwts;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final long JWT_EXPIRATION_TIME = 3600;
    private final AuthenticationManager authenticationManager;

        private final String jwtSecret;

        public JWTAuthenticationFilter(AuthenticationManager authenticationManager, String jwtSecret) {
            this.authenticationManager = authenticationManager;
            this.jwtSecret = jwtSecret;
            setFilterProcessesUrl("/api/auth/login");
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                throws AuthenticationException {
            try {
                UserLogin loginRequest = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);

                return authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword(),
                                new ArrayList<>()
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                FilterChain chain, Authentication authResult) throws IOException, ServletException {
            String username = ((User) authResult.getPrincipal()).getUsername();
            String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
            response.addHeader("Authorization", "Bearer " + token);
        }
    }