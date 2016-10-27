package com.catalyst.teammateria.injuryreport.dao.jdbc.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import com.catalyst.teammateria.injuryreport.dao.jdbc.WeatherDaoJdbc;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.WeatherRowMapper;
import com.catalyst.teammateria.injuryreport.model.Weather;

public class WeatherDaoJdbcTest {

    WeatherDaoJdbc target;
    JdbcTemplate   mockJdbcTemplate;
    DataSource     mockedDataSrc = Mockito.mock(DataSource.class);

    @Before
    public void setup() {
        target = new WeatherDaoJdbc();
        this.mockJdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(mockedDataSrc);
        target.setJdbcTemplate(mockJdbcTemplate);
    }

    @Ignore
    public Weather defaultWeather() {
        Weather weather = new Weather();
        weather.setWeatherId(1);
        weather.setWeatherCondition("N/A");
        return weather;
    }

    @Test
    public void addWeatherTest() {
        Weather weather = defaultWeather();
        target.addWeather(weather);
        verify(mockJdbcTemplate).update(anyString(), eq("N/A"));
    }

    @Test
    public void getWeatherByIdTest() {
        Weather expectedWeather = defaultWeather();
        Mockito.when(
                mockJdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(WeatherRowMapper.class))).thenReturn(
                expectedWeather);
        Weather actualWeather = target.getWeatherById(1);
        assertEquals(actualWeather, expectedWeather);
    }

    @Test
    public void updateWeatherTest() {
        Weather weather = defaultWeather();
        target.updateWeather(weather);
        verify(mockJdbcTemplate).update(anyString(), eq("N/A"), eq(1));
    }

    @Test
    public void removeWeatherTest() {
        Weather weather = defaultWeather();
        target.removeWeather(weather);
        verify(mockJdbcTemplate).update(anyString(), eq(1));
    }

    @Test
    public void getAllTest() {
        List<Weather> expectedWeather = new ArrayList<Weather>();
        Mockito.when(
                mockJdbcTemplate.query(anyString(),
                        any(WeatherRowMapper.class))).thenReturn(
                expectedWeather);
        List<Weather> actualWeather = target.getAll();
        assertEquals(actualWeather, expectedWeather);
    }
}
