package com.baselogic.tutorials.reference.security;

import java.util.Base64;

/**
 * Created by mickknutson on 9/3/15.
 */
public class Base64Decorator implements ICrypt {

    private ICrypt realCrypt;

    private Base64Decorator(ICrypt crypt) {
        this.realCrypt = crypt;
    }

    public static ICrypt wrap(ICrypt real) {
        return new Base64Decorator(real);
    }

    @Override
    public String encode(String plainText) throws Exception {
        String encoded = realCrypt.encode(plainText);
        return Base64.getEncoder().encodeToString(encoded.getBytes());
    }

    @Override
    public String decode(String encodedText) throws Exception {
        byte[] encodedBytes = Base64.getDecoder().decode(encodedText);
        return realCrypt.decode(new String(encodedBytes));
    }

}

