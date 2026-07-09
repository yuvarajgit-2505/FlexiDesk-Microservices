package com.flexidesk.booking.model;

import lombok.Data;

@Data
public class Desk {

    private Long id;

    private String deskType;

    private Double pricePerDay;

    private Integer totalSlots;

    private Integer available;

}
