package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Special row mapper (used with count(column)): returns true if it counts a
 * single column
 * 
 * @author dGrimes
 */
public class UserBoolean implements RowMapper<Boolean> {

    @Override
    public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1) == 1;
    }
}
