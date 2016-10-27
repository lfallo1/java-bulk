package com.catalyst.teammateria.injuryreport.dao.jdbc.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import com.catalyst.teammateria.injuryreport.dao.jdbc.BodyPartDaoJdbc;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.BodyPartRowMapper;
import com.catalyst.teammateria.injuryreport.model.BodyPart;

public class BodyPartDaoJdbcTest {

    BodyPartDaoJdbc target;
    JdbcTemplate    mockJdbcTemplate;
    DataSource      mockedDataSrc = Mockito.mock(DataSource.class);

    @Before
    public void setup() {
        target = new BodyPartDaoJdbc();
        this.mockJdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(mockJdbcTemplate);
    }

    @Ignore
    public BodyPart defaultBodyPart() {
        BodyPart bodyPart = new BodyPart();
        bodyPart.setBodyPartId(1);
        bodyPart.setBodyPartName("Head");
        return bodyPart;
    }

    @Test
    public void coverDataSource() {
        target.setJdbcTemplate(mockedDataSrc);
    }

    @Test
    public void addBodyPartTest() {
        BodyPart bodyPart = defaultBodyPart();
        target.addBodyPart(bodyPart);
        verify(mockJdbcTemplate).update(anyString(), eq("Head"));
    }

    @Test
    public void getBodyTypeByIdTest() {
        BodyPart expectedBodyPart = defaultBodyPart();
        Mockito.when(
                mockJdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(BodyPartRowMapper.class))).thenReturn(
                expectedBodyPart);
        BodyPart actualBodyPart = target.getBodyPartById(1);
        assertEquals(expectedBodyPart, actualBodyPart);
    }

    @Test
    public void updateBodyPartTest() {
        BodyPart bodyPart = defaultBodyPart();
        target.updateBodyPart(bodyPart);
        verify(mockJdbcTemplate).update(anyString(), eq("Head"), eq(1));
    }

    @Test
    public void removeBodyPartTest() {
        BodyPart bodyPart = defaultBodyPart();
        target.removeBodyPart(bodyPart);
        verify(mockJdbcTemplate).update(anyString(), eq(1));
    }

    @Test
    public void getAllTest() {
        List<BodyPart> expectedList = new ArrayList<BodyPart>();
        Mockito.when(
                mockJdbcTemplate.query(anyString(),
                        any(BodyPartRowMapper.class))).thenReturn(
                expectedList);
        List<BodyPart> actualList = target.getAll();
        assertEquals(actualList, expectedList);
    }
}
