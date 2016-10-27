package com.catalyst.teammateria.injuryreport.dao;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.BodyPart;

/**
 * Body Part Dao Interface
 * 
 * @author dGrimes
 */
public interface BodyPartDao {

    /**
     * create new object in database
     * 
     * @param new BodyPart object
     */
    void addBodyPart(BodyPart newObject);

    /**
     * Read a BodyPart object from database accessed by ID
     * 
     * @param key
     * @return BodyPart
     */
    BodyPart getBodyPartById(Integer key);

    /**
     * Save changes made to a BodyPart object
     * 
     * @param BodyPart
     *            object
     */
    void updateBodyPart(BodyPart newObject);

    /**
     * Delete an BodyPart from database
     * 
     * @param BodyPart
     *            object
     */
    void removeBodyPart(BodyPart newObject);

    /**
     * Return a list of all BodyParts
     */
    List<BodyPart> getAll();
}
