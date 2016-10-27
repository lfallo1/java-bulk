package com.catalyst.teammateria.timeclock.webservices;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.formbeans.TimeManagementForm;
import com.catalyst.teammateria.timeclock.services.TimeManagementService;

/**
 * The Restful controller for the time management page
 * 
 * @author adietrich
 */
@RestController
public class TimeManagementWebService {

    private TimeManagementService timeManagementService;

    @Autowired
    public void setTimeManagementService(
            TimeManagementService timeManagementService) {
        this.timeManagementService = timeManagementService;
    }

    /**
     * Called to obtain a list of dates that can be filtered into specific weeks
     * (those that were worked by an individual) that are listed by the page
     * 
     * @param userId
     *            - the user about whom to query the database
     * @return a list of dates that translate into the weeks worked by the
     *         aforementioned individual
     */
    @RequestMapping (value = "/timemanagement/weeks/{userId}", method = RequestMethod.GET)
    public List<LocalDate> getDaysWorkedForWeeks(@PathVariable int userId) {
        return timeManagementService.getDays(userId);
    }

    /**
     * Used to get the list of user time objects for the specified week and
     * individual
     * 
     * @param userId
     *            the user
     * @param week
     *            the week to grab for
     * @return a list of user time objects that represent all the time spent
     *         working in the week by the user
     */
    @RequestMapping (value = "/timemanagement/hours/{userId}", method = RequestMethod.GET)
    public List<UserTime> getUserTime(@PathVariable int userId, long week) {
        return timeManagementService.getUserTime(userId, week);
    }

    /**
     * The method to update all the user time objects passed in
     * 
     * @param userId
     *            - the user for whom to update (passed in this way to be more
     *            restful)
     * @param timeManagementForm
     *            - a list of user time objects to update
     * @return
     */
    @RequestMapping (value = "/timemanagement/{userId}", method = RequestMethod.PUT)
    public String updateUserTime(@PathVariable int userId,
            TimeManagementForm timeManagementForm) {
        return timeManagementService.updateUserTime(timeManagementForm, userId);
    }
}
