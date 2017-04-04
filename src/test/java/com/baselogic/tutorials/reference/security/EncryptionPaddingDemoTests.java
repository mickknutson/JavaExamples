package com.baselogic.tutorials.reference.security;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by mickknutson on 4/30/15.
 */
@RunWith(JUnit4.class)
public class EncryptionPaddingDemoTests {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionPaddingDemoTests.class);

    EncryptionPaddingDemo encryptionPaddingDemo = new EncryptionPaddingDemo();

    String seed = "foo Bar";

    String keyPassword = "someSecretKeyTha7890123456789012";
    String keyPassword16Bit = "someSecretKeyTha";

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//

    @Test
    public void encryptSecretKeySpec_AES_ECB_PKCS5Padding() throws Exception{

        byte[] result = encryptionPaddingDemo.encryptSecretKeySpec(
                seed.getBytes(Charset.defaultCharset()),
                keyPassword16Bit.getBytes(Charset.defaultCharset()),
                "AES/ECB/PKCS5Padding"
        );

        String resultHex = EncryptionUtilities.toHex(result);
        String expectedHex = "3bbe66510de4eb2ea944c618f59b963b";

        assertThat(resultHex, is(expectedHex));
        assertThat(resultHex.length(), is(32));
        logger.info("expected: {}", expectedHex);
        logger.info("result: {}", result);

    }


//    @Test
//    java.security.InvalidKeyException: Illegal key size or default parameters
    public void encryptSecretKeySpec_AES_CBC_PKCS5Padding() throws Exception{

//        ICrypt crypt = Base64Decorator.wrap(new AESCrypt());
         ICrypt crypt = new AESCrypt();

        String result = crypt.encode("hello");
        String decoded = crypt.decode(result);


        String expected = "3bbe66510de4eb2ea944c618f59b963b";

        assertThat(result, is(expected));
        assertThat(result.length(), is(32));
        logger.info("expected: {}", expected);
        logger.info("result: {}", result);

    }


    @BeforeClass
    public static void beforeClass(){
        logger.debug("=== BEFORE ============================================");
    }
    @AfterClass
    public static void afterClass(){
        logger.debug("=== AFTER =============================================");
    }


} // The End...
