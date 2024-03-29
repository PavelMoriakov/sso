package com.naya.poc.auth;

import com.okta.jwt.JoseException;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerifier;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class OktaOAuthAuthenticator implements Authenticator<String,AccessTokenPrincipal> {

    private final JwtVerifier jwtVerifier;

    public OktaOAuthAuthenticator(JwtVerifier jwtVerifier) {
        this.jwtVerifier = jwtVerifier;
    }

    @Override
    public Optional<AccessTokenPrincipal> authenticate(String credentials) throws AuthenticationException {

        try {
            Jwt jwt = jwtVerifier.decodeAccessToken(credentials);
            return Optional.of(new AccessTokenPrincipal(jwt));
        } catch (JoseException e) {
            throw new AuthenticationException(e);
        }
    }
}
