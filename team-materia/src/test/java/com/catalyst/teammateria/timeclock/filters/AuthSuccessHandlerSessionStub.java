package com.catalyst.teammateria.timeclock.filters;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;


public class AuthSuccessHandlerSessionStub implements HttpSession{
    private User currUser;
    private User userRegular = new User();
    private User userAdmin = new User();
    private User userOther = new User();
    
    @Override
    public void setAttribute(String name, Object value) {
        currUser = (User)value;
        //System.out.println(currUser.getFirstName());
        //System.out.println(currUser.getRole().getUserRole());
    }
    
    @Override
    public Object getAttribute(String name) {
        return currUser;
    }

    @Override
    public long getCreationTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getLastAccessedTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getMaxInactiveInterval() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        // TODO Auto-generated method stub
        return null;
    }

    

    @Override
    public Object getValue(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getValueNames() {
        // TODO Auto-generated method stub
        return null;
    }

   

    @Override
    public void putValue(String name, Object value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeAttribute(String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeValue(String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void invalidate() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isNew() {
        // TODO Auto-generated method stub
        return false;
    }
}
