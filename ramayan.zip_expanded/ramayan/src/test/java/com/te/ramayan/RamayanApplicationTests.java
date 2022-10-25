package com.te.ramayan;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.maven.model.building.Result;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.beans.HasProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.jayway.jsonpath.JsonPath;
import com.te.ramayan.controller.HeroController;
import com.te.ramayan.herocontrolleradviser.HeroControllerAdviser;
import com.te.ramayan.heroentity.HeroDto;
import com.te.ramayan.heroentity.Heros;
import com.te.ramayan.herorepo.HeroRepoInterface;
import com.te.ramayan.heroservice.heroimple.HeroImple;

import io.smallrye.common.constraint.Assert;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class RamayanApplicationTests {
	@InjectMocks
	HeroImple heroImple;
	@Autowired
	WebApplicationContext appl;
	@Mock
	HeroRepoInterface heroRepoInterface;

	@InjectMocks
	HeroController heroController;

	@InjectMocks
	HeroControllerAdviser heroControllerAdviser;

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void dtoTester() {
		Heros hero = new Heros(1, "hanuman", "sa", "ayodhya", "male", "king");
		StandaloneMockMvcBuilder mockMvc = MockMvcBuilders.standaloneSetup(hero);

		HeroDto heroDto = new HeroDto(1, "hanuman", "sa", "ayodhya", "male", "king");
		when(heroRepoInterface.save(Mockito.any())).thenReturn(hero);
		assertEquals(heroImple.addHero(heroDto), hero);

	}

	@Test
	public void findIdDto() {
		Heros hero = new Heros(1, "hanuman", "sa", "ayodhya", "male", "king");
		HeroDto heroDto = new HeroDto(1, "hanuman", "sa", "ayodhya", "male", "king");
		when(heroRepoInterface.findById(Mockito.anyInt())).thenReturn(Optional.of(hero));
		assertEquals(heroImple.findid(heroDto).getId(), heroDto.getId());

	}

	@Test
	public void exceptionHandler() {
		Heros hero = new Heros(1, "hanuman", "sa", "ayodhya", "male", "king");
		RuntimeException e = assertThrows(RuntimeException.class, () -> heroImple.addHero(hero));
		when(heroRepoInterface.save(hero)).thenThrow(new RuntimeException("plase give right info"));
		String expectedmassage = "plase give right info";
		assertEquals(expectedmassage, e.getMessage());
	}

	@SuppressWarnings({ "unused" })
	@Test
	public void findpowerexception() {
		Heros hero = new Heros(1, "hanuman", "sa", "ayodhya", "male", "king");
		String power = "mahabali";
		when(heroRepoInterface.findByPower(power)).thenThrow(new RuntimeException("please write proper power"));
		RuntimeException r = assertThrows(RuntimeException.class, () -> heroImple.findpower(power));
		String expectmassage = "please write proper power";
		assertEquals(expectmassage, r.getMessage());
	}

	@Test
	public void addHerosTest() throws Exception {

		Heros hero = new Heros(1, "hanuman", "sa", "ayodhya", "male", "king");
//		when(heroImple.addHero(hero)).thenReturn(hero);
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(hero);
		this.mockMvc.perform(post("/hero").content(jsonbody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void findpower() throws Exception {
		Heros hero = new Heros(1, "hanuman", "mahabali", "ayodhya", "male", "king");
		String power = "mahaba";
//		when(heroImple.findpower(power)).thenReturn(Optional.of(hero));
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unused")
		String jsonbody = mapper.writeValueAsString(hero.getPower());
		String string = mapper.writeValueAsString(power);
		@SuppressWarnings("unused")
		ResultActions andExpect = this.mockMvc
				.perform(get("/power").content(power).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		System.out.println(power);
		System.out.println(jsonbody);
                assertThat(jsonbody.compareToIgnoreCase(string));
	}

	@Test
	@Disabled
	void contextLoads() {

		assertTrue(true);

	}

	@Test
	@Disabled
	public void addHeroTest() {
		Heros hero = new Heros(1, "hanuman", "sa", "ayodhya", "male", "king");
		System.out.println(hero);
		when(heroRepoInterface.save(Mockito.any())).thenReturn(hero);
		Heros addHero = heroImple.addHero(hero);
		System.out.println(addHero);

		assertThat(addHero.equals(hero));
	}

	@Test
	public void findpowerTest() {
		Heros hero = new Heros(1, "hanuman", "mahabali", "ayodhya", "male", "king");
		String power = "maha";
		when(heroImple.findpower(Mockito.anyString())).thenReturn(Optional.of(hero));
		Heros heros = heroImple.findpower(power).get();

		assertEquals(hero.getName(), heros.getName());

	}

	@Test

	public void findNameTest() {
		Heros hero = new Heros(1, "hanuman", "sa", "ayodhya", "male", "king");
		String name = "hanuman";
		OngoingStubbing<Optional<Heros>> thenReturn = when(heroRepoInterface.findByName(Mockito.anyString()))
				.thenReturn((Optional.of(hero)));
		Heros findName = heroImple.findName("hanuma");
		assertThat(thenReturn.equals(findName));
	}

}
