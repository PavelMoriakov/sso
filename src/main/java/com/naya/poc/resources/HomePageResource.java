package com.naya.poc.resources;

import com.naya.poc.auth.AccessTokenPrincipal;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/message")
public class HomePageResource {
    @GET
    public String handleGetRequest(@Auth AccessTokenPrincipal tokenPrincipal){
        return "Hello from POC SSO ! we'll be contacting you at:" + tokenPrincipal.getName();
    }
}
