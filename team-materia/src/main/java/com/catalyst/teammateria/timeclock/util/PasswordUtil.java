package com.catalyst.teammateria.timeclock.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * A utility class for encrypting Passwords
 * 
 * @author aDietrich
 */
public final class PasswordUtil {

    private static final Logger LOG = Logger.getLogger(PasswordUtil.class);

    private static final String MD_DEFAULT_INSTANCE = "SHA-256";

    private static final String MD_DEFAULT_CHARSET = "UTF-8";

    private PasswordUtil() {
    }

    /**
     * A method to implement the default encryption algorithm
     * 
     * @param password
     *            - a string containing the user's password
     * @param salt
     *            - the username
     * @return an encrypted password
     */
    public static String encryptPassword(String password, String salt) {
        return encryptPassword(password, salt, MD_DEFAULT_INSTANCE,
                MD_DEFAULT_CHARSET);
    }

    /**
     * A method to implement a specific encryption algorithm and character set
     * 
     * @param password
     *            - a string containing the user's password
     * @param salt
     *            - the username
     * @param mdInstance
     *            - the desired instance for Message Digest
     * @param mdChar
     *            - the desired character set
     * @return an encrypted password
     */
    public static String encryptPassword(String password, String salt,
            String mdInstance, String mdChar) {
        String plainText = password + salt;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(mdInstance);
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage());
            return encryptPassword(password, salt);
        }
        try {
            md.update(plainText.getBytes(mdChar));
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
            return encryptPassword(password, salt);
        }
        byte raw[] = md.digest();
        return (new Base64()).encodeToString(raw);
    }
}
