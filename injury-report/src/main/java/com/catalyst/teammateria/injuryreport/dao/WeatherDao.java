package com.catalyst.teammateria.injuryreport.dao;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.Weather;

/**
 * Weather Dao Interface
 * 
 * @author dGrimes
 */
public interface WeatherDao {

    /**
     * create new object in database
     * 
     * @param new Weather object
     */
    void addWeather(Weather newObject);

    /**
     * Read a Weather object from database accessed by ID
     * 
     * @param key
     * @return Weather
     */
    Weather getWeatherById(Integer key);

    /**
     * Save changes made to a Weather object
     * 
     * @param Weather
     *            object
     */
    void updateWeather(Weather newObject);

    /**
     * Delete a Weather from database
     * 
     * @param Weather
     *            object
     */
    void removeWeather(Weather newObject);

    /**
     * Return a list of all Weathers
     */
    List<Weather> getAll();
}
