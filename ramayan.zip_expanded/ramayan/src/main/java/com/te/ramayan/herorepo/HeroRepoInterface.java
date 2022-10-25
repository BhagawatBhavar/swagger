package com.te.ramayan.herorepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.ramayan.heroentity.Heros;
@Repository
public interface HeroRepoInterface extends JpaRepository<Heros, Integer>{
  
	Optional<Heros> findByName(String name);
	Optional<Heros> findByPower(String power);
	Optional<Heros> findByDesignation(String designation);
}
