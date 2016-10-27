package com.catalyst.teammateria.injuryreport.webservices.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.Weather;
import com.catalyst.teammateria.injuryreport.service.AdminToolsService;
import com.catalyst.teammateria.injuryreport.webservices.AdminToolsWebService;

@RunWith(MockitoJUnitRunner.class)
public class AdminToolsWebServiceTest {

	private static final String VALID_INJURY_TYPE = "cut";

	private static final String VALID_BODYPART = "bodypart";

	private static final String VALID_POSITION = "position";

	private static final String VALID_WEATHER = "rainy";

	@InjectMocks
	private AdminToolsWebService target;

	@Mock
	private AdminToolsService adminToolsService;

	@Test
	public void getAllWeatherTest() {
		List<Weather> expectedWeatherList = new ArrayList<Weather>();
		expectedWeatherList.add(new Weather());
		when(adminToolsService.getAllWeather()).thenReturn(expectedWeatherList);
		List<Weather> actualWeatherList = target.getAllWeather();
		assertEquals(expectedWeatherList, actualWeatherList);
	}

	@Test
	public void getAllPositionsTest() {
		List<Position> expectedPositions = new ArrayList<Position>();
		expectedPositions.add(new Position());
		when(adminToolsService.getAllPositions()).thenReturn(expectedPositions);
		List<Position> actualPositions = target.getAllPositions();
		assertEquals(expectedPositions, actualPositions);
	}

	@Test
	public void getAllBodyPartsTest() {
		List<BodyPart> expectedBodyPartList = new ArrayList<BodyPart>();
		expectedBodyPartList.add(new BodyPart());
		when(adminToolsService.getAllBodyParts()).thenReturn(
				expectedBodyPartList);
		List<BodyPart> actualBodyPartList = target.getAllBodyParts();
		assertEquals(expectedBodyPartList, actualBodyPartList);
	}

	@Test
	public void getAllInjuryTypesTest() {
		List<InjuryType> expectedInjuryTypeList = new ArrayList<InjuryType>();
		when(adminToolsService.getAllInjuryTypes()).thenReturn(
				expectedInjuryTypeList);
		List<InjuryType> actualInjuryTypeList = target.getAllInjuryTypes();
		assertEquals(expectedInjuryTypeList, actualInjuryTypeList);
	}
	
	@Test
	public void getAllEmployeesTest(){
	    List<Employee> expectedEmployeeList = new ArrayList<Employee>();
        when(adminToolsService.getAllEmployees()).thenReturn(
                expectedEmployeeList);
        List<Employee> actualEmployeeList = target.getAllEmployees();
        assertEquals(expectedEmployeeList, actualEmployeeList);
	}
	
	@Test
	public void getEmployeeByIdTest(){
	    target.getEmployeeById(anyInt());
        verify(adminToolsService).getEmployeeById(anyInt());
	}
	
	@Test 
	public void getUserByEmployeeIdTest(){
	    target.getUserByEmployeeId(anyInt());
        verify(adminToolsService).getUserByEmployeeId(anyInt());
	}
	
	@Test
	public void getUsernameAvailability(){
	    target.getUserNameAvailability(2, "testusername");
        verify(adminToolsService).userNameAvailable(2, "testusername");
	}
	
	

	@Test
	public void postAllWeatherTest() {
		target.postAllWeather(VALID_WEATHER);
		verify(adminToolsService, times(1)).postWeather(VALID_WEATHER);
	}

	@Test
	public void postAllPositionsTest() {
		target.postAllPositions(VALID_POSITION);
		verify(adminToolsService, times(1)).postPosition(VALID_POSITION);
	}

	@Test
	public void postAllBodyPartsTest() {
		target.postAllBodyParts(VALID_BODYPART);
		verify(adminToolsService, times(1)).postBodyPart(VALID_BODYPART);
	}

	@Test
	public void postAllInjuryTypesTest() {
		target.postAllInjuryTypes(VALID_INJURY_TYPE);
		verify(adminToolsService, times(1)).postInjuryType(VALID_INJURY_TYPE);
	}
	
	@Test
	public void postEmployeeTest(){
	    target.postEmployee(anyString(), anyString(), anyInt());
        verify(adminToolsService, times(1)).postEmployee(anyString(), anyString(), anyInt());
	}
	
	@Test
	public void postUserTest(){
	    target.postUser(anyInt(), anyString(), anyString(), anyInt());
        verify(adminToolsService, times(1)).postUser(anyInt(), anyString(), anyString(), anyInt());
	}
	
	@Test
	public void putEmployee(){
	    target.putEmployee(anyInt(), anyString(), anyString(), anyInt());;
        verify(adminToolsService).putEmployee(anyInt(), anyString(), anyString(), anyInt());
	}

	@Test
	public void deleteWeatherTest() {
		target.deleteWeather(1);
		verify(adminToolsService, times(1)).deleteWeather(1);
	}

	@Test
	public void deletePositionTest() {
		target.deletePosition(1);
		verify(adminToolsService, times(1)).deletePosition(1);
	}

	@Test
	public void deleteBodyPartTest() {
		target.deleteBodyPart(1);
		verify(adminToolsService,times(1)).deleteBodyPart(1);

	}

	@Test
	public void deleteInjuryTypeTest() {
		target.deleteInjuryType(1);
		verify(adminToolsService, times(1)).deleteInjuryType(1);
	}
	
	@Test
	public void getFilteredEmployeesTest(){
	    target.getFilteredEmployees(anyString(), anyString(), anyInt(), anyInt());
        verify(adminToolsService).getFilteredEmployeeList(anyString(), anyString(), anyInt(), anyInt());
	}

}
