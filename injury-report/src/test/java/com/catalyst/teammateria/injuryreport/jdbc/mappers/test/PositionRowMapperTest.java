package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.PositionRowMapper;
import com.catalyst.teammateria.injuryreport.model.Position;

public class PositionRowMapperTest {

    private static final int POSITIONID_FIELD = 1;
    
    private static final int EXPECTED_POSITIONID = 1;

    private static final int POSITION_NAME_FIELD = 2;
    
    private static final String EXPECTED_POSITION_NAME = "foo";

    private PositionRowMapper target;

    private ResultSet rs;

    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new PositionRowMapper();
    }

    @Test
    public void mapRowTest() {
        Position returnedPosition = new Position();
        try {
            when(rs.getInt(POSITIONID_FIELD)).thenReturn(EXPECTED_POSITIONID);
            when(rs.getString(POSITION_NAME_FIELD)).thenReturn(EXPECTED_POSITION_NAME);
            returnedPosition = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(returnedPosition.getPositionId() == EXPECTED_POSITIONID);
        assertEquals(returnedPosition.getPositionName(), EXPECTED_POSITION_NAME);
    }
}
