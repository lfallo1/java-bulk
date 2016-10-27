package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.BodyPartRowMapper;
import com.catalyst.teammateria.injuryreport.model.BodyPart;

public class BodyPartRowMapperTest {

    private static final int BODYPARTID_FIELD = 1;

    private static final int EXPECTED_BODYPARTID = 1;

    private static final int BODYPART_NAME_FIELD = 2;

    private static final String EXPECTED_BODYPART_NAME = "elbow";

    BodyPartRowMapper target;

    ResultSet rs;

    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new BodyPartRowMapper();
    }

    @Test
    public void mapRowTest() {
        BodyPart expectedBodyPart = new BodyPart();
        expectedBodyPart.setBodyPartId(EXPECTED_BODYPARTID);
        expectedBodyPart.setBodyPartName(EXPECTED_BODYPART_NAME);
        BodyPart returnedBodyPart = new BodyPart();
        try {
            when(rs.getInt(BODYPARTID_FIELD)).thenReturn(EXPECTED_BODYPARTID);
            when(rs.getString(BODYPART_NAME_FIELD)).thenReturn(
                    EXPECTED_BODYPART_NAME);
            returnedBodyPart = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(expectedBodyPart.getBodyPartId() == returnedBodyPart
                .getBodyPartId());
        assertEquals(expectedBodyPart.getBodyPartName(),
                returnedBodyPart.getBodyPartName());
    }
}
