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

import com.catalyst.teammateria.injuryreport.dao.jdbc.InjuryTypeDaoJdbc;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.InjuryTypeRowMapper;
import com.catalyst.teammateria.injuryreport.model.InjuryType;

public class InjuryTypeDaoJdbcTest {

    private static final int    INJURY_TYPE_ID_VALID   = 1;
    private static final String INJURY_TYPE_NAME_VALID = "foo";
    InjuryTypeDaoJdbc           target;
    JdbcTemplate                mockJdbcTemplate;
    DataSource                  mockedDataSrc          = Mockito
                                                               .mock(DataSource.class);

    @Before
    public void setup() {
        target = new InjuryTypeDaoJdbc();
        this.mockJdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(mockedDataSrc);
        target.setJdbcTemplate(mockJdbcTemplate);
    }

    @Test
    public void addInjuryTypeTest() {
        InjuryType injuryType = validInjuryType();
        target.addInjuryType(injuryType);
        verify(mockJdbcTemplate)
                .update(anyString(), eq(INJURY_TYPE_NAME_VALID));
    }

    @Test
    public void getInjuryTypeByIdTest() {
        InjuryType expectedInjuryType = validInjuryType();
        Mockito.when(
                mockJdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(InjuryTypeRowMapper.class))).thenReturn(
                expectedInjuryType);
        InjuryType actualInjuryType = target.getInjuryTypeById(1);
        assertEquals(expectedInjuryType, actualInjuryType);
    }

    @Test
    public void updateInjuryTypeTest() {
        InjuryType injuryType = validInjuryType();
        target.updateInjuryType(injuryType);
        verify(mockJdbcTemplate).update(anyString(),
                eq(INJURY_TYPE_NAME_VALID), eq(INJURY_TYPE_ID_VALID));
    }

    @Test
    public void removeInjuryTypeTest() {
        InjuryType injuryType = validInjuryType();
        target.removeInjuryType(injuryType);
        verify(mockJdbcTemplate).update(anyString(), eq(INJURY_TYPE_ID_VALID));
    }

    @Test
    public void getAllTest() {
        List<InjuryType> expectedList = new ArrayList<InjuryType>();
        Mockito.when(
                mockJdbcTemplate.query(anyString(),
                        any(InjuryTypeRowMapper.class))).thenReturn(
                expectedList);
        List<InjuryType> actualList = target.getAll();
        assertEquals(expectedList, actualList);
    }

    @Ignore
    private InjuryType validInjuryType() {
        int typeId = INJURY_TYPE_ID_VALID;
        String typeName = INJURY_TYPE_NAME_VALID;
        InjuryType injuryType = new InjuryType();
        injuryType.setTypeId(typeId);
        injuryType.setTypeName(typeName);
        return injuryType;
    }
}
