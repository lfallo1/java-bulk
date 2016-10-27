package com.catalyst.teammateria.injuryreport.service.impl;

import static com.catalyst.teammateria.injuryreport.utils.ValidationUtil.isValidName;
import static com.catalyst.teammateria.injuryreport.utils.ValidationUtil.isValidUsername;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.injuryreport.dao.BodyPartDao;
import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.InjuryTypeDao;
import com.catalyst.teammateria.injuryreport.dao.PositionDao;
import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.dao.WeatherDao;
import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.Role;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;
import com.catalyst.teammateria.injuryreport.service.AdminToolsService;
import com.catalyst.teammateria.injuryreport.utils.PasswordUtil;

/**
 * Implementation of the Admin Tools Service.
 * 
 * @author dGrimes
 */
@Service
public class AdminToolsServiceImpl implements AdminToolsService {

    private WeatherDao weatherDao;

    private PositionDao positionDao;

    private BodyPartDao bodyPartDao;

    private InjuryTypeDao injuryTypeDao;

    private EmployeeDao employeeDao;

    private UserDao userDao;

    @Autowired
    public void setWeatherDao(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    @Autowired
    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Autowired
    public void setBodyPartDao(BodyPartDao bodyPartDao) {
        this.bodyPartDao = bodyPartDao;
    }

    @Autowired
    public void setInjuryTypeDao(InjuryTypeDao injuryTypeDao) {
        this.injuryTypeDao = injuryTypeDao;
    }

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Weather> getAllWeather() {
        return weatherDao.getAll();
    }

    @Override
    public List<Position> getAllPositions() {
        return positionDao.getAll();
    }

    @Override
    public List<BodyPart> getAllBodyParts() {
        return bodyPartDao.getAll();
    }

    @Override
    public List<InjuryType> getAllInjuryTypes() {
        return injuryTypeDao.getAll();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public User getUserByEmployeeId(int id) {
        if (userDao.isAlreadyAUser(id)) {
            return userDao.getUserByEmployeeId(id);
        }
        return new User();
    }

    @Override
    public void postWeather(String name) {
        Weather weather = new Weather();
        weather.setWeatherCondition(makeSafe(name));
        weatherDao.addWeather(weather);
    }

    @Override
    public void postPosition(String name) {
        Position position = new Position();
        position.setPositionName(makeSafe(name));
        positionDao.addPosition(position);
    }

    @Override
    public void postBodyPart(String name) {
        BodyPart bodyPart = new BodyPart();
        bodyPart.setBodyPartName(makeSafe(name));
        bodyPartDao.addBodyPart(bodyPart);
    }

    @Override
    public void postInjuryType(String name) {
        InjuryType injuryType = new InjuryType();
        injuryType.setTypeName(makeSafe(name));
        injuryTypeDao.addInjuryType(injuryType);
    }

    @Override
    public void postEmployee(String firstName, String lastName, int positionId) {
        if (isValidName(firstName) && isValidName(lastName)) {
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            Position position = new Position();
            position.setPositionId(positionId);
            employee.setPosition(position);
            employeeDao.addEmployee(employee);
        }
    }

    @Override
    public void postUser(int employeeId, String username, String password,
            int roleId) {
        if (isValidUsername(username)) {
            if (userDao.isAlreadyAUser(employeeId)) {
                User user = userDao.getUserByEmployeeId(employeeId);
                if ( !user.getUserName().equals(username)
                        && userDao.userNameTaken(username)) {
                    return;
                }
                user.setUserName(username);
                user.setUserPass(PasswordUtil.encodePassword(password));
                user.getRole().setRoleId(roleId);
                userDao.updateUser(user);
            } else {
                if (userDao.userNameTaken(username)) {
                    return;
                }
                User user = new User();
                user.setUserName(username);
                user.setUserPass(PasswordUtil.encodePassword(password));
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                user.setEmployee(employee);
                Role role = new Role();
                role.setRoleId(roleId);
                user.setRole(role);
                user.setActive(true);
                userDao.addUser(user);
            }
        }
    }

    @Override
    public boolean userNameAvailable(int id, String username) {
        if (userDao.isAlreadyAUser(id)
                && userDao.getUserByEmployeeId(id).getUserName()
                        .equals(username)) {
            return true;
        }
        return !userDao.userNameTaken(username);
    }

    @Override
    public void putEmployee(int employeeId, String firstName, String lastName,
            int positionId) {
        if (isValidName(firstName) && isValidName(lastName)) {
            Employee employee = new Employee();
            employee.setEmployeeId(employeeId);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            Position position = new Position();
            position.setPositionId(positionId);
            employee.setPosition(position);
            employeeDao.updateEmployee(employee);
        }
    }

    @Override
    public void deleteWeather(int id) {
        Weather weather = new Weather();
        weather.setWeatherId(id);
        weatherDao.removeWeather(weather);
    }

    @Override
    public void deletePosition(int id) {
        Position position = new Position();
        position.setPositionId(id);
        positionDao.removePosition(position);
    }

    @Override
    public void deleteBodyPart(int id) {
        BodyPart bodyPart = new BodyPart();
        bodyPart.setBodyPartId(id);
        bodyPartDao.removeBodyPart(bodyPart);
    }

    @Override
    public void deleteInjuryType(int id) {
        InjuryType injuryType = new InjuryType();
        injuryType.setTypeId(id);
        injuryTypeDao.removeInjuryType(injuryType);
    }

    @Override
    public String makeSafe(String str) {
        str.replaceAll("\\s", " ");
        str.replaceAll("[^ a-zA-Z0-9/:]", "");
        str.trim();
        return str;
    }

    @Override
    public List<Employee> getFilteredEmployeeList(String filtFirstName,
            String filtLastName, Integer filtEmployeeId, Integer filtPositionId) {
        return employeeDao.getAdminToolsEmployeeListWithFilters(
                makeNameSafe(filtFirstName),
                makeNameSafe(filtLastName),
                makeNumberSafe(filtEmployeeId == null ? "%" : filtEmployeeId
                        .toString()),
                makeNumberSafe(filtPositionId == null ? "%" : filtPositionId
                        .toString()));
    }

    @Override
    public String makeNumberSafe(String number) {
        String newNumber = number.replaceAll("[^0-9]", "");
        return newNumber.equals("") ? "%" : '%' + newNumber + '%';
    }

    @Override
    public String makeNameSafe(String name) {
        String newName = name.replaceAll("[^A-Za-z]", "");
        return newName.equals("") ? "%" : '%' + newName + '%';
    }
}
