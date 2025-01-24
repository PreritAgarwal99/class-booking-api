package com.abcfitness.clubapi.service;

import com.abcfitness.clubapi.entity.BookingEntity;
import com.abcfitness.clubapi.entity.ClassEntity;
import com.abcfitness.clubapi.repository.BookingRepository;
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
public class BookingServiceTest {

	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private ClassRepository classRepository;

	@InjectMocks
	private BookingService bookingService;

	@Test
	public void testCreateBooking_ValidInput() {
		BookingEntity booking = new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-25"));
		ClassEntity classEntity = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"),
				LocalDate.parse("2025-01-28"), "14:00", 60, 10);

		when(classRepository.getAllClasses()).thenReturn(List.of(classEntity));
		when(bookingRepository.getBookingsByClassAndDate("Pilates", LocalDate.parse("2025-01-25")))
				.thenReturn(List.of());
		when(bookingRepository.saveBooking(booking)).thenReturn(booking);

		BookingEntity result = bookingService.createBooking(booking);

		assertNotNull(result);
		assertEquals("John Doe", result.getMemberName());
		verify(bookingRepository, times(1)).saveBooking(booking);
	}

	@Test
	public void testCreateBooking_ClassNotFound() {
		BookingEntity booking = new BookingEntity("John Doe", "Zumba", LocalDate.parse("2025-01-05"));

		when(classRepository.getAllClasses()).thenReturn(List.of());

		Exception exception = assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(booking));

		assertEquals("Class not found.", exception.getMessage());
		verify(bookingRepository, never()).saveBooking(any());
	}

	@Test
	public void testCreateBooking_ClassAtFullCapacity() {
		BookingEntity booking = new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-05"));
		ClassEntity classEntity = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"),
				LocalDate.parse("2025-01-20"), "14:00", 60, 1);

		when(classRepository.getAllClasses()).thenReturn(List.of(classEntity));
		when(bookingRepository.getBookingsByClassAndDate("Pilates", LocalDate.parse("2025-01-05")))
				.thenReturn(List.of(new BookingEntity("Jane Doe", "Pilates", LocalDate.parse("2025-01-05"))));

		Exception exception = assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(booking));

		assertEquals("Class is at full capacity for the selected date.", exception.getMessage());
		verify(bookingRepository, never()).saveBooking(any());
	}

	@Test
	public void testCreateBooking_InvalidParticipationDate() {
		BookingEntity booking = new BookingEntity("John Doe", "Pilates", LocalDate.now().minusDays(1));
		ClassEntity classEntity = new ClassEntity("Pilates", LocalDate.parse("2025-01-01"),
				LocalDate.parse("2025-01-28"), "14:00", 60, 10);

		when(classRepository.getAllClasses()).thenReturn(List.of(classEntity));

		Exception exception = assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(booking));

		assertEquals("Participation date must be in the future.", exception.getMessage());
		verify(bookingRepository, never()).saveBooking(any());
	}

	@Test
	public void testSearchBookings() {
		List<BookingEntity> bookings = Arrays.asList(
				new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-05")),
				new BookingEntity("Jane Doe", "Yoga", LocalDate.parse("2025-01-06")));

		when(bookingRepository.searchBookings("John Doe", "2025-01-01", "2025-01-10")).thenReturn(bookings);

		List<BookingEntity> result = bookingService.searchBookings("John Doe", "2025-01-01", "2025-01-10");

		assertEquals(2, result.size());
		verify(bookingRepository, times(1)).searchBookings("John Doe", "2025-01-01", "2025-01-10");
	}
}
