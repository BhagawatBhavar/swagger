package com.te.ramayan.heroentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Heros {

public Heros(int i) {
		// TODO Auto-generated constructor stub
	}
	//	public Heros( int i, String string, String string2, String string3, String string4) {
//	// TODO Auto-generated constructor stub
//	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String power;
	private String designation;
	private String kingdom;
	private String gender;
	
	
}
