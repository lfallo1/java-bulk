package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.InjuryTypeRowMapper;
import com.catalyst.teammateria.injuryreport.model.InjuryType;

public class InjuryTypeRowMapperTest {

    private static final int TYPE_ID_FIELD = 1;

    private static final int EXPECTED_TYPE_ID = 1;

    private static final int TYPE_NAME_FIELD = 2;

    private static final String EXPECTED_TYPE_NAME = "fall";

    private InjuryTypeRowMapper target;

    private ResultSet rs;

    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new InjuryTypeRowMapper();
    }

    @Test
    public void mapRowTest() {
        InjuryType returnedInjuryType = new InjuryType();
        try {
            when(rs.getInt(TYPE_ID_FIELD)).thenReturn(EXPECTED_TYPE_ID);
            when(rs.getString(TYPE_NAME_FIELD)).thenReturn(EXPECTED_TYPE_NAME);
            returnedInjuryType = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(returnedInjuryType.getTypeId() == EXPECTED_TYPE_ID);
        assertEquals(returnedInjuryType.getTypeName(), EXPECTED_TYPE_NAME);
    }
}
