package com.baselogic.tutorials.reference.security;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by mickknutson on 4/30/15.
 */
@RunWith(JUnit4.class)
public class DigitalSignatureDemoTests {

    private static final Logger logger = LoggerFactory.getLogger(DigitalSignatureDemoTests.class);

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void providerTests() {

        /* Call the generateDigitalSignature() method of the DigitalSignatureDemo
         * class to generate a digital signature of the text passed to the
		 * generateDigitalSignature() method
		 */
        DigitalSignatureDemo.generateDigitalSignature("ABB statement.");


		/* Call the verifyDigitalSignature() method of the DigitalSignatureDemo
		 * class to verify a digital signature of the text passed to the
		 * verifyDigitalSignature() method
		 */
        DigitalSignatureDemo.verifyDigitalSignature("ABB statement.");

    }

    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
    @BeforeClass
    public static void beforeClass(){
        logger.warn("=== BEFORE ============================================");
    }
    @AfterClass
    public static void afterClass(){
        logger.warn("=== AFTER =============================================");
    }
    @Before
    public void beforeEachTest() throws Exception {}

    @After
    public void afterEachTest() throws Exception {}
}
