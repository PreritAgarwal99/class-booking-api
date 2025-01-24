package com.abcfitness.clubapi.service;

import com.abcfitness.clubapi.entity.BookingEntity;
import com.abcfitness.clubapi.entity.ClassEntity;
import com.abcfitness.clubapi.repository.BookingRepository;
import com.abcfitness.clubapi.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ClassRepository classRepository;

	public BookingEntity createBooking(BookingEntity booking) {
		List<ClassEntity> classes = classRepository.getAllClasses();
		ClassEntity matchedClass = classes.stream()
				.filter(c -> c.getName().equals(booking.getClassName())
						&& (c.getEndDate().isEqual(booking.getParticipationDate())
								|| c.getEndDate().isAfter(booking.getParticipationDate())))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Class not found."));

		long existingBookings = bookingRepository
				.getBookingsByClassAndDate(booking.getClassName(), booking.getParticipationDate()).size();

		if (existingBookings >= matchedClass.getCapacity()) {
			throw new IllegalArgumentException("Class is at full capacity for the selected date.");
		}

		if (booking.getParticipationDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Participation date must be in the future.");
		}

		return bookingRepository.saveBooking(booking);
	}

	public List<BookingEntity> searchBookings(String memberName, String startDate, String endDate) {
		return bookingRepository.searchBookings(memberName, startDate, endDate);
	}
}
