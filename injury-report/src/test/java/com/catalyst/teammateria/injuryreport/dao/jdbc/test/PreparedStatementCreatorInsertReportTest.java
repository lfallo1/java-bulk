package com.catalyst.teammateria.injuryreport.dao.jdbc.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.PreparedStatementCreatorInsertReport;
import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;

public class PreparedStatementCreatorInsertReportTest {

    private PreparedStatementCreatorInsertReport target;

    private InjuryReportBean injuryReportBean;

    private static final Integer EMPLOYEE_ID_COLUMN = 1;

    private static final Integer EXPECTED_EMPLOYEE_ID = 1;

    private static final Integer REPORTER_ID_COLUMN = 2;

    private static final Integer EXPECTED_REPORTER_ID = 2;

    private static final Integer WEATHER_ID_COLUMN = 3;

    private static final Integer EXPECTED_WEATHER_ID = 3;

    private static final Integer BODYPART_ID_COLUMN = 4;

    private static final Integer EXPECTED_BODYPART_ID = 4;

    private static final Integer INJURY_TYPE_ID_COLUMN = 5;

    private static final Integer EXPECTED_INJURY_TYPE_ID = 5;

    private static final Integer DESCRIPTION_COLUMN = 10;

    private static final String EXPECTED_DESCRIPTION = "foo";

    @Before
    public void setup() {
        injuryReportBean = new InjuryReportBean();
        injuryReportBean.setEmployeeId(EXPECTED_EMPLOYEE_ID);
        injuryReportBean.setReporterId(EXPECTED_REPORTER_ID);
        injuryReportBean.setWeatherId(EXPECTED_WEATHER_ID);
        injuryReportBean.setBodyPartId(EXPECTED_BODYPART_ID);
        injuryReportBean.setInjuryTypeId(EXPECTED_INJURY_TYPE_ID);
        injuryReportBean.setDescription(EXPECTED_DESCRIPTION);
        target = new PreparedStatementCreatorInsertReport(injuryReportBean,
                "sql");
    }

    @Test
    public void createPreparedStatementTest() {
        Connection connection = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        try {
            when(connection.prepareStatement(anyString(), any(String[].class)))
                    .thenReturn(ps);
            target.createPreparedStatement(connection);
            verify(ps).setInt(EMPLOYEE_ID_COLUMN, EXPECTED_EMPLOYEE_ID);
            verify(ps).setInt(REPORTER_ID_COLUMN, EXPECTED_REPORTER_ID);
            verify(ps).setInt(WEATHER_ID_COLUMN, EXPECTED_WEATHER_ID);
            verify(ps).setInt(BODYPART_ID_COLUMN, EXPECTED_BODYPART_ID);
            verify(ps).setInt(INJURY_TYPE_ID_COLUMN, EXPECTED_INJURY_TYPE_ID);
            verify(ps).setString(DESCRIPTION_COLUMN, EXPECTED_DESCRIPTION);
        } catch (SQLException e) {
            assert false;
        }
    }
}
