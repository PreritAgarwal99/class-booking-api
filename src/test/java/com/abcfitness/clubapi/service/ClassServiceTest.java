package com.abcfitness.clubapi.service;

import com.abcfitness.clubapi.entity.ClassEntity;
import com.abcfitness.clubapi.repository.ClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClassServiceTest {

	@Mock
	private ClassRepository classRepository;

	@InjectMocks
	private ClassService classService;

	@Test
	public void testCreateClass_ValidInput() {
		ClassEntity classEntity = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"),
				LocalDate.parse("2025-01-28"), "14:00", 60, 10);

		when(classRepository.saveClass(classEntity)).thenReturn(classEntity);

		ClassEntity result = classService.createClass(classEntity);

		assertNotNull(result);
		assertEquals("Pilates", result.getName());
		verify(classRepository, times(1)).saveClass(classEntity);
	}
	
	@Test
	public void testCreateClass_InvalidDate() {
		ClassEntity classEntity = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"),
				LocalDate.parse("2025-01-20"), "14:00", 60, 10);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			classService.createClass(classEntity);
		});

		assertEquals("End date must be in the future.", exception.getMessage());
		verify(classRepository, never()).saveClass(any());
	}

	@Test
	public void testCreateClass_InvalidCapacity() {
		ClassEntity classEntity = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"),
				LocalDate.parse("2025-01-28"), "14:00", 60, 0);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			classService.createClass(classEntity);
		});

		assertEquals("Capacity must be at least 1.", exception.getMessage());
		verify(classRepository, never()).saveClass(any());
	}

	@Test
	public void testGetAllClasses() {
		List<ClassEntity> classList = Arrays.asList(
				new ClassEntity("Pilates", LocalDate.parse("2025-01-01"), LocalDate.parse("2025-01-28"), "14:00", 60,
						10),
				new ClassEntity("Yoga", LocalDate.parse("2025-02-01"), LocalDate.parse("2025-02-15"), "10:00", 45, 15));

		when(classRepository.getAllClasses()).thenReturn(classList);

		List<ClassEntity> result = classService.getAllClasses();

		assertEquals(2, result.size());
		verify(classRepository, times(1)).getAllClasses();
	}
}