package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.catalyst.teammateria.injuryreport.dao.WeatherDao;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.WeatherRowMapper;
import com.catalyst.teammateria.injuryreport.model.Weather;

/**
 * Weather Dao Implementation for JDBC
 * 
 * @author dGrimes
 */
@Component
public class WeatherDaoJdbc implements WeatherDao {

    private static final String INSERT_WEATHER = "INSERT INTO weather (`weather_condition`) VALUES (?)";

    private static final String GET_WEATHER_BY_ID = "SELECT w.weather_id, w.weather_condition FROM weather AS w WHERE w.weather_id = ?";

    private static final String UPDATE_WEATHER = "UPDATE weather AS w SET w.weather_condition = ? WHERE w.weather_id = ?";

    private static final String REMOVE_WEATHER = "DELETE FROM weather WHERE weather_id = ?";

    private static final String GET_ALL_WEATHERS = "SELECT w.weather_id, w.weather_condition FROM weather AS w";

    private JdbcTemplate jdbcTemplate;

    /**
     * Sets the JdbcTemplate using the provided dataSource.
     * 
     * @param dataSource
     */
    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * For adding a JdbcTemplate object directly.
     * 
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addWeather(Weather newObject) {
        this.jdbcTemplate.update(INSERT_WEATHER,
                newObject.getWeatherCondition());
    }

    @Override
    public Weather getWeatherById(Integer key) {
        return this.jdbcTemplate.queryForObject(GET_WEATHER_BY_ID,
                new Object[] {key}, new WeatherRowMapper());
    }

    @Override
    public void updateWeather(Weather newObject) {
        this.jdbcTemplate.update(UPDATE_WEATHER,
                newObject.getWeatherCondition(), newObject.getWeatherId());
    }

    @Override
    public void removeWeather(Weather newObject) {
        this.jdbcTemplate.update(REMOVE_WEATHER, newObject.getWeatherId());
    }

    @Override
    public List<Weather> getAll() {
        return this.jdbcTemplate.query(GET_ALL_WEATHERS,
                new WeatherRowMapper());
    }
}
