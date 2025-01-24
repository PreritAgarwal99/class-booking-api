package com.abcfitness.clubapi.repository;

import com.abcfitness.clubapi.entity.BookingEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingRepository {

    private final List<BookingEntity> bookingList = new ArrayList<>();

    public BookingEntity saveBooking(BookingEntity booking) {
        bookingList.add(booking);
        return booking;
    }

    public List<BookingEntity> getBookingsByClassAndDate(String className, LocalDate date) {
        return bookingList.stream()
                .filter(b -> b.getClassName().equals(className) && b.getParticipationDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<BookingEntity> searchBookings(String memberName, String startDate, String endDate) {
        LocalDate start = (startDate != null) ? LocalDate.parse(startDate) : null;
        LocalDate end = (endDate != null) ? LocalDate.parse(endDate) : null;

        return bookingList.stream()
                .filter(booking -> (memberName == null || booking.getMemberName().equalsIgnoreCase(memberName)) &&
                        (start == null || !booking.getParticipationDate().isBefore(start)) &&
                        (end == null || !booking.getParticipationDate().isAfter(end)))
                .collect(Collectors.toList());
    }
}

