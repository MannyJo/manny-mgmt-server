package com.manny.mgmt.security.config;

import com.manny.mgmt.security.jwt.JwtTokenVerifier;
import com.manny.mgmt.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.manny.mgmt.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

import static com.manny.mgmt.user.model.AppUserRole.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private SecretKey secretKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(jwtProperties, secretKey, authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(jwtProperties, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/user/create").permitAll()
                .antMatchers("/api/user/**").hasAnyRole(ADMIN.name(), USER.name())
                .antMatchers("/api/storage/**").hasAnyRole(ADMIN.name(), USER.name())
                .anyRequest()
                .authenticated();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration loginConfiguration = new CorsConfiguration();
        loginConfiguration.setAllowedOrigins(Arrays.asList("*"));
        loginConfiguration.setAllowedMethods(Arrays.asList("POST"));
        loginConfiguration.setExposedHeaders(Arrays.asList("Authorization"));

        CorsConfiguration normalConfiguration = new CorsConfiguration();
        normalConfiguration.setAllowedOrigins(Arrays.asList("*"));
        normalConfiguration.setAllowedMethods(Arrays.asList("*"));
        normalConfiguration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/login", loginConfiguration);
        source.registerCorsConfiguration("/api/**", normalConfiguration);

        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
