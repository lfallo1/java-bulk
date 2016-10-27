package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.catalyst.teammateria.injuryreport.dao.PositionDao;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.PositionRowMapper;
import com.catalyst.teammateria.injuryreport.model.Position;

/**
 * JDBC implementation of the positionDao
 * 
 * @author dGrimes
 */
@Component
public class PositionDaoJdbc implements PositionDao {

    private static final String INSERT_POSITION = "INSERT INTO position (`position_name`) VALUES (?)";

    private static final String GET_POSITION_BY_ID = "SELECT p.position_id, p.position_name FROM position AS p WHERE p.position_id = ?";

    private static final String UPDATE_POSITION = "UPDATE position AS p SET p.position_name = ? WHERE p.position_id = ?";

    private static final String REMOVE_POSITION = "DELETE FROM position WHERE position_id = ?";

    private static final String GET_ALL_POSITIONS = "SELECT p.position_id, p.position_name FROM position AS p";

    private JdbcTemplate jdbcTemplate;

    /**
     * Sets the JdbcTemplate using the provided dataSource.
     * 
     * @param dataSource
     */
    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * For adding a JdbcTemplate object directly.
     * 
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPosition(Position newObject) {
        this.jdbcTemplate.update(INSERT_POSITION, newObject.getPositionName());
    }

    @Override
    public Position getPositionById(Integer key) {
        return this.jdbcTemplate.queryForObject(GET_POSITION_BY_ID,
                new Object[] {key}, new PositionRowMapper());
    }

    @Override
    public void updatePosition(Position newObject) {
        this.jdbcTemplate.update(UPDATE_POSITION, newObject.getPositionName(),
                newObject.getPositionId());
    }

    @Override
    public void removePosition(Position newObject) {
        this.jdbcTemplate.update(REMOVE_POSITION, newObject.getPositionId());
    }

    @Override
    public List<Position> getAll() {
        return this.jdbcTemplate.query(GET_ALL_POSITIONS,
                new PositionRowMapper());
    }
}
