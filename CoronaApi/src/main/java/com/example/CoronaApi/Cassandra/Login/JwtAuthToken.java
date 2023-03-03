package com.example.CoronaApi.Cassandra.Login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthToken extends UsernamePasswordAuthenticationToken {

    private String token;

    public JwtAuthToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
