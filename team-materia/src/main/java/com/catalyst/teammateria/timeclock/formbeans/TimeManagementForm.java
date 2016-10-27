package com.catalyst.teammateria.timeclock.formbeans;

import java.util.List;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;

/**
 * A simple wrapper for the UserTime objects that come off the TimeManagement
 * page
 * 
 * @author dGrimes
 */
public class TimeManagementForm {

    private List<UserTime> userTimeList;

    public List<UserTime> getUserTimeList() {
        return userTimeList;
    }

    public void setUserTimeList(List<UserTime> list) {
        this.userTimeList = list;
    }
}
