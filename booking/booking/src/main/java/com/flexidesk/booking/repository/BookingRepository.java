package com.flexidesk.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexidesk.booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByDeskIdAndStartdateLessThanEqualAndEnddateGreaterThanEqual(
        Long deskId,
        LocalDate endDate,
        LocalDate startDate
);
    List<Booking> findByUserId(Long userId);



}
