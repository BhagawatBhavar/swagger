package com.te.ramayan.heroservice.heroimple;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.ramayan.heroentity.HeroDto;
import com.te.ramayan.heroentity.Heros;
import com.te.ramayan.herorepo.HeroRepoInterface;
import com.te.ramayan.heroservice.herointerface.HeroInterface;

import net.bytebuddy.implementation.bytecode.Throw;

@Service

public class HeroImple implements HeroInterface {

	@Autowired
	HeroRepoInterface heroRepoInterface;
  
	Heros heros;

	@Override
	public Heros addHero(Heros heros) {
		if (heros == null) {
			throw new RuntimeException("please give proper input");
		}
		return heroRepoInterface.save(heros);

	}

	@Override
	public Heros findName(String name) {
		Heros heros = heroRepoInterface.findByName(name).get();
		if (heros != null) {
			return heros;
		}

		throw new RuntimeException("plese give right info");

	}

	@Override
	public Optional<Heros> findpower(String power) {
		Optional<Heros> findByPower = heroRepoInterface.findByPower(power);

		return findByPower;
	}

	@Override
	public Heros findDesignation(String designation) {
		Optional<Heros> findByDesignation = heroRepoInterface.findByDesignation(designation);

		if (findByDesignation.isPresent()) {
			return findByDesignation.get();
		}
		throw new RuntimeException("plrase gives right info");
	}

	@Override
	public Heros findid(HeroDto heros) {
		Heros heros2 = heroRepoInterface.findById(heros.getId()).get();
	
		return heros2;
	}

	public Heros addHero(HeroDto heroDto) {
		heros.setDesignation(heroDto.getDesignation());
		heros.setGender(heroDto.getGender());
		heros.setId(heroDto.getId());
		heros.setKingdom(heroDto.getKingdom());
		heros.setName(heroDto.getName());
		heros.setPower(heroDto.getPower());

		return heroRepoInterface.save(heros);

	}

}
