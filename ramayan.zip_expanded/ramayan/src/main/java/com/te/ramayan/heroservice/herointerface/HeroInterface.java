package com.te.ramayan.heroservice.herointerface;

import java.util.Optional;

import com.te.ramayan.heroentity.HeroDto;
import com.te.ramayan.heroentity.Heros;

public interface HeroInterface {

	public Heros addHero(Heros heros);
	public Heros findName(String name);
	public Optional<Heros> findpower(String power);
	public Heros findDesignation(String designation);
	

	Heros findid(HeroDto heros);
}
