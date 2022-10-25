package com.te.ramayan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.ramayan.heroentity.HeroDto;
import com.te.ramayan.heroentity.Heros;
import com.te.ramayan.heroservice.heroimple.HeroImple;
import com.te.ramayan.heroservice.herointerface.HeroInterface;

@RestController
public class HeroController {
   @Autowired
   HeroImple heroImple;
	
   @PostMapping("/hero")
	public Heros addHeros( @RequestBody HeroDto heros) {
		return heroImple.addHero(heros);
		
	}
   @GetMapping("/info")
   public Heros findName(@RequestParam String name) {
	   return heroImple.findName(name);
   }
   @GetMapping("/power")
   public Optional<Heros> findpower(String power){
	   return heroImple.findpower(power);
   }
   @GetMapping("/designation")
   public Heros findDesignation(@RequestParam String designation) {
	   return heroImple.findDesignation(designation);
   }
   
}
