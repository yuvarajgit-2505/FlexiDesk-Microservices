package com.flexidesk.booking.service;

import java.time.LocalDate;
import java.util.List;

import com.flexidesk.booking.entity.Booking;

public interface BookingService {
    Booking createBooking(Long userId, Long deskId, LocalDate startDate,
                          LocalDate endDate);

    List<Booking> getBookingsByUser(Long userId);
    List<Booking> getAllBookings();
    void cancelBooking(Long bookingId);

}
