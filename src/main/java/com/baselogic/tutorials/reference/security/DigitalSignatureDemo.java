package com.baselogic.tutorials.reference.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 * TODO: Need to add the following into the Signatures:
 * TODO: SHA-1
 * TODO: SHA-2 (256)
 * TODO: MD5
 */
public final class DigitalSignatureDemo {

	private static final Logger logger = LoggerFactory.getLogger(DigitalSignatureDemo.class);

    public static final String SIGNATURE_FILE = "target/signature.sig";
    public static final String SIGNATURE_PUBLIC_KEY_FILE = "target/signature_pub.key";


    public static void main(String[] args) {

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


	/*
	 * This method generates a digital signature and saves the digital signature
	 * along with the public key to the file system
	 */
	public static void generateDigitalSignature(final String input) {
		try {

			/* Obtain input text to sign as a byte array */
			//byte[] data = "Digital Signature Demo.".getBytes("UTF8");
			byte[] data = input.getBytes("UTF8");

			/* Generate a key pair */
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(1024);

			/*
			 * Obtain the private and public keys as PrivateKey and PublicKey
			 * objects
			 */
			KeyPair pair = keyGen.generateKeyPair();

			PrivateKey priv = pair.getPrivate();
			PublicKey pub = pair.getPublic();

			/* Generate the digital signature */
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initSign(priv);
			sig.update(data);

			/* Sign the input data */
			byte[] signatureBytes = sig.sign();

			/* Save the digital signature to a file */
			FileOutputStream sigFos = new FileOutputStream(SIGNATURE_FILE);
			sigFos.write(signatureBytes);
			sigFos.close();
            logger.info("Digital signature saved to: signature.sig");

			/* Save the public key to a file */
			byte[] key = pub.getEncoded();
			FileOutputStream keyFos = new FileOutputStream(SIGNATURE_PUBLIC_KEY_FILE);
			keyFos.write(key);
			keyFos.close();

            logger.info("Public key saved to: pubk.key");

		} catch (Exception e) {
            logger.error("Caught exception {}", e.getMessage(), e);
		}

	}

	/* This method verifies a digital signature */
	public static void verifyDigitalSignature(final String input) {
		try {

			/* Retrive the public key from the file system */
			FileInputStream keyFis = new FileInputStream(SIGNATURE_PUBLIC_KEY_FILE);
			byte[] encKey = new byte[keyFis.available()];
			keyFis.read(encKey);
			keyFis.close();

			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

			/* Retrive the digital signature from the file system */
			FileInputStream sigFis = new FileInputStream(SIGNATURE_FILE);
			byte[] sigToVerify = new byte[sigFis.available()];
			sigFis.read(sigToVerify);
			sigFis.close();

			/* Verify the signature */
			Signature sig = Signature.getInstance("SHA1withRSA");

			sig.initVerify(pubKey);

			byte[] data = input.getBytes("UTF8");
//			byte[] data = "Digital Signature Demo.".getBytes("UTF8");
			sig.update(data);

			boolean result = sig.verify(sigToVerify);

			logger.info("Signature verification result: {}", result);

        } catch (Exception ex) {
			ex.printStackTrace();
		}

	}

} // THE END...
