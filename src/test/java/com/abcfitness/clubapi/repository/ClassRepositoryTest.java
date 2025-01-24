package com.abcfitness.clubapi.repository;

import com.abcfitness.clubapi.entity.ClassEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassRepositoryTest {

	private final ClassRepository classRepository = new ClassRepository();

	@Test
	public void testSaveClass() {
		ClassEntity classEntity = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"),
				LocalDate.parse("2025-01-28"), "14:00", 60, 10);

		ClassEntity result = classRepository.saveClass(classEntity);

		assertNotNull(result);
		assertEquals("Pilates", result.getName());
	}

	@Test
	public void testGetAllClasses() {
		ClassEntity class1 = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"), LocalDate.parse("2025-01-20"),
				"14:00", 60, 10);
		ClassEntity class2 = new ClassEntity("Yoga", LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-15"),
				"10:00", 45, 15);

		classRepository.saveClass(class1);
		classRepository.saveClass(class2);

		List<ClassEntity> result = classRepository.getAllClasses();

		assertEquals(2, result.size());
		assertEquals("Pilates", result.get(0).getName());
		assertEquals("Yoga", result.get(1).getName());
	}
}