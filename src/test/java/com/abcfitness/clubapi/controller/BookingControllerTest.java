package com.abcfitness.clubapi.controller;

import com.abcfitness.clubapi.entity.BookingEntity;
import com.abcfitness.clubapi.service.BookingService;
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

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookingService bookingService;

	@Test
	public void testCreateBooking() throws Exception {
		BookingEntity booking = new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-05"));

		when(bookingService.createBooking(booking)).thenReturn(booking);

		String requestBody = "{\"memberName\":\"John Doe\",\"className\":\"Pilates\",\"participationDate\":\"2025-01-05\"}";

		mockMvc.perform(post("/api/bookings").contentType(APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk());
	}

	@Test
	public void testSearchBookings_ByMemberName() throws Exception {
		List<BookingEntity> bookings = Arrays.asList(
				new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-05")),
				new BookingEntity("John Doe", "Yoga", LocalDate.parse("2025-01-06")));

		when(bookingService.searchBookings("John Doe", null, null)).thenReturn(bookings);

		mockMvc.perform(get("/api/bookings").param("memberName", "John Doe")).andExpect(status().isOk());
	}

	@Test
	public void testSearchBookings_ByDateRange() throws Exception {
		List<BookingEntity> bookings = Arrays.asList(
				new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-05")),
				new BookingEntity("Jane Doe", "Yoga", LocalDate.parse("2025-01-06")));

		when(bookingService.searchBookings(null, "2025-01-01", "2025-01-10")).thenReturn(bookings);

		mockMvc.perform(get("/api/bookings").param("startDate", "2025-01-01").param("endDate", "2025-01-10"))
				.andExpect(status().isOk());
	}

	@Test
	public void testSearchBookings_ByMemberNameAndDateRange() throws Exception {
		List<BookingEntity> bookings = Arrays
				.asList(new BookingEntity("John Doe", "Pilates", LocalDate.parse("2025-01-05")));

		when(bookingService.searchBookings("John Doe", "2025-01-01", "2025-01-10")).thenReturn(bookings);

		mockMvc.perform(get("/api/bookings").param("memberName", "John Doe").param("startDate", "2025-01-01")
				.param("endDate", "2025-01-10")).andExpect(status().isOk());
	}
}
