package com.catalyst.teammateria.injuryreport.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.model.Weather;

public class WeatherTest {
    Weather target;
    
    private static final Integer VALID_ID_1 = 1;
    
    private static final Integer VALID_ID_2 = 2;
    
    @Before    
    public void setup(){
        target = new Weather();
    }
    
    @Test
    public void testGetWeatherId(){        
        target.setWeatherId(VALID_ID_1);
        assertEquals(VALID_ID_1, target.getWeatherId());        
    }
    
    @Test
    public void testGetWeatherConditions(){
        target.setWeatherCondition("rainy");
        assertEquals("rainy", target.getWeatherCondition());
    }
    
    @Test
    public void equalsTest(){
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        String test = "Test";
        assertFalse(target.equals(test));
        Weather testWeather = new Weather();
        target.setWeatherId(VALID_ID_1);
        testWeather.setWeatherId(target.getWeatherId());
        assertEquals(target, testWeather);
    }
    
    @Test
    public void hashTest(){
        Weather weather = new Weather();
        weather.setWeatherId(VALID_ID_1);
        target.setWeatherId(VALID_ID_2);
        int weatherHash = weather.hashCode();
        int targetHash = target.hashCode();
        assertFalse(targetHash == weatherHash);
    }   
}
