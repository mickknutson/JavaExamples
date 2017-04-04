package com.baselogic.tutorials.reference.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

/**
 * For ECB and CBC modes of operation, each block (128 bits or 16 bytes for AES)
 * of plaintext or will be encrypted once it is available. Furthermore, your
 * example uses PKCS#7 padding (or, in Java, "PKCS5Padding") to make sure that
 * the last plaintext is padded to a full block. Otherwise you would not be able to encrypt it.
 * <p/>
 * When you encrypt it is obviously not a good idea to buffer all the resulting ciphertext.
 * So update will simply return all blocks it can encrypt. Therefore you will always get a multiple
 * of the blocksize back. That means that it may keep part of a block in an internal buffer.
 * Of course, once you add enough bytes for a full block it will encrypt and return the data.
 * All this means that the update may return up to blocksize - 1 bytes more or less than the input.
 * For smaller input it may not return anything at all - everything is buffered until the block is full.
 * <p/>
 * Now PKCS#7 padding is always applied, adding 1 to blocksize bytes.
 * Obviously the cipher must know if more bytes are to be expected, or if the end of the plaintext is reached,
 * before it applies the padding. So doFinal will return at least one block, even if no data is presented to it.
 * If the previous updates did not return any data, then the doFinal() method will return the entire ciphertext.
 * <p/>
 * More or less the same reasoning can be performed for decryption. Also note the getOutputSize(int inputLength)
 * and the getBlockSize() methods of Cipher. If you want to receive all the data back at once, you can use a
 * stream cipher mode of operation, such as "AES/CTR/NoPadding", which has a block size of 1 byte.
 *
 *
 * "AES" for Cipher will use the provider's default.
 *
 * For the SUN provider, that would be "AES/ECB/PKCS5Padding".
 * You should use at least "AES/CBC/PKCS5Padding" (requires a random IV)
 * or "AES/CTR/NoPadding" (requires a unique IV).
 *
 * Always specify the mode of operation and padding explicitly.
 *
 * And never use getBytes() without specifying a character encoding
 *
 * Electronic Codebook (ECB)
 * Cipher Block Chaining (CBC)
 * Propagating Cipher Block Chaining (PCBC)
 *
 * <b>Cipher Feedback (CFB)</b>
 * CFB shares two advantages over CBC mode with the stream cipher modes OFB and CTR: the block cipher
 * is only ever used in the encrypting direction, and the message does not need to be padded to a multiple
 * of the cipher block size (though ciphertext stealing can also be used to make padding unnecessary).
 *
 * Output Feedback (OFB)
 *
 * <b>Counter (CTR)</b>
 * Note: CTR mode (CM) is also known as integer counter mode (ICM) and segmented integer counter (SIC) mode.
 * Like OFB, Counter mode turns a block cipher into a stream cipher.
 *
 * TODO:
 * Look into Ciphertext stealing (CTS)
 *
 *
 * Every implementation of the Java platform is required to support the
 * following standard Cipher transformations with the keysizes in
 * parentheses:
 *
 *  AES/CBC/NoPadding (128)
 *  AES/CBC/PKCS5Padding (128)
 *  AES/ECB/NoPadding (128)
 *  AES/ECB/PKCS5Padding (128)
 *  DES/CBC/NoPadding (56)
 *  DES/CBC/PKCS5Padding (56)
 *  DES/ECB/NoPadding (56)
 *  DES/ECB/PKCS5Padding (56)
 *  DESede/CBC/NoPadding (168)
 *  DESede/CBC/PKCS5Padding (168)
 *  DESede/ECB/NoPadding (168)
 *  DESede/ECB/PKCS5Padding (168)
 *  RSA/ECB/PKCS1Padding (1024, 2048)
 *  RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
 *  RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)

 * @see <a href="http://en.wikipedia.org/wiki/Block_cipher_mode_of_operation">Block_cipher_mode_of_operation</a>
 *
 */
public final class EncryptionPaddingDemo {

    public static final String ALGORITHM = "AES";

    public byte[] encryptSecretKeySpec(final byte[] data,
                                       final byte[] keyPass,
                                       final String algorithmModePad)
            throws GeneralSecurityException {

        Cipher cipher = Cipher.getInstance(algorithmModePad);

        SecretKeySpec AESkeySpec = new SecretKeySpec(keyPass, ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, AESkeySpec);

        cipher.update(data);

        return cipher.doFinal();
    }

} // The end...
