package com.catalyst.teammateria.injuryreport.formbeans.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;

@RunWith (MockitoJUnitRunner.class)
public class InjuryReportBeanTest {
    
    @InjectMocks
    private InjuryReportBean target;
    
    @Test
    public void employeeIdTest(){
        target.setEmployeeId(1);
        assertTrue(target.getEmployeeId() == 1);
    }
    
    @Test
    public void reporterIdTest(){
        target.setReporterId(1);
        assertTrue(target.getReporterId() == 1);
    }
    
    @Test
    public void weatherIdTest(){
        target.setWeatherId(1);
        assertTrue(target.getWeatherId() == 1);
    }
    
    @Test
    public void injuryTypeIdTest(){
        target.setInjuryTypeId(1);
        assertTrue(target.getInjuryTypeId() == 1);
    }
    
    @Test
    public void bodyPartIdTest(){
        target.setBodyPartId(1);
        assertTrue(target.getBodyPartId() == 1);
    }
    
    @Test
    public void reportDateTest(){
        target.setReportDate("2014-11-21");
        assertTrue(target.getReportDate() == "2014-11-21");
    }
    
    @Test
    public void reportManagementDateTest(){
        target.setReportManagementDate("2014-11-21");
        assertTrue(target.getReportManagementDate() == "2014-11-21");
    }
    
    @Test
    public void timeOfInjuyTest(){
        target.setTimeOfInjury("10:00");
        assertTrue(target.getTimeOfInjury() == "10:00");
    }
    
    @Test
    public void descriptionTest(){
        target.setDescription("Test");
        assertTrue(target.getDescription() == "Test");
    }
}
