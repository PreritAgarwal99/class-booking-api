package com.abcfitness.clubapi.repository;

import com.abcfitness.clubapi.entity.BookingEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingRepositoryTest {

	private final BookingRepository bookingRepository = new BookingRepository();

	@Test
	public void testSaveBooking() {
		BookingEntity booking = new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-25"));

		BookingEntity result = bookingRepository.saveBooking(booking);

		assertNotNull(result);
		assertEquals("John Doe", result.getMemberName());
		assertEquals("Pilates", result.getClassName());
		assertEquals(LocalDate.parse("2025-01-25"), result.getParticipationDate());
	}

	@Test
	public void testGetBookingsByClassAndDate() {
		BookingEntity booking1 = new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-25"));
		BookingEntity booking2 = new BookingEntity("Jane Doe", "Pilates", LocalDate.parse("2025-01-25"));
		BookingEntity booking3 = new BookingEntity("John Doe", "Yoga", LocalDate.parse("2025-01-26"));

		bookingRepository.saveBooking(booking1);
		bookingRepository.saveBooking(booking2);
		bookingRepository.saveBooking(booking3);

		List<BookingEntity> result = bookingRepository.getBookingsByClassAndDate("Pilates",
				LocalDate.parse("2025-01-25"));

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(b -> b.getClassName().equals("Pilates")));
	}

	@Test
	public void testSearchBookings_ByMemberName() {
		BookingEntity booking1 = new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-25"));
		BookingEntity booking2 = new BookingEntity("Jane Doe", "Pilates", LocalDate.parse("2025-01-25"));
		BookingEntity booking3 = new BookingEntity("John Doe", "Yoga", LocalDate.parse("2025-01-26"));

		bookingRepository.saveBooking(booking1);
		bookingRepository.saveBooking(booking2);
		bookingRepository.saveBooking(booking3);

		List<BookingEntity> result = bookingRepository.searchBookings("John Doe", null, null);

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(b -> b.getMemberName().equals("John Doe")));
	}

	@Test
	public void testSearchBookings_ByDateRange() {
		BookingEntity booking1 = new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-25"));
		BookingEntity booking2 = new BookingEntity("Jane Doe", "Pilates", LocalDate.parse("2025-01-26"));
		BookingEntity booking3 = new BookingEntity("John Doe", "Yoga", LocalDate.parse("2025-01-25"));

		bookingRepository.saveBooking(booking1);
		bookingRepository.saveBooking(booking2);
		bookingRepository.saveBooking(booking3);

		List<BookingEntity> result = bookingRepository.searchBookings(null, "2025-01-25", "2025-01-26");

		assertEquals(2, result.size());
		assertTrue(result.stream().allMatch(b -> b.getParticipationDate().isAfter(LocalDate.parse("2025-01-24"))
				&& b.getParticipationDate().isBefore(LocalDate.parse("2025-01-27"))));
	}

	@Test
	public void testSearchBookings_ByMemberNameAndDateRange() {
		BookingEntity booking1 = new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-25"));
		BookingEntity booking2 = new BookingEntity("Jane Doe", "Pilates", LocalDate.parse("2025-01-26"));
		BookingEntity booking3 = new BookingEntity("John Doe", "Yoga", LocalDate.parse("2025-01-25"));

		bookingRepository.saveBooking(booking1);
		bookingRepository.saveBooking(booking2);
		bookingRepository.saveBooking(booking3);

		List<BookingEntity> result = bookingRepository.searchBookings("John Doe", "2025-01-21", "2025-01-26");

		assertEquals(1, result.size());
		assertEquals("John Doe", result.get(0).getMemberName());
		assertEquals(LocalDate.parse("2025-01-25"), result.get(0).getParticipationDate());
	}
}
