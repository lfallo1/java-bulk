package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.Position;

/**
 * Basic row mapper for the Position class
 * 
 * @author dGrimes
 */
public class PositionRowMapper implements RowMapper<Position> {

    private static final int POSITIONID_FIELD = 1;

    private static final int POSITION_NAME_FIELD = 2;

    @Override
    public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
        Position position = new Position();
        position.setPositionId(rs.getInt(POSITIONID_FIELD));
        position.setPositionName(rs.getString(POSITION_NAME_FIELD));
        return position;
    }
}
