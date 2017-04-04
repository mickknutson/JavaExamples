package com.baselogic.tutorials.reference.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;

/**
 * Created by mickknutson on 9/3/15.
 */
public class AESCrypt implements ICrypt {

    private static final int PASSWORD_ITERATIONS = 65536; // vs brute force
    private static final int KEY_LENGTH          = 256;

    private char[]     pass                = "password".toCharArray(); // hardcoded or read me from a file
    private byte[]     salt                = new byte[20]; // for more confusion
    private byte[]     ivBytes             = null;

    public AESCrypt() {
        //
        // INIT SALT
        //
        SecureRandom secureRandom = new SecureRandom(); // seed is 0
        secureRandom.setSeed(secureRandom.generateSeed(16));
        secureRandom.nextBytes(salt);
    }

    private Cipher createCipher(boolean encryptMode) throws Exception {

        if (!encryptMode && ivBytes == null) {
            throw new IllegalStateException("ivBytes is null");
        }

        final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        final PBEKeySpec spec = new PBEKeySpec(pass, salt, PASSWORD_ITERATIONS, KEY_LENGTH);

        final SecretKey secretKey = factory.generateSecret(spec);
        final SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        final int mode = encryptMode ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;

        if (ivBytes == null) {

            //TODO java.security.InvalidKeyException: Illegal key size or default parameters
            cipher.init(mode, secret);
            final AlgorithmParameters params = cipher.getParameters();
            ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();

        } else {

            cipher.init(mode, secret, new IvParameterSpec(ivBytes));
        }

        return cipher;
    }

    @Override
    public String encode(String plainText) throws Exception {

        Cipher cipher = createCipher(true);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

        return new String(encryptedBytes);

    }

    @Override
    public String decode(String encodedText) throws Exception {

        Cipher cipher = createCipher(false);

        return new String(cipher.doFinal(encodedText.getBytes()), "UTF-8");
    }
}

