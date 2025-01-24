package com.abcfitness.clubapi.controller;

import com.abcfitness.clubapi.entity.ClassEntity;
import com.abcfitness.clubapi.service.ClassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(ClassController.class)
public class ClassControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClassService classService;

	@Test
	public void testCreateClass() throws Exception {
		ClassEntity classEntity = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"),
				LocalDate.parse("2025-01-20"), "14:00", 60, 10);

		when(classService.createClass(classEntity)).thenReturn(classEntity);

		String requestBody = "{\"name\":\"Pilates\",\"startDate\":\"2025-01-01\",\"endDate\":\"2025-01-20\",\"startTime\":\"14:00\",\"duration\":60,\"capacity\":10}";

		mockMvc.perform(post("/api/classes").contentType(APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetAllClasses() throws Exception {
		List<ClassEntity> classList = Arrays.asList(
				new ClassEntity("Pilates", LocalDate.parse("2025-01-01"), LocalDate.parse("2025-01-20"), "14:00", 60,
						10),
				new ClassEntity("Yoga", LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-15"), "10:00", 45, 15));

		when(classService.getAllClasses()).thenReturn(classList);

		mockMvc.perform(get("/api/classes")).andExpect(status().isOk());
	}
}
