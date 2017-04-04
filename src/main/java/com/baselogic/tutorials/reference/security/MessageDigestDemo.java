package com.baselogic.tutorials.reference.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

/**
 * In cryptography, SHA-1 is a cryptographic hash function designed by the
 * United States National Security Agency and is a U.S. Federal Information
 * Processing Standard published by the United States NIST.[2]
 * SHA-1 produces a 160-bit (20-byte) hash value known as a message digest.
 * <p>
 * A SHA-1 hash value is typically rendered as a hexadecimal number, 40 digits long.
 * SHA stands for "secure hash algorithm". The four SHA algorithms are structured
 * differently and are named SHA-0, SHA-1, SHA-2, and SHA-3. SHA-0 is the original
 * version of the 160-bit hash function published in 1993
 * under the name "SHA": it was not adopted by many applications.
 * <p>
 * Published in 1995, SHA-1 is very similar to SHA-0, but alters the original
 * SHA hash specification to correct alleged weaknesses. SHA-2, published in 2001,
 * is significantly different from the SHA-1 hash function.
 *
 * TODO: Need to add the following into the Message Digest:
 * TODO: MAC
 * TODO: UMAC
 * TODO: CMAC

 */
public final class MessageDigestDemo {

    private static final Logger logger = LoggerFactory.getLogger(MessageDigestDemo.class);

    @FunctionalInterface
    interface Encoding {
        String encode(byte[] input);
    }

    Encoding base64 = (byte[] input) -> {
        return new String(Base64.getEncoder().encode(input));
    };
    Encoding hex = (byte[] input) -> {
        return String.format("%0" + (input.length << 1) + "x", new BigInteger(1, input));
    };

    public String encode(byte[] input, Encoding encoding) {
        return encoding.encode(input);
    }

    public String toBase64(byte[] input) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(input);
//        return new String(Base64.getEncoder().encode(input));
    }

    public String toHex(byte[] input) {
        return Hex.encodeHexString(input);
//        return String.format("%0" + (input.length << 1) + "x", new BigInteger(1, input));
    }

    /**
     * Generate an SHA1 encoded MessageDigest.
     *
     * @param clearText
     * @param salt
     * @param date
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String generateMessageDigest_Base64(final String algorithm,
                                               final String salt,
                                               final String clearText,
                                               final Date date)
            throws NoSuchAlgorithmException {

        byte[] digest = EncryptionUtilities.generateMessageDigest(algorithm, clearText);

        // to hex:
//        return enc(digest, hex);
        return toBase64(digest);

//        String temp = salt + date.getTime() + clearText;
//
//        try {
//            MessageDigest md = MessageDigest.getInstance(algorithm);
//
//            byte[] digest = md.digest(temp.getBytes());
//
////            return enc(digest, base64);
//            return toBase64(digest);
//        } catch (NoSuchAlgorithmException e) {
//            throw e;
//        }
    }


    public String generateMessageDigestSalt_Hex(final String algorithm,
                                                final String salt,
                                                final String clearText)
            throws NoSuchAlgorithmException {

        byte[] digest = EncryptionUtilities.generateMessageDigest(algorithm, clearText);

        // to hex:
//        return enc(digest, hex);
        return toHex(digest);
    }

    /**
     * Creates a MessageDigest of byte array and returns it as a hexadecimal String.
     *
     * @param clearText The data to digest.
     * @return The hexadecimal result of the digest.
     */
    public String generateMessageDigest_Hex(final String algorithm,
                                            final String clearText) {
        if (StringUtils.isBlank(clearText)) {
            throw new RuntimeException("Input data is invalid");
        }
        try {
            byte[] digest = EncryptionUtilities.generateMessageDigest(algorithm, clearText);

            return toHex(digest);

        } catch (final Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     * Generate Message Authentication Code (MAC)
     *
     * @param algorithm
     * @param clearText
     * @param salt
     * @return
     * @throws Exception
     */
    public byte[] generateMac(final String algorithm,
                              final String salt,
                              final String clearText)
            throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(salt.getBytes(), algorithm);

        Mac mac = Mac.getInstance(algorithm);
        mac.init(signingKey);
        return mac.doFinal(clearText.getBytes());
    }


} // the end...
