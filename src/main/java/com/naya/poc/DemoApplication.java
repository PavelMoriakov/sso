package com.naya.poc;

import com.naya.poc.auth.AccessTokenPrincipal;
import com.naya.poc.auth.OktaOAuthAuthenticator;
import com.naya.poc.model.OktaOAuthConfig;
import com.naya.poc.resources.HomePageResource;
import com.naya.poc.resources.LoginWidgetConfigResource;
import com.nimbusds.oauth2.sdk.ParseException;
import com.okta.jwt.JwtHelper;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;

import java.io.IOException;

public class DemoApplication extends Application<DemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "Demo";
    }

    @Override
    public void initialize(final Bootstrap<DemoConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/","/","index.html"));
    }

    @Override
    public void run(final DemoConfiguration configuration,
                    final Environment environment) {

        environment.jersey().setUrlPattern("/api/*");

        configureOAuth(configuration, environment);

        environment.jersey().register(new HomePageResource());
        environment.jersey().register(new LoginWidgetConfigResource(configuration.oktaOAuth));
    }

    private void configureOAuth(final DemoConfiguration configuration,
                                final Environment environment) {
        try {
            OktaOAuthConfig widgetConfig = configuration.oktaOAuth;
            JwtHelper helper = new JwtHelper()
                    .setIssuerUrl(widgetConfig.issuer)
                    .setClientId(widgetConfig.clientId);

            String audience = widgetConfig.audience;
            if (StringUtils.isNotEmpty(audience)) {
                helper.setAudience(audience);
            }

            environment.jersey().register(new AuthDynamicFeature(
                    new OAuthCredentialAuthFilter.Builder<AccessTokenPrincipal>()
                            .setAuthenticator(new OktaOAuthAuthenticator(helper.build()))
                            .setPrefix("Bearer")
                            .buildAuthFilter()
            ));

            environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AccessTokenPrincipal.class));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to configure JwtVerifier", e);
        }

    }
}
