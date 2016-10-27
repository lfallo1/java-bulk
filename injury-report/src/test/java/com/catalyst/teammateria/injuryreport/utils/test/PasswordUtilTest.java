package com.catalyst.teammateria.injuryreport.utils.test;

import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.Constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.catalyst.teammateria.injuryreport.utils.PasswordUtil;

@RunWith (MockitoJUnitRunner.class)
public class PasswordUtilTest {

    @InjectMocks
    private PasswordUtil target;

    @Mock
    BCryptPasswordEncoder encoder;

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = PasswordUtil.class
                .getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[])null);
    }

    @SuppressWarnings ("static-access")
    @Test
    public void encodePasswordTest() {
        String test = "test";
        assertNotEquals(test, target.encodePassword(test));
    }
}
