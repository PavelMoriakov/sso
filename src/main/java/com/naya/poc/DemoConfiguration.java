package com.naya.poc;

import com.naya.poc.model.OktaOAuthConfig;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class DemoConfiguration extends Configuration {

    public OktaOAuthConfig oktaOAuth = new OktaOAuthConfig();

}
