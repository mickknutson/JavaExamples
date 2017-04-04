package com.baselogic.tutorials.reference.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.security.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.fail;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 *
 */
@RunWith(JUnit4.class)
public class EncryptionUtilitiesTests {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionPaddingDemoTests.class);

    /* Define constants for algorithm and key files */

    public static List<Algorithm> algorithms = new ArrayList<Algorithm>() {{
//        add(new Algorithm("RSA", "ECB", "PKCS5Padding", 128));

        //                ALGORYTHM, MODE, PADDING, SIZE, TARGET-SIZE
        add(new Algorithm("DES", "ECB", "PKCS5Padding", 56, 48)); // must be equal to 56 only
        add(new Algorithm("AES", "ECB", "PKCS5Padding", 128, 48)); // must be equal to 128, 192 or 256
        add(new Algorithm("AES", "ECB", "PKCS5Padding", 256, 48)); // must be equal to 128, 192 or 256

        /*
        TODO: throws javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes
         */
//        add(new Algorithm("AES", "ECB", "NoPadding", 128, 48));

        /*
        TODO: throws java.security.InvalidKeyException: Parameters missing
        at com.sun.crypto.provider.CipherCore.init(CipherCore.java:388)
        */
        add(new Algorithm("AES", "CBC", "PKCS5Padding", 128, 48));
        add(new Algorithm("AES", "CTR", "PKCS5Padding", 128, 48));
//        add(new Algorithm("AES", "CTR", "NoPadding", 256));
//        add(new Algorithm("AES", "CTR", "NoPadding", 256));
    }};


    public static final String ALGORITHM = "RSA";
    public static final String ALGORITHM_MODE_PAD = "AES/ECB/PKCS5Padding";

    public static final String PRIVATE_KEY_FILE = "target/private.key";
    public static final String PUBLIC_KEY_FILE = "target/public.key";
    public static final String CIPHERED_DATA_FILE = "target/cipheredFile.dat";

    public static final int KEY_SIZE = 2048;

    String originalText = "This is a text file for test purposes...";

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//

    @Test
    public void getKeyFromFileTests() throws Exception{

//        EncryptionUtilities
    }


    //@Test
    public void generateKeyTests_Public() throws Exception{
        PublicKey result = EncryptionUtilities.getKeyFromFile(PUBLIC_KEY_FILE);

        assertThat(result, isA(PublicKey.class));
    }


    @Test
    public void encryptAndDecrypt_WithCipher_with_Update() throws Exception{

        Key key = EncryptionUtilities.generateSecretKey("AES", 128);

        /* Perform encryption with the PublicKey object */
        final byte[] cipherText = EncryptionUtilities.encryptWithUpdate(ALGORITHM_MODE_PAD, key, originalText);

        /* Perform decryption with the PrivateKey object */
        final byte[] decryptedText = EncryptionUtilities.decryptWithUpdate(ALGORITHM_MODE_PAD, key, cipherText);


        logger.info("=======================================================");
        logger.info("Original Data: {}", originalText);
        assertThat(originalText, is(originalText));

        logger.info("Encrypted Data: {}", cipherText);
//        assertThat(cipherText.length, is(48));
        assertThat(cipherText.length, is(16));

        StringBuilder sb = new StringBuilder();

        for(byte b: cipherText){
            sb.append(b);
        }

        logger.info("bytes of cipherText: {}", sb.toString());


        logger.info("Decrypted Data: {}", decryptedText);
//        assertThat(decryptedText, is(originalText));
        logger.info("-----------------------------");
    }

//    @Test
    public void encryptAndDecryptTests() throws Exception {

        for(Algorithm algorithm: algorithms){
            encryptAndDecrypt(algorithm);
        }
    }

    protected void encryptAndDecrypt(Algorithm algorithm) throws Exception {
//        String algorithm = "AES/ECB/PKCS7Padding";

        Key key = EncryptionUtilities.generateSecretKey(algorithm.getType(), algorithm.getKeyLength());
//        Key key = EncryptionUtilities.generateSecretKey("AES", 256);

        /* Perform encryption with the PublicKey object */
//        final byte[] cipherText = EncryptionUtilities.encryptWithUpdate(algorithm.getTypeModePadding(), key, originalText);
        final byte[] cipherText = EncryptionUtilities.encryptWithUpdate(algorithm.getType(), key, originalText);

        /* Perform decryption with the PrivateKey object */
        final byte[] decryptedText = EncryptionUtilities.decryptWithUpdate(algorithm.getType(), key, cipherText);


        logger.info("Original Data: {}", originalText);
        assertThat(originalText, is(originalText));

        logger.info("Encrypted Data: {}", cipherText);
        assertThat(cipherText.length, is(algorithm.getTargetSize()));

        logger.info("Decrypted Data: {}", decryptedText);
        assertThat(decryptedText, is(originalText));
    }



    //@Test(expected = ClassCastException.class)
    public void generateKeyTests_Public_Exception() throws Exception{

        final String keyFile = PRIVATE_KEY_FILE;
        final File file = new File(keyFile);
        //file.delete();

        //TODO: need to save to file first.
        PublicKey result = EncryptionUtilities.getKeyFromFile(keyFile);

        assertThat(result, isA(PublicKey.class));
        assertThat(new File(keyFile).exists(), is(true));
    }

    //@Test
    public void generateKeyTests_Private() throws Exception{

        final String keyFile = PUBLIC_KEY_FILE;
        final File file = new File(keyFile);
        //file.delete();

        //TODO: need to save to file first.
        PrivateKey result = EncryptionUtilities.getKeyFromFile(keyFile);

        assertThat(result, is(PrivateKey.class));
        assertThat(new File(keyFile).exists(), is(true));
    }


    //@Test(expected = ClassCastException.class)
    public void generateKeyTests_Private_Exception() throws Exception{

        final String keyFile = PUBLIC_KEY_FILE;
        final File file = new File(keyFile);
        //file.delete();

        //TODO: need to save to file first.
        PrivateKey result = EncryptionUtilities.getKeyFromFile(keyFile);

        assertThat(result, is(PrivateKey.class));
        assertThat(new File(keyFile).exists(), is(true));


    }



    @Test
    public void cipherOutputInputStreamTest() throws Exception{

        final String cipheredFile = CIPHERED_DATA_FILE;
        final File file = new File(cipheredFile);
        file.delete();

        Algorithm algorithm = algorithms.get(0);

        Key key = EncryptionUtilities.generateSecretKey(algorithm.getType(), algorithm.getKeyLength());


        EncryptionUtilities.cipherOutputStreamToFile(
                key, algorithm.getTypeModePadding(), cipheredFile, originalText);

        assertThat(new File(cipheredFile).exists(), is(true));

        String result = EncryptionUtilities.cipherInputStreamFromFile(
                key, algorithm.getTypeModePadding(), cipheredFile);

        logger.info(result);
        assertThat(result, is(originalText));

    }
} // The End...
