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

import com.catalyst.teammateria.injuryreport.dao.jdbc.PositionDaoJdbc;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.PositionRowMapper;
import com.catalyst.teammateria.injuryreport.model.Position;

public class PositionDaoJdbcTest {

    PositionDaoJdbc target;
    JdbcTemplate    mockJdbcTemplate;
    DataSource      mockedDataSrc = Mockito.mock(DataSource.class);

    @Before
    public void setup() {
        target = new PositionDaoJdbc();
        this.mockJdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(mockedDataSrc);
        target.setJdbcTemplate(mockJdbcTemplate);
    }

    @Ignore
    public Position defaultPosition() {
        Position position = new Position();
        position.setPositionId(1);
        position.setPositionName("Management");
        return position;
    }

    @Test
    public void addPositionTest() {
        Position position = defaultPosition();
        target.addPosition(position);
        verify(mockJdbcTemplate).update(anyString(), eq("Management"));
    }

    @Test
    public void getPositionByIdTest() {
        Position expectedPosition = defaultPosition();
        Mockito.when(
                mockJdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(PositionRowMapper.class))).thenReturn(
                expectedPosition);
        Position actualPosition = target.getPositionById(1);
        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void updatePositionTest() {
        Position position = defaultPosition();
        target.updatePosition(position);
        verify(mockJdbcTemplate).update(anyString(), eq("Management"), eq(1));
    }

    @Test
    public void removePositionTest() {
        Position position = defaultPosition();
        target.removePosition(position);
        verify(mockJdbcTemplate).update(anyString(), eq(1));
    }

    @Test
    public void getAllTest() {
        List<Position> expectedList = new ArrayList<Position>();
        Mockito.when(
                mockJdbcTemplate.query(anyString(),
                        any(PositionRowMapper.class))).thenReturn(
                expectedList);
        List<Position> actualList = target.getAll();
        assertEquals(actualList, expectedList);
    }
}
