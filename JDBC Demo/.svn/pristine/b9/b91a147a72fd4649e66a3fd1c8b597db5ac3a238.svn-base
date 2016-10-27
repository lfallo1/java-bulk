package com.catalyst.cycle.jdbc_demo.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.cycle.jdbc_demo.model.User;

/**
 * 
 * Note that if we are getting a list of users
 * this method of mapping is not ideal. 
 * Seperate user objects will be created for each users manager 
 * even if multiple users have the same manager. Refer to
 * the {@link #UserResultSetExtractor.class} for how to get a list of users
 * without duplicating managers.
 * @author egover
 *
 */
public class UserRowMapper implements RowMapper<User> {

    private static final int USERID_FIELD = 1;
    private static final int USER_FIRSTNAME_FIELD = 2;
    private static final int USER_LASTNAME_FIELD = 3;
    private static final int USER_MANAGER_ID_FIELD = 4;
    private static final int USER_MANAGER_FIRSTNAME_FIELD = 5;
    private static final int USER_MANAGER_LASTNAME_FIELD = 6;

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserID(rs.getInt(USERID_FIELD));
        user.setFirstName(rs.getString(USER_FIRSTNAME_FIELD));
        user.setLastName(rs.getString(USER_LASTNAME_FIELD));
        Integer managerID = rs.getInt(USER_MANAGER_ID_FIELD);
        if (managerID != 0) {
           //If the managerID was not 0, then set the manager object for the user
            User manager = new User();
            manager.setUserID(managerID);
            manager.setFirstName(rs.getString(USER_MANAGER_FIRSTNAME_FIELD));
            manager.setLastName(rs.getString(USER_MANAGER_LASTNAME_FIELD));
            user.setManager(manager);

        }

        return user;
    }

}
