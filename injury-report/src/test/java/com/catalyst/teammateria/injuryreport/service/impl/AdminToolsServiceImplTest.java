package com.catalyst.teammateria.injuryreport.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.dao.BodyPartDao;
import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.InjuryTypeDao;
import com.catalyst.teammateria.injuryreport.dao.PositionDao;
import com.catalyst.teammateria.injuryreport.dao.ReportDao;
import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.dao.WeatherDao;
import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.Role;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;

@RunWith (MockitoJUnitRunner.class)
public class AdminToolsServiceImplTest {

    private static final String   NUMBER_STRING_123        = "123";
    private static final String   SEARCHABLE_STRING_FOO    = "%foo%";
    private static final String   SEARCHABLE_EMPTY         = "%";
    private static final String   SEARCHABLE_NUMBER_123    = "%123%";
    private static final String   LETTER_STRING_2          = "bar";
    private static final String   LETTER_STRING_1          = "foo";
    private static final String   NUMBER_AND_LETTER_STRING = "foo123";
    private static final String   VALID_NAME               = "Test";
    private static final String   NEW_NAME                 = "New";
    private static final String   TAKEN_NAME               = "Taken";
    @InjectMocks
    private AdminToolsServiceImpl target;
    @Mock
    BodyPartDao                   bodyPartDao;
    @Mock
    InjuryTypeDao                 injuryTypeDao;
    @Mock
    WeatherDao                    weatherDao;
    @Mock
    EmployeeDao                   employeeDao;
    @Mock
    ReportDao                     reportDao;
    @Mock
    UserDao                       userDao;
    @Mock
    PositionDao                   positionDao;

    @Test
    public void getAllWeatherTest() {
        List<Weather> expectedList = new ArrayList<Weather>();
        Mockito.when(weatherDao.getAll()).thenReturn(expectedList);
        List<Weather> actualList = target.getAllWeather();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void getAllPositionsTest() {
        List<Position> expectedList = new ArrayList<Position>();
        Mockito.when(positionDao.getAll()).thenReturn(expectedList);
        List<Position> actualList = target.getAllPositions();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void getAllBodyPartsTest() {
        List<BodyPart> expectedList = new ArrayList<BodyPart>();
        Mockito.when(bodyPartDao.getAll()).thenReturn(expectedList);
        List<BodyPart> actualList = target.getAllBodyParts();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void getAllInjuryTypesTest() {
        List<InjuryType> expectedList = new ArrayList<InjuryType>();
        Mockito.when(injuryTypeDao.getAll()).thenReturn(expectedList);
        List<InjuryType> actualList = target.getAllInjuryTypes();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void getAllEmployeesTest() {
        List<Employee> expectedList = new ArrayList<Employee>();
        Mockito.when(employeeDao.getAll()).thenReturn(expectedList);
        List<Employee> actualList = target.getAllEmployees();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void makeSafeTest() {
        String test = "string 9";
        String result = target.makeSafe(test);
        assertEquals(test, result);
    }

    @Test
    public void postWeatherTest() {
        Weather weather = new Weather();
        target.postWeather("");
        verify(weatherDao).addWeather(weather);
    }

    @Test
    public void postPositionTest() {
        Position position = new Position();
        target.postPosition("");
        verify(positionDao).addPosition(position);
    }

    @Test
    public void postBodyPartTest() {
        BodyPart bodyPart = new BodyPart();
        target.postBodyPart("");
        verify(bodyPartDao).addBodyPart(bodyPart);
    }

    @Test
    public void postInjuryTest() {
        InjuryType injury = new InjuryType();
        target.postInjuryType("");
        verify(injuryTypeDao).addInjuryType(injury);
    }

    @Test
    public void deleteWeatherTest() {
        Weather weather = defaultWeather();
        target.deleteWeather(weather.getWeatherId());
        verify(weatherDao).removeWeather(weather);
    }

    @Test
    public void deletePositionTest() {
        Position position = defaultPosition();
        target.deletePosition(position.getPositionId());
        verify(positionDao).removePosition(position);
    }

    @Test
    public void deleteBodyPartTest() {
        BodyPart bodyPart = defaultBodyPart();
        target.deleteBodyPart(bodyPart.getBodyPartId());
        verify(bodyPartDao).removeBodyPart(bodyPart);
    }

    @Test
    public void deleteInjuryTypeTest() {
        InjuryType injuryType = defaultInjuryType();
        target.deleteInjuryType(injuryType.getTypeId());
        verify(injuryTypeDao).removeInjuryType(injuryType);
    }

    @Ignore
    public InjuryType defaultInjuryType() {
        InjuryType injuryType = new InjuryType();
        injuryType.setTypeId(1);
        injuryType.setTypeName("");
        return injuryType;
    }

    @Ignore
    public BodyPart defaultBodyPart() {
        BodyPart bodyPart = new BodyPart();
        bodyPart.setBodyPartId(1);
        bodyPart.setBodyPartName("");
        return bodyPart;
    }

    @Ignore
    public Weather defaultWeather() {
        Weather weather = new Weather();
        weather.setWeatherId(1);
        weather.setWeatherCondition("");
        return weather;
    }

    @Ignore
    public Position defaultPosition() {
        Position position = new Position();
        position.setPositionId(1);
        position.setPositionName("");
        return position;
    }

    @Test
    public void postEmployeeVerificationTest() {
        Employee employee = new Employee();
        employee.setFirstName(VALID_NAME);
        employee.setLastName(VALID_NAME);
        Position position = new Position();
        position.setPositionId(0);
        employee.setPosition(position);
        target.postEmployee("", "", 0);
        target.postEmployee(VALID_NAME, "", 0);
        target.postEmployee("", VALID_NAME, 0);
        target.postEmployee(VALID_NAME, VALID_NAME, 0);
        verify(employeeDao).addEmployee(employee);
    }

    @Test
    public void putEmployeeVerificationTest() {
        Employee employee = new Employee();
        employee.setEmployeeId(0);
        employee.setFirstName(VALID_NAME);
        employee.setLastName(VALID_NAME);
        Position position = new Position();
        position.setPositionId(0);
        employee.setPosition(position);
        target.putEmployee(0, "", "", 0);
        target.putEmployee(0, VALID_NAME, "", 0);
        target.putEmployee(0, "", VALID_NAME, 0);
        target.putEmployee(0, VALID_NAME, VALID_NAME, 0);
        verify(employeeDao).updateEmployee(employee);
    }

    @Test
    public void usernameAvailableTest() {
        User newUser = new User();
        newUser.setUserName(VALID_NAME);
        when(userDao.isAlreadyAUser(0)).thenReturn(true);
        when(userDao.isAlreadyAUser(1)).thenReturn(false);
        when(userDao.getUserByEmployeeId(0)).thenReturn(newUser);
        when(userDao.userNameTaken(NEW_NAME)).thenReturn(false);
        when(userDao.userNameTaken(TAKEN_NAME)).thenReturn(true);
        // Is a user with original name
        assertTrue(target.userNameAvailable(0, VALID_NAME));
        // Is a user with a taken name
        assertFalse(target.userNameAvailable(0, TAKEN_NAME));
        // Is not a user with a new name
        assertTrue(target.userNameAvailable(1, NEW_NAME));
        // Is not a user with an old name
        assertFalse(target.userNameAvailable(1, TAKEN_NAME));
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        when(employeeDao.getEmployeeById(0)).thenReturn(employee);
        assertEquals(employee, target.getEmployeeById(0));
    }

    @Test
    public void getUserByEmployeeIdTest() {
        User expectedUser = new User();
        expectedUser.setUserId(1);
        when(userDao.getUserByEmployeeId(0)).thenReturn(expectedUser);
        when(userDao.isAlreadyAUser(0)).thenReturn(true);
        when(userDao.isAlreadyAUser(1)).thenReturn(false);
        assertEquals(target.getUserByEmployeeId(0), expectedUser);
        assertEquals(target.getUserByEmployeeId(1), new User());
    }

    @Test
    public void postUserInvalidUserTest() {
        // invalid username should never be added to the database
        target.postUser(1, "foo1?>", LETTER_STRING_2, 1);
        verify(userDao, never()).addUser(any(User.class));
    }

    @Test
    public void postUserValidNewUserTest() {
        // When adding a valid username
        when(userDao.isAlreadyAUser(1)).thenReturn(false);
        target.postUser(1, "foo1", LETTER_STRING_2, 1);
        verify(userDao).addUser(any(User.class));
    }

    @Test
    public void postUserValidNewUserTest2() {
        // When adding a valid username, but existing username
        when(userDao.isAlreadyAUser(1)).thenReturn(false);
        when(userDao.userNameTaken("foo1")).thenReturn(true);
        target.postUser(1, "foo1", LETTER_STRING_2, 1);
        // Verify
        verify(userDao, never()).addUser(any(User.class));
    }

    // Post an already existing user with a changed username that collides with
    // an existing username
    @Test
    public void postUserValidCurrentUserTest() {
        // Valid and unique userid
        when(userDao.isAlreadyAUser(1)).thenReturn(true);
        // And a valid but already used username
        User expectedUser = new User();
        expectedUser.setUserName(LETTER_STRING_1);
        when(userDao.userNameTaken("foo1")).thenReturn(true);
        when(userDao.getUserByEmployeeId(1)).thenReturn(expectedUser);
        target.postUser(1, "foo1", LETTER_STRING_2, 1);
        verify(userDao, never()).addUser(any(User.class));
    }

    @Test
    public void postUserValidCurrentUserTest3() {
        // Valid and unique userid
        when(userDao.isAlreadyAUser(1)).thenReturn(true);
        // And a valid but already used username
        User expectedUser = new User();
        Role expectedRole = new Role();
        expectedRole.setRoleId(1);
        expectedUser.setRole(expectedRole);
        expectedUser.setUserName(LETTER_STRING_1);
        when(userDao.userNameTaken(LETTER_STRING_1)).thenReturn(true);
        when(userDao.getUserByEmployeeId(1)).thenReturn(expectedUser);
        target.postUser(1, LETTER_STRING_1, LETTER_STRING_2, 1);
        verify(userDao).updateUser(any(User.class));
    }

    @Test
    public void postUserValidCurrentUserTest2() {
        // Valid and unique userid
        when(userDao.isAlreadyAUser(1)).thenReturn(true);
        // And a valid but already used username
        User expectedUser = new User();
        expectedUser.setUserName(LETTER_STRING_1);
        Role expectedRole = new Role();
        expectedRole.setRoleId(1);
        expectedUser.setRole(expectedRole);
        when(userDao.userNameTaken("foo1")).thenReturn(false);
        when(userDao.getUserByEmployeeId(1)).thenReturn(expectedUser);
        target.postUser(1, "foo1", LETTER_STRING_2, 1);
    }

    @Test
    public void getFilteredEmployeeListTest() {
        List<Employee> expectedList = new ArrayList<Employee>();
        expectedList.add(new Employee());
        when(
                employeeDao.getAdminToolsEmployeeListWithFilters(anyString(),
                        anyString(), anyString(), anyString())).thenReturn(
                expectedList);
        List<Employee> actualList = target.getFilteredEmployeeList(
                LETTER_STRING_1, LETTER_STRING_2, 1, 2);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void filteredEmployeeListNullTest() {
        target.getFilteredEmployeeList("x", "x", null, 0);
        Mockito.verify(employeeDao, times(1))
                .getAdminToolsEmployeeListWithFilters("%x%", "%x%", "%", "%0%");
        target.getFilteredEmployeeList("x", "x", 0, null);
        Mockito.verify(employeeDao, times(1))
                .getAdminToolsEmployeeListWithFilters("%x%", "%x%", "%0%", "%");
    }

    @Test
    public void makeNumberSafeTest() {
        String safeStringLettersOnly = target.makeNumberSafe(LETTER_STRING_1);
        assertEquals(SEARCHABLE_EMPTY, safeStringLettersOnly);
        String safeStringNumbersAndLetters = target
                .makeNumberSafe(NUMBER_AND_LETTER_STRING);
        assertEquals(SEARCHABLE_NUMBER_123, safeStringNumbersAndLetters);
        String safeStringNumbersOnly = target.makeNumberSafe(NUMBER_STRING_123);
        assertEquals(SEARCHABLE_NUMBER_123, safeStringNumbersOnly);
    }

    @Test
    public void makeNameSafe() {
        String numbersOnly = target.makeNameSafe(NUMBER_STRING_123);
        assertEquals(SEARCHABLE_EMPTY, numbersOnly);
        String numbersAndLetters = target
                .makeNameSafe(NUMBER_AND_LETTER_STRING);
        assertEquals(SEARCHABLE_STRING_FOO, numbersAndLetters);
        String lettersOnly = target.makeNameSafe(LETTER_STRING_1);
        assertEquals(SEARCHABLE_STRING_FOO, lettersOnly);
    }
}
