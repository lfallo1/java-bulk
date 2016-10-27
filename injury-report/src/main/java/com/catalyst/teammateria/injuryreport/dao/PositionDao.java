package com.catalyst.teammateria.injuryreport.dao;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.Position;

/**
 * Position Dao Interface
 * 
 * @author dGrimes
 */
public interface PositionDao {

    /**
     * create new object in database
     * 
     * @param new Position object
     */
    void addPosition(Position newObject);

    /**
     * Read a Position object from database accessed by ID
     * 
     * @param key
     * @return Position
     */
    Position getPositionById(Integer key);

    /**
     * Save changes made to a Position object
     * 
     * @param PositionDao
     *            object
     */
    void updatePosition(Position newObject);

    /**
     * Delete an Position from database
     * 
     * @param PositionDao
     *            object
     */
    void removePosition(Position newObject);

    /**
     * Return a list of all Positions
     */
    List<Position> getAll();
}
