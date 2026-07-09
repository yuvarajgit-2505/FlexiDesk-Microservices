package com.flexidesk.booking.service;

import com.flexidesk.booking.client.AuthClient;
import com.flexidesk.booking.client.DeskClient;
import com.flexidesk.booking.entity.Booking;
import com.flexidesk.booking.model.Desk;
import com.flexidesk.booking.model.User;
import com.flexidesk.booking.repository.*;
import com.flexidesk.booking.service.*;

import jakarta.transaction.Transactional;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final AuthClient authClient;
    private final DeskClient deskClient;

    public BookingServiceImpl(BookingRepository bookingRepository,AuthClient authClient,
        DeskClient deskClient) {
        this.bookingRepository = bookingRepository;
        this.authClient = authClient;
        this.deskClient = deskClient;
    }

    @Override
public Booking createBooking(Long userId,
                             Long deskId,
                             LocalDate startDate,
                             LocalDate endDate) {

    // Check whether user exists
    User user = authClient.getUser(userId);

    if (user == null) {
        throw new RuntimeException("User not found");
    }

    // Check whether desk exists
    Desk desk = deskClient.getDesk(deskId);

    if (desk == null) {
        throw new RuntimeException("Desk not found");
    }

    // Check availability
    if (desk.getAvailable() <= 0) {
        throw new RuntimeException("Desk is already booked");
    }

    // Reserve the desk in Desk Service
    deskClient.reserveDesk(deskId);

    Booking booking = new Booking();

    booking.setUserId(user.getId());
    booking.setDeskId(desk.getId());
    booking.setStartdate(startDate);
    booking.setEnddate(endDate);
    booking.setBookingTime(LocalDateTime.now());

    return bookingRepository.save(booking);
}

    @Override
public List<Booking> getBookingsByUser(Long userId) {
    return bookingRepository.findByUserId(userId);
}

    @Override
public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
}

    @Override
public void cancelBooking(Long bookingId) {

    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

    // Release the desk in Desk Service
    deskClient.releaseDesk(booking.getDeskId());

    bookingRepository.delete(booking);
}
}
