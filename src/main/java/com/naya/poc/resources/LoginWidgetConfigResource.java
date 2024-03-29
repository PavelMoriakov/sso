package com.naya.poc.resources;

import com.naya.poc.model.OktaOAuthConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/signInConfig")
@Produces("application/json")
public class LoginWidgetConfigResource {

    private final OktaOAuthConfig config;


    public LoginWidgetConfigResource(OktaOAuthConfig config) {
        this.config = config;
    }

    @GET
    public OktaOAuthConfig getConfig(){
        return config;
    }
}
