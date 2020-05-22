package com.manny.mgmt.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationDays;

    public JwtProperties() {}

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationDays() {
        return tokenExpirationDays;
    }

    public void setTokenExpirationDays(Integer tokenExpirationDays) {
        this.tokenExpirationDays = tokenExpirationDays;
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
