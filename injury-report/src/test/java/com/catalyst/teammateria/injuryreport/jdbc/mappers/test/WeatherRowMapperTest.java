package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.WeatherRowMapper;
import com.catalyst.teammateria.injuryreport.model.Weather;

public class WeatherRowMapperTest {

    private static final int WEATHERID_FIELD = 1;
    
    private static final int EXPECTED_WEATHERID = 1;

    private static final int WEATHER_CONDITION_FIELD = 2;
    
    private static final String EXPECTED_WEATHER_CONDITION = "sunny";

    private WeatherRowMapper target;

    private ResultSet rs;

    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new WeatherRowMapper();
    }

    @Test
    public void mapRowTest() {
        Weather returnedWeather = new Weather();
        try {
            when(rs.getInt(WEATHERID_FIELD)).thenReturn(EXPECTED_WEATHERID);
            when(rs.getString(WEATHER_CONDITION_FIELD)).thenReturn(EXPECTED_WEATHER_CONDITION);
            returnedWeather = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(returnedWeather.getWeatherId() == EXPECTED_WEATHERID);
        assertEquals(returnedWeather.getWeatherCondition(), EXPECTED_WEATHER_CONDITION);
    }
}
