package com.naya.poc.model;

public class OktaOAuthConfig {
    public String baseUrl;
    public String clientId;
    public String issuer;
    public String audience;

    public OktaOAuthConfig() {
    }

    public OktaOAuthConfig(String baseUrl, String clientId, String issuer, String audience) {
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.issuer = issuer;
        this.audience = audience;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}
