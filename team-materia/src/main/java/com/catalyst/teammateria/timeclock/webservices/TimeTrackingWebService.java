package com.catalyst.teammateria.timeclock.webservices;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.services.TimeTrackingService;

/**
 * The Restful controller for the time tracking page
 * 
 * @author dGrimes
 */
@RestController
public class TimeTrackingWebService {

    private TimeTrackingService timeTrackingService;

    private static final String CURRENT_USER = "currentUser";

    /**
     * The abstracted service to be used for this page
     *
     * @param timeTrackingService
     */
    @Autowired
    public void setTimeTrackingService(TimeTrackingService timeTrackingService) {
        this.timeTrackingService = timeTrackingService;
    }

    /**
     * AJAX call to get the hours for the user in session
     * 
     * @param session
     * @param date
     * @return
     */
    @RequestMapping (value = "/timetracking/date", method = RequestMethod.GET)
    public List<UserTime> myHoursGET(HttpSession session, long date) {
        return timeTrackingService.getWeek(
                (User)session.getAttribute(CURRENT_USER), new LocalDate(date));
    }

    /**
     * Checks if the user in session is currently clocked in or not
     * 
     * @param session
     * @return
     */
    @RequestMapping (value = "/timetracking/clockstatus", method = RequestMethod.GET)
    public boolean myClockStatusGET(HttpSession session) {
        return timeTrackingService.currentlyOpenTimeTicket((User)session
                .getAttribute(CURRENT_USER));
    }

    /**
     * Get the oldest week with hours recorded for the user in session
     * 
     * @param session
     * @return
     */
    @RequestMapping (value = "/timetracking/old", method = RequestMethod.GET)
    public LocalDate oldestWeekGET(HttpSession session) {
        return timeTrackingService.getOldest((User)session
                .getAttribute(CURRENT_USER));
    }

    /**
     * Add a record to the UserTime table of the user in session's clock in time
     * 
     * @param session
     */
    @RequestMapping (value = "/timetracking", method = RequestMethod.POST)
    public void clockInPOST(HttpSession session) {
        timeTrackingService.clockIn((User)session.getAttribute(CURRENT_USER));
    }

    /**
     * Update the user in session's clock out time field
     * 
     * @param session
     */
    @RequestMapping (value = "/timetracking", method = RequestMethod.PUT)
    public void clockOutPOST(HttpSession session) {
        timeTrackingService.clockOut((User)session.getAttribute(CURRENT_USER));
    }
}
