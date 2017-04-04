package com.baselogic.tutorials.reference.security;

/**
 * Created by mickknutson on 9/3/15.
 */
public interface ICrypt {
    String encode(String plainText)     throws Exception;
    String decode(String encodedText)   throws Exception;

}
