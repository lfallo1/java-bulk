package com.catalyst.teammateria.timeclock.util.test;

import static org.junit.Assert.assertFalse;

import java.lang.reflect.Constructor;

import org.junit.Test;
import org.mockito.Mock;

import com.catalyst.teammateria.timeclock.util.ValidationUtil;

public class ValidationUtilTest {

    @Mock
    private ValidationUtil target;

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = ValidationUtil.class
                .getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[])null);
    }

    @Test
    public void isNumberTest() {
        ValidationUtil.isNumber("01");
        assertFalse(ValidationUtil.isNumber("Aa"));
    }

    @Test
    public void isAlphaTest() {
        ValidationUtil.isAlpha("Aa");
        assertFalse(ValidationUtil.isAlpha("01"));
    }

    @Test
    public void isAlphaNumericTest() {
        ValidationUtil.isAlphaNumeric("Aa1");
        assertFalse(ValidationUtil.isAlphaNumeric("Aa!="));
    }

    @Test
    public void isEmail() {
        ValidationUtil.isEmail("adietrich@catalystitservices.com");
        assertFalse(ValidationUtil.isEmail("fsfsaf&&&6^^**@sfsdfds.vnm"));
    }

    @Test
    public void isValidPassword() {
        ValidationUtil.isValidPassword("password89");
        assertFalse(ValidationUtil.isValidPassword("12345"));
    }
}
