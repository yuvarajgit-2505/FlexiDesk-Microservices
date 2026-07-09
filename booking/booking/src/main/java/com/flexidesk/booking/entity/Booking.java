package com.flexidesk.booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
    private LocalDate startdate;
    private LocalDate enddate;

    
    @Column(name = "user_id")
private Long userId;

@Column(name = "desk_id")
private Long deskId;
    private LocalDateTime bookingTime;

}
