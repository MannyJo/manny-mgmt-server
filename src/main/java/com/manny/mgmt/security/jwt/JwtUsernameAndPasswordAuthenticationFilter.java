package com.manny.mgmt.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manny.mgmt.security.config.JwtProperties;
import com.manny.mgmt.user.model.User;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;
    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(JwtProperties jwtProperties,
                                                      SecretKey secretKey,
                                                      AuthenticationManager authenticationManager) {
        this.jwtProperties = jwtProperties;
        this.secretKey = secretKey;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);

            return authenticate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtProperties.getTokenExpirationDays())))
                .signWith(secretKey)
                .compact();

        User authenticatedUser = (User) authResult.getPrincipal();

        response.addHeader(jwtProperties.getAuthorizationHeader(), jwtProperties.getTokenPrefix() + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", authenticatedUser.getId());
            jsonObject.put("roleId", authenticatedUser.getRole().getId());

            out.println(jsonObject.toString());
        } finally {
            out.close();
            out.flush();
        }

    }
}
