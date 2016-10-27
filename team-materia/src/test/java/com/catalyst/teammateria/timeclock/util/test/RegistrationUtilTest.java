package com.catalyst.teammateria.timeclock.util.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;

import org.junit.Test;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.formbeans.RegistrationForm;
import com.catalyst.teammateria.timeclock.util.RegistrationUtil;

public class RegistrationUtilTest {

    RegistrationForm regForm = new RegistrationForm();

    User user = new User();

    String hash = "TESTHASH";

    @Test
    public void separateUser() {
        assertEquals(regForm.getUsername(),
                RegistrationUtil.separateUser(regForm).getUsername());
    }

    @Test
    public void separatePassword() {
        assertEquals(user.getUserId(),
                RegistrationUtil.separatePassword(user, hash).getUserId());
    }

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = RegistrationUtil.class
                .getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[])null);
    }
}
