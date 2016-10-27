package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.Weather;

/**
 * Base weather row mapper
 * 
 * @author dGrimes
 */
public class WeatherRowMapper implements RowMapper<Weather> {

    private static final int WEATHERID_FIELD = 1;

    private static final int WEATHER_CONDITION_FIELD = 2;

    @Override
    public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {
        Weather weather = new Weather();
        weather.setWeatherId(rs.getInt(WEATHERID_FIELD));
        weather.setWeatherCondition(rs.getString(WEATHER_CONDITION_FIELD));
        return weather;
    }
}
