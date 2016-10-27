package com.catalyst.teammateria.injuryreport.webservices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;
import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;
import com.catalyst.teammateria.injuryreport.service.InjuryReportingService;

/**
 * Web service for the injury reporting page
 * 
 * @author dGrimes
 */
@RestController
public class InjuryReportingWebService {

    private InjuryReportingService injuryReportingService;

    @Autowired
    public void setInjuryReportingService(
            InjuryReportingService injuryReportingService) {
        this.injuryReportingService = injuryReportingService;
    }

    /**
     * GET request for weather
     * 
     * @return List of all weather
     */
    @RequestMapping (value = "/injuryreporting/weather", method = RequestMethod.GET)
    public List<Weather> getWeatherList() {
        return injuryReportingService.getWeatherForSelect();
    }

    /**
     * GET request for injury types
     * 
     * @return List of all injury types
     */
    @RequestMapping (value = "/injuryreporting/injurytype", method = RequestMethod.GET)
    public List<InjuryType> getInjuryTypeList() {
        return injuryReportingService.getInjuryTypeForSelect();
    }

    /**
     * GET request for injury types
     * 
     * @return List of all injury types
     */
    @RequestMapping (value = "/injuryreporting/bodypart", method = RequestMethod.GET)
    public List<BodyPart> getBodyPartList() {
        return injuryReportingService.getBodyPartForSelect();
    }

    /**
     * GET request for the user details
     * 
     * @param request
     *            - HttpServletRequest to gether the base user data from
     * @return Full user detail (including employee details)
     */
    @RequestMapping (value = "/injuryreporting/getUser", method = RequestMethod.GET)
    public User getUser(HttpServletRequest request) {
        return injuryReportingService.getUserDetails(request.getUserPrincipal()
                .getName());
    }

    /**
     * POST request for submitting an injury report
     * 
     * @param injuryReportBean
     *            - the injury report
     * @return Confirmation number
     */
    @RequestMapping (value = "/injuryreporting", method = RequestMethod.POST)
    public Integer addReport(InjuryReportBean injuryReportBean) {
        return injuryReportingService.addInjuryReport(injuryReportBean);
    }
}
