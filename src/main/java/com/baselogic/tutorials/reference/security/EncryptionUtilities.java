package com.baselogic.tutorials.reference.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import java.io.*;
import java.security.*;

/**
 *
 *
 *
 * Hybrid Cryptosystem

 Example: For a 1024 bit key, you can encrypt around 1024 / 8 = 128 bytes
 Note: Exact value is 128 bytes - 11 bytes for padding

 You can use a symmetric key to encrypt and decrypt the data (> 128 bytes) to be transferred. RSA can only encrypt data up to a certain extent (e.g. 128 bytes) which depends on the RSA key length.

 This means that if you want to transfer anything bigger than 128 bytes, you have to transfer a symmetric key < 128 bytes first so you can have the following:

 Generate a symmetric key (< 128 bytes)
 Encrypt symmetric key with RSA
 Transfer encrypted symmetric key
 Decrypt symmetric key with RSA
 Encrypt data (> 128 bytes) with symmetric key
 Transfer encrypted data
 Decrypt encrypted data with symmetric key

 or (transfer encrypted symmetric key and encrypted data at the same time)

 Generate a symmetric key (< 128 bytes)
 Encrypt symmetric key with RSA
 Encrypt data (> 128 bytes) with symmetric key
 Transfer encrypted symmetric key & encrypted data
 Decrypt symmetric key with RSA
 Decrypt encrypted data with symmetric key
 *
 * see SecretKeySpec
 */
public final class EncryptionUtilities {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtilities.class);

    public static final String ENCODING = "UTF-8";


    /**
     * Generate KeyPair
     * @param algorithmModePad
     * @param keySize
     * @return
     */
    public static KeyPair generateKeyPair(final String algorithmModePad, final int keySize) {
        KeyPair keyPair = null;

        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithmModePad);
            keyGen.initialize(keySize);

            keyPair = keyGen.generateKeyPair();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return keyPair;
    }

    /**
     * Generate SecretKey
     * @param algorithmModePad
     * @param keySize
     * @return
     */
    public static Key generateSecretKey(final String algorithmModePad, final int keySize) {
        SecretKey secretKey = null;

        try {
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithmModePad);
            keyGenerator.init(keySize);

            secretKey = keyGenerator.generateKey();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return secretKey;
    }


    /**
     *
     * @param text
     * @param algorithmModePad
     * @param key
     * @return
     */
    public static byte[] encrypt(final String algorithmModePad, final PublicKey key, final String text) {
        byte[] cipherText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            cipherText = cipher.doFinal(text.getBytes(ENCODING));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     *
     * @param originalText
     * @param algorithmModePad
     * @param key
     * @return
     */
    public static byte[] encryptWithUpdate(final String algorithmModePad, final Key key, final String ... originalText) {
        byte[] cipherText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            logger.info("Original Data: {}", originalText);

            for(String text: originalText){
                logger.info("text: {}", text);
                cipher.update(text.getBytes(ENCODING));
            }
            cipherText = cipher.doFinal();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     *
     * @param encryptedText
     * @param algorithmModePad
     * @param privateKey
     * @return
     */
    public static String decrypt(final String algorithmModePad, final PrivateKey privateKey, final byte[] encryptedText) {
        byte[] decryptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            decryptedText = cipher.doFinal(encryptedText);

        } catch (Exception ex) {
            logger.error("decrypt exception", ex);
            ex.printStackTrace();
        }

        return new String(decryptedText);
    }


    /**
     *
     * @param encryptedText
     * @param algorithmModePad
     * @param key
     * @return
     */
    public static byte[] decryptWithUpdate(final String algorithmModePad,
                                           final Key key,
                                           final byte[] encryptedText) {
        byte[] decryptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.DECRYPT_MODE, key);

            cipher.update(encryptedText);
            decryptedText = cipher.doFinal();

        } catch (Exception ex) {
            logger.error("decryptWithUpdate exception", ex);
            ex.printStackTrace();
        }

        logger.info("(a) Decrypted Data: {}", decryptedText);

        return decryptedText;
    }



    /**
     * Use Generics, to retrieve a PublicKey or PrivateKey
     * @param fileName
     * @return PublicKey or PrivateKey
     */
    @SuppressWarnings("unchecked")
    protected static <T> T getKeyFromFile(final String fileName) {

        T key = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(fileName))
        ){
            key = (T) inputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return key;
    }

    /**
     * Save a Key to file, WITHOUT encryption
     * @param fileName
     * @param key
     */
    protected static void saveKeyToFile(final String fileName, final Key key) {

        File keyFile = new File(fileName);

        try (ObjectOutputStream keyOs = new ObjectOutputStream(
                new FileOutputStream(keyFile)))
        {
//            keyFile = new File(fileName);
            if (keyFile.getParentFile() != null) {
                boolean mkdirsResult = keyFile.getParentFile().mkdirs();

                if(mkdirsResult){
                    logger.error("'{}'.mkdirs() failed", keyFile.getParentFile().getAbsolutePath());
                    throw new RuntimeException("mkdirs() failed");
                }
            }

            boolean keyFileResult = keyFile.createNewFile();
            if(keyFileResult){
                logger.error("'{}'.createNewFile() failed", fileName);
                throw new RuntimeException("keyFile.createNewFile() failed");
            }

            keyOs.writeObject(key);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Export a SecretKey to a file.
     * @param secretKey
     * @param algorithmModePad
     * @param fileName
     */
    protected static void exportKeyWrapToFile(final SecretKey secretKey, final String algorithmModePad, final String fileName) {

        File keyFile = new File(fileName);


        try (
                DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(keyFile))
        )
        {

            // start
            Cipher c = Cipher.getInstance(algorithmModePad);
            c.init(Cipher.WRAP_MODE, secretKey);

            byte[] wrappedKey = c.wrap(secretKey);

            dos.write(wrappedKey, 0, wrappedKey.length);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypt text to a CipherOutputStream and save to File.
     * @param key
     * @param algorithmModePad
     * @param fileName
     * @param text
     */
    public static void cipherOutputStreamToFile(final Key key,
                                                final String algorithmModePad,
                                                final String fileName,
                                                final String text) {
        try {
            Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            FileOutputStream fos = new FileOutputStream(fileName);

            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            PrintWriter pw = new PrintWriter(cos);

            pw.println(text);

            pw.close();
        } catch (Exception e) {
            logger.error("Error : {}", e.getMessage(), e);
        }
    }

    /**
     * Decrypt text from CipherInputStream.
     * @param key
     * @param algorithmModePad
     * @param fileName
     */
    public static String cipherInputStreamFromFile(final Key key,
                                                 final String algorithmModePad,
                                                 final String fileName) {
        StringBuilder sb = new StringBuilder();

        try {
            Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.DECRYPT_MODE, key);


            // Read encrypted text from file
            FileInputStream fis = new FileInputStream(fileName);
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            BufferedReader br = new BufferedReader(new InputStreamReader(cis));


            String line;
            while ((line = br.readLine()) != null) {
                logger.info(line);
                sb.append(line);
            }
            br.close();

        } catch (Exception e) {
            logger.error("Error : {}", e.getMessage(), e);
        }
        return sb.toString();
    }

    //-----------------------------------------------------------------------//
    //-----------------------------------------------------------------------//

    /**
     * FIXME: MAKE THIS SIMPLE.
     *
     * @param algorithm
     * @param clearText
     * @return MessageDigest as a byte[]
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generateMessageDigest(final String algorithm,
                                               final String clearText)
            throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(algorithm);
        return md.digest(clearText.getBytes());
    }


    /**
     * FIXME: MAKE THIS SIMPLE.
     *
     * @param algorithm
     * @param clearText
     * @param salt
     * @return MessageDigest as a byte[]
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generateMessageDigest_OLD(final String algorithm,
                                               final byte[] salt,
                                               final String clearText)
            throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hash1 = md.digest(clearText.getBytes());

        // Resets the digest for further use.
//        md.reset();

        byte[] hash2 = md.digest(hash1);

        // Resets the digest for further use.
//        md.reset();

        if (salt != null) {
            // Updates the digest using the specified array of bytes.
            md.update(salt);
        }

        // Resets the digest for further use.
//        md.reset();

        // Updates the digest using the specified array of bytes.
        md.update(hash2);

        byte[] digest = md.digest();

        for (int i = 0; i < digest.length; i++) {
            digest[i] = (byte) (digest[i] ^ hash1[i]);
        }

        return digest;
    }


    public static String toBase64(byte[] input) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(input);
//        return new String(Base64.getEncoder().encode(input));
    }

    public static String toHex(byte[] input) {
        return Hex.encodeHexString(input);
//        return String.format("%0" + (input.length << 1) + "x", new BigInteger(1, input));
    }


} // The End...
