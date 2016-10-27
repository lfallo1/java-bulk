package com.catalyst.teammateria.injuryreport.service;

import java.util.List;

import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;
import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;

/**
 * Interface for the injury reporting service implementation
 * 
 * @author dGrimes
 */
public interface InjuryReportingService {

    /**
     * Returns a list of weather types
     * 
     * @return - all weather types
     */
    List<Weather> getWeatherForSelect();

    /**
     * Returns a list of injury types
     * 
     * @return - all injury types
     */
    List<InjuryType> getInjuryTypeForSelect();

    /**
     * Returns a list of body parts
     * 
     * @return - all body parts
     */
    List<BodyPart> getBodyPartForSelect();

    /**
     * Returns a User based on username
     * 
     * @param username
     *            - username to use
     * @return - User (with full employee detail)
     */
    User getUserDetails(String username);

    /**
     * Pushes injury report bean to database
     * 
     * @param injuryReportBean
     * @return confirmation number
     */
    Integer addInjuryReport(InjuryReportBean injuryReportBean);
}
