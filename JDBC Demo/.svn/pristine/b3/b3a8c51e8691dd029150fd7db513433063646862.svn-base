package com.catalyst.cycle.jdbc_demo.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.catalyst.cycle.jdbc_demo.model.User;

/**
 * Best for data that contains One-to-Many relationships.
 * 
 * In this example, we are expecting 6 fields: UserID, FirstName, LastName,
 * ManagerID, Manager FirstName, and Manager LastName.
 * 
 *  As we add entries to the list if the user has a manager and that manager is a user in our list,
 *  then we set that user's manager to the manager in the userlist. If the userlist does not contain
 *  a matching user, we add one. Make sure User has an overridden .equals method comparing just
 *  the user ID's.
 * 
 * @author egover
 *
 */
public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

    private static final int USERID_FIELD = 1;
    private static final int USER_FIRSTNAME_FIELD = 2;
    private static final int USER_LASTNAME_FIELD = 3;
    private static final int USER_MANAGER_ID_FIELD = 4;
    private static final int USER_MANAGER_FIRSTNAME_FIELD = 5;
    private static final int USER_MANAGER_LASTNAME_FIELD = 6;

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException,
            DataAccessException {

        //Our list of users as they are pulled from the database
        List<User> userList = new ArrayList<User>();

        while (rs.next()) {

            User user = new User();
            user.setUserID(rs.getInt(USERID_FIELD));
            //Check if the user has already been added to the userList
            if(!userList.contains(user)){
                //Add the user to the userList
                user.setFirstName(rs.getString(USER_FIRSTNAME_FIELD));
                user.setLastName(rs.getString(USER_LASTNAME_FIELD));
                userList.add(user);
            }else{
                //Do not add the user. Instead, get the user from userList.
                user = userList.get(userList.indexOf(user));
            }
            //Gets the manager ID. According to the documentation
            //0 is returned if the field is NULL.
            Integer managerID = rs.getInt(USER_MANAGER_ID_FIELD);
         
            if (managerID != 0) {

                
                User manager = new User();
                manager.setUserID(managerID);
                //Check if the manager is already in the userList.
                if (!userList.contains(manager)) {

                    //Add the manager to the userList
                    manager.setFirstName(rs
                            .getString(USER_MANAGER_FIRSTNAME_FIELD));
                    manager.setLastName(rs
                            .getString(USER_MANAGER_LASTNAME_FIELD));
                    userList.add(manager);
                } else {
                    //Get the manager from the userList. This will prevent us
                    //from storing duplicate objects in memory. The reference
                    //to the original manager object will be lost and cleaned
                    //up with Garbage Collection
                    manager = userList.get(userList.indexOf(manager));
                }

                //Set the reference to the manager.
                user.setManager(manager);

            }

        }

        return userList;
    }

}
