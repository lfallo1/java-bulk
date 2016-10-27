package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.catalyst.teammateria.injuryreport.dao.InjuryTypeDao;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.InjuryTypeRowMapper;
import com.catalyst.teammateria.injuryreport.model.InjuryType;

/**
 * JDBC implementation of the injury type dao
 * 
 * @author dGrimes
 */
@Component
public class InjuryTypeDaoJdbc implements InjuryTypeDao {

    private static final String INSERT_INJURY_TYPE = "INSERT INTO injurytype (`type_name`) VALUES (?)";

    private static final String GET_INJURY_TYPE_BY_ID = "SELECT i.type_id, i.type_name FROM injurytype AS i WHERE i.type_id = ?";

    private static final String UPDATE_INJURY_TYPE = "UPDATE injurytype AS i SET i.type_name = ? WHERE i.type_id = ?";

    private static final String REMOVE_INJURY_TYPE = "DELETE FROM injurytype WHERE type_id = ?";

    private static final String GET_ALL_INJURY_TYPES = "SELECT i.type_id, i.type_name FROM injurytype AS i";

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
    public void addInjuryType(InjuryType newObject) {
        this.jdbcTemplate.update(INSERT_INJURY_TYPE, newObject.getTypeName());
    }

    @Override
    public InjuryType getInjuryTypeById(Integer key) {
        return this.jdbcTemplate.queryForObject(GET_INJURY_TYPE_BY_ID,
                new Object[] {key}, new InjuryTypeRowMapper());
    }

    @Override
    public void updateInjuryType(InjuryType newObject) {
        this.jdbcTemplate.update(UPDATE_INJURY_TYPE, newObject.getTypeName(),
                newObject.getTypeId());
    }

    @Override
    public void removeInjuryType(InjuryType newObject) {
        this.jdbcTemplate.update(REMOVE_INJURY_TYPE, newObject.getTypeId());
    }

    @Override
    public List<InjuryType> getAll() {
        return this.jdbcTemplate.query(GET_ALL_INJURY_TYPES,
                new InjuryTypeRowMapper());
    }
}
