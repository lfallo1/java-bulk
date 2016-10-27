package com.catalyst.teammateria.injuryreport.utils.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.utils.DateUtil;

@RunWith (MockitoJUnitRunner.class)
public class DateUtilTest {
    
    @InjectMocks
    private DateUtil target;

    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = DateUtil.class
                .getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[])null);
    }
    
    @SuppressWarnings ("static-access")
    @Test
    public void convertDateFormatTest(){
        assertEquals(target.convertDateFormat("11/21/2014"), "2014-11-21");
    }

}
