package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.InjuryType;

/**
 * Row mapper for Injury Type
 * 
 * @author dGrimes
 */
public class InjuryTypeRowMapper implements RowMapper<InjuryType> {

    private static final int TYPE_ID_FIELD = 1;

    private static final int TYPE_NAME_FIELD = 2;

    @Override
    public InjuryType mapRow(ResultSet rs, int rowNum) throws SQLException {
        InjuryType injury = new InjuryType();
        injury.setTypeId(rs.getInt(TYPE_ID_FIELD));
        injury.setTypeName(rs.getString(TYPE_NAME_FIELD));
        return injury;
    }
}