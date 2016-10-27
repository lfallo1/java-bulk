package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.catalyst.teammateria.injuryreport.dao.BodyPartDao;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.BodyPartRowMapper;
import com.catalyst.teammateria.injuryreport.model.BodyPart;

/**
 * JDBC Dao implementation
 * 
 * @author dGrimes
 */
@Component
public class BodyPartDaoJdbc implements BodyPartDao {

    private static final String INSERT_BODY_PART = "INSERT INTO bodypart (`bodypart_name`) VALUES (?)";

    private static final String GET_BODY_PART_BY_ID = "SELECT b.bodypart_id, b.bodypart_name FROM bodypart AS b WHERE b.bodypart_id = ?";

    private static final String UPDATE_BODY_PART = "UPDATE bodypart AS b SET b.bodypart_name = ? WHERE b.bodypart_id = ?";

    private static final String REMOVE_BODY_PART = "DELETE FROM bodypart WHERE bodypart_id = ?";

    private static final String GET_ALL_BODY_PARTS = "SELECT b.bodypart_id, b.bodypart_name FROM bodypart AS b";

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
    public void addBodyPart(BodyPart newObject) {
        this.jdbcTemplate.update(INSERT_BODY_PART, newObject.getBodyPartName());
    }

    @Override
    public BodyPart getBodyPartById(Integer key) {
        return this.jdbcTemplate.queryForObject(GET_BODY_PART_BY_ID,
                new Object[] {key}, new BodyPartRowMapper());
    }

    @Override
    public void updateBodyPart(BodyPart newObject) {
        this.jdbcTemplate.update(UPDATE_BODY_PART, newObject.getBodyPartName(),
                newObject.getBodyPartId());
    }

    @Override
    public void removeBodyPart(BodyPart newObject) {
        this.jdbcTemplate.update(REMOVE_BODY_PART, newObject.getBodyPartId());
    }

    @Override
    public List<BodyPart> getAll() {
        return this.jdbcTemplate.query(GET_ALL_BODY_PARTS,
                new BodyPartRowMapper());
    }
}
