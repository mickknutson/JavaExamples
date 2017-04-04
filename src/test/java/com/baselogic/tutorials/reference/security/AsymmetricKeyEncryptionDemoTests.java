package com.baselogic.tutorials.reference.security;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by mickknutson on 4/30/15.
 */
@RunWith(JUnit4.class)
public final class AsymmetricKeyEncryptionDemoTests {

    private static final Logger logger = LoggerFactory.getLogger(AsymmetricKeyEncryptionDemoTests.class);


    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void asymmetricKeyEncryptionDemoTest() throws Exception {
        /* Check if key is present */

        if (!AsymmetricKeyEncryptionDemo.areKeysPresent()) {
            AsymmetricKeyEncryptionDemo.generateKeyAndSaveToFile();
        }

        /* Obtain the public key as a PublicKey object */
        final String originalText = "Text to be encrypted.";

        final PublicKey publicKey = EncryptionUtilities.getKeyFromFile(AsymmetricKeyEncryptionDemo.PUBLIC_KEY_FILE);
        final PrivateKey privateKey = EncryptionUtilities.getKeyFromFile(AsymmetricKeyEncryptionDemo.PRIVATE_KEY_FILE);

        /* Perform encryption with the PublicKey object */
        final byte[] encryptedText = AsymmetricKeyEncryptionDemo.encrypt(publicKey, originalText);

        /* Perform decryption with the PrivateKey object */
        final String decryptedText = AsymmetricKeyEncryptionDemo.decrypt(privateKey, encryptedText);

			/* Print out the original, encrypted, and decrypted data */
        logger.info("Original Data: {}", originalText);
        assertThat(originalText, is(originalText));

        logger.info("Encrypted Data: {}", encryptedText);
        assertThat(encryptedText.length, is(256));

        logger.info("Decrypted Data: {}", decryptedText);
        assertThat(decryptedText, is(originalText));

    }

    @Test
    public void test__asymmetricKeyEncryption_with_update() throws Exception {
        /* Check if key is present */

        if (!AsymmetricKeyEncryptionDemo.areKeysPresent()) {
            AsymmetricKeyEncryptionDemo.generateKeyAndSaveToFile();
        }


        final PublicKey publicKey = EncryptionUtilities.getKeyFromFile(AsymmetricKeyEncryptionDemo.PUBLIC_KEY_FILE);
        final PrivateKey privateKey = EncryptionUtilities.getKeyFromFile(AsymmetricKeyEncryptionDemo.PRIVATE_KEY_FILE);

        /* Perform encryption with the PublicKey object */
        final String[] originalText = {"foo", "bar", "baz"};

        byte[] encryptedText = EncryptionUtilities.encryptWithUpdate("RSA", publicKey, originalText);

        /* Perform decryption with the PrivateKey object */
//        final String decryptedText = AsymmetricKeyEncryptionDemo.decrypt(privateKey, cipherText);
//        byte[] decryptedText = EncryptionUtilities.decryptWithUpdate("RSA", privateKey, cipherText);
        final byte[] decryptedText = EncryptionUtilities.decryptWithUpdate("RSA", privateKey, encryptedText);

			/* Print out the original, encrypted, and decrypted data */
        logger.info("Original Data: {}", originalText, null);
        logger.info("Encrypted Data: {}", encryptedText);
        assertThat(encryptedText.length, is(256));

        logger.info("Decrypted Data: {}", decryptedText);
//        assertThat(decryptedText, is(originalText));

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
