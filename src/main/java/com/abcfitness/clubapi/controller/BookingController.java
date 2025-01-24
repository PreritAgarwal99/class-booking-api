package com.abcfitness.clubapi.controller;

import com.abcfitness.clubapi.entity.BookingEntity;
import com.abcfitness.clubapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * API to create a booking.
     * abcfitness Request Body:
     * {
     *   "memberName": "John Doe",
     *   "className": "Pilates",
     *   "participationDate": "2025-01-05"
     * }
     */
    @PostMapping
    public ResponseEntity<BookingEntity> createBooking(@RequestBody BookingEntity booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    /**
     * API to search bookings.
     * Example Query Parameters:
     * - `/api/bookings?memberName=John Doe`
     * - `/api/bookings?startDate=2025-01-01&endDate=2025-01-10`
     * - `/api/bookings?memberName=John Doe&startDate=2025-01-01&endDate=2025-01-10`
     */
    @GetMapping
    public ResponseEntity<List<BookingEntity>> searchBookings(
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return ResponseEntity.ok(bookingService.searchBookings(memberName, startDate, endDate));
    }
}
