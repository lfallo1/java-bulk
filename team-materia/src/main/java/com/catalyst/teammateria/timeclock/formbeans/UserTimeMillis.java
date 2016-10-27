package com.catalyst.teammateria.timeclock.formbeans;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;

/**
 * Object that tracks total hours worked per week by user
 * 
 * @author lFallon
 */
public class UserTimeMillis {
    private UserTime userTime;
    private long millis;
    
    public UserTimeMillis(){
    }   
    
    /**
     * Public constructor for UserTimeMillis
     * @param userTime
     * @param millis
     */
	public UserTimeMillis(UserTime userTime, long millis) {
		super();
		this.userTime = userTime;
		this.millis = millis;
	}

	public UserTime getUserTime() {
		return userTime;
	}

	public void setUserTime(UserTime userTime) {
		this.userTime = userTime;
	}

	public long getMillis() {
		return millis;
	}

	public void setMillis(long millis) {
		this.millis = millis;
	}
    
    
}
