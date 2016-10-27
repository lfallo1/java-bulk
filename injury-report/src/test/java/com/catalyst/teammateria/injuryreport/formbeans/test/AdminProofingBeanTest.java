package com.catalyst.teammateria.injuryreport.formbeans.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;

@RunWith (MockitoJUnitRunner.class)
public class AdminProofingBeanTest {
    
    @InjectMocks
    private AdminProofingBean target;
    
    @Test
    public void dateReportedToManagementTest(){
        target.setDateReportedToManagement("2014-11-25");;
        assertEquals(target.getDateReportedToManagement(), "2014-11-25");
    }
    
    @Test
    public void dateOfReportTest(){
        target.setDateOfReport("2014-11-25");
        assertEquals(target.getDateOfReport(), "2014-11-25");
    }
    
    @Test
    public void weatherIdTest(){
        target.setWeatherId(1);
        assertTrue(target.getWeatherId() == 1);
    }
    
    @Test
    public void typeIdTest(){
        target.setTypeId(1);
        assertTrue(target.getTypeId() == 1);
    }
    
    @Test
    public void bodyPartIdTest(){
        target.setBodyPartId(1);
        assertTrue(target.getBodyPartId() == 1);
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
    
    @Test
    public void approverCommentsTest(){
        target.setApproverComments("Test");
        assertTrue(target.getApproverComments() == "Test");
    }
}
