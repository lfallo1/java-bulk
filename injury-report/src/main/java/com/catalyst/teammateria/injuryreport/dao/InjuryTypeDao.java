package com.catalyst.teammateria.injuryreport.dao;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.InjuryType;

/**
 * Injury Type interface for dao
 * 
 * @author dGrimes
 */
public interface InjuryTypeDao {

    /**
     * create new object in database
     * 
     * @param new InjuryType object
     */
    void addInjuryType(InjuryType newObject);

    /**
     * Read a InjuryType object from database accessed by ID
     * 
     * @param key
     * @return InjuryType
     */
    InjuryType getInjuryTypeById(Integer key);

    /**
     * Save changes made to a Injury Type object
     * 
     * @param InjuryType
     *            object
     */
    void updateInjuryType(InjuryType newObject);

    /**
     * Delete an Injury Type from database
     * 
     * @param InjuryType
     *            object
     */
    void removeInjuryType(InjuryType newObject);

    /**
     * Return a list of all Injury Types
     */
    List<InjuryType> getAll();
}
