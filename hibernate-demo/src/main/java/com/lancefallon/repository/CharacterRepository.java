package com.lancefallon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lancefallon.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
	
	@Query(value="Select c from Character c where alias = :alias")
	Character findByAlias(@Param(value = "alias") String alias);
}
