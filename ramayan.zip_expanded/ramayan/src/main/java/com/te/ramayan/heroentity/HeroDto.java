package com.te.ramayan.heroentity;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class HeroDto {
	@Id
	private int id;
	private String name;
	private String power;
	private String designation;
	private String kingdom;
	private String gender;
}
