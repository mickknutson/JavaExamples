package com.baselogic.tutorials.reference.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Provider;
import java.security.Security;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@RunWith(JUnit4.class)
public final class ProviderTests {

    private static final Logger logger = LoggerFactory.getLogger(ProviderTests.class);

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void providerTests() {

		Provider[] providers = Security.getProviders();

        assertThat(providers.length, is(greaterThan(1)));

        for(Provider p: providers){
			logger.info("Provider: " + p);
		}

		logger.info("------------------------------------------------------");

		for(Provider prv : providers)
		 {
			logger.info("Provider Name: "+prv.getName());
			logger.info("Provider Class Name: "+prv.getClass().getName());
			logger.info("Provider Version: "+prv.getVersion());
			logger.info("Provider Information: "+prv.getInfo());
			logger.info("------------------------------------------------------");
		 }
	}

}
