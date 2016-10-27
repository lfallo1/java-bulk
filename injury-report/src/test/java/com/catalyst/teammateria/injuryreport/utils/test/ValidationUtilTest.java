package com.catalyst.teammateria.injuryreport.utils.test;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.utils.DateUtil;
import com.catalyst.teammateria.injuryreport.utils.ValidationUtil;

@RunWith (MockitoJUnitRunner.class)
public class ValidationUtilTest {

    @InjectMocks
    private ValidationUtil target;

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = DateUtil.class
                .getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[])null);
    }

    @Test
    public void isValidNameTest() {
        assertTrue(ValidationUtil.isValidName("XXxx"));
    }

    @Test
    public void isValidUsernameTest() {
        assertTrue(ValidationUtil.isValidUsername("XXxx99"));
    }
}
