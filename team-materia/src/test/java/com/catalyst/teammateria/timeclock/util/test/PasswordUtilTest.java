package com.catalyst.teammateria.timeclock.util.test;

import java.lang.reflect.Constructor;

import org.junit.Test;

import com.catalyst.teammateria.timeclock.util.PasswordUtil;

/**
 * Asserts nothing, because it algorithmically dependent. These tests would be
 * unmaintainable in the only situation in which we would need to update the
 * code. (To change the algorithm that hashes the password).
 * 
 * @author dGrimes
 */
public class PasswordUtilTest {

    private static final String MD_DEFAULT_INSTANCE = "SHA-256";

    private static final String A_STRING_LITERAL = "Words";

    @Test
    public void encryptDefaultsPasswordTest() {
        PasswordUtil.encryptPassword(A_STRING_LITERAL, A_STRING_LITERAL);
    }

    @Test
    public void encryptWithOneDefaultPasswordTest() {
        PasswordUtil.encryptPassword(A_STRING_LITERAL, A_STRING_LITERAL,
                MD_DEFAULT_INSTANCE, A_STRING_LITERAL);
    }

    @Test
    public void encryptWithNoDefaultsPasswordTest() {
        PasswordUtil.encryptPassword(A_STRING_LITERAL, A_STRING_LITERAL,
                A_STRING_LITERAL, A_STRING_LITERAL);
    }

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = PasswordUtil.class
                .getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[])null);
    }
}
