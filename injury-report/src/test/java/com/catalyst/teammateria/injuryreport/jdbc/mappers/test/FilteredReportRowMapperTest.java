package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.FilteredReportRowMapper;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.Report;

@RunWith(MockitoJUnitRunner.class)
public class FilteredReportRowMapperTest {

	private static final String POSITION_NAME = "PositionName";
	private static final String DATE_STRING = "2014-11-11";
	private static final String LAST_NAME = "LastName";
	private static final String FIRST_NAME = "FirstName";
	private static final int REPORT_ID_FIELD = 1;
	private static final int FIRST_NAME_FIELD = 2;
	private static final int LAST_NAME_FIELD = 3;
	private static final int DATE_OF_REPORT_FIELD = 4;
	private static final int ENABLED_FIELD = 5;	
	
	private FilteredReportRowMapper target;	
	private Report rpt;
	private ResultSet rs;
	
	@Before
	public void setup(){
		target = new FilteredReportRowMapper();
		rpt = createReport();
		rs = Mockito.mock(ResultSet.class);
	}
	
	@After
	public void teardown(){
		
	}

	@Test
	public void mapRowTest(){
		Report actual = null;
		Report expected = createReport();
		try{
			Mockito.when(rs.getString(FIRST_NAME_FIELD)).thenReturn(rpt.getEmployee().getFirstName());
			Mockito.when(rs.getString(LAST_NAME_FIELD)).thenReturn(rpt.getEmployee().getLastName());
			Mockito.when(rs.getInt(REPORT_ID_FIELD)).thenReturn(rpt.getEmployee().getEmployeeId());
			Mockito.when(rs.getDate(DATE_OF_REPORT_FIELD)).thenReturn(rpt.getDateOfReport());
			Mockito.when(rs.getBoolean(ENABLED_FIELD)).thenReturn(rpt.getEnabled());
			actual = target.mapRow(rs, 1);
		}
		catch(SQLException e){
			assert false;
		}
		Assert.assertEquals(expected, actual);
	}
	
	@Ignore
	private Employee createEmployee() {
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setFirstName(FIRST_NAME);
		emp1.setLastName(LAST_NAME);
		emp1.setPosition(createPosition());
		return emp1;
	}
	
	@Ignore
	private Position createPosition(){
		Position pos1 = new Position();
		pos1.setPositionId(1);
		pos1.setPositionName(POSITION_NAME);
		return pos1;
	}
	
	@Ignore
	private Report createReport() {
		Report rpt = new Report();
		rpt.setReportId(1);
		rpt.setDateOfReport(Date.valueOf(DATE_STRING));
		rpt.setEnabled(true);
		rpt.setEmployee(createEmployee());
		return rpt;
	}	
}
