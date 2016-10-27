package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.BodyPart;

/**
 * Row mapper for mapping the BodyPart class to the bodypart table 
 * @author gPorter
 *
 */
public class BodyPartRowMapper implements RowMapper<BodyPart> {

    private static final int BODYPARTID_FIELD = 1;

    private static final int BODYPART_NAME_FIELD = 2;

    @Override
    public BodyPart mapRow(ResultSet rs, int rowNum) throws SQLException {
        BodyPart bodyPart = new BodyPart();
        bodyPart.setBodyPartId(rs.getInt(BODYPARTID_FIELD));
        bodyPart.setBodyPartName(rs.getString(BODYPART_NAME_FIELD));
        return bodyPart;
    }
}
