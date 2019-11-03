package com.naya.poc.auth;

import com.okta.jwt.Jwt;

import javax.security.auth.Subject;
import java.security.Principal;

public class AccessTokenPrincipal implements Principal {

    private final Jwt accessToken;

    public AccessTokenPrincipal(Jwt accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getName() {
        return (String) accessToken.getClaims().get("sub");
    }

}
