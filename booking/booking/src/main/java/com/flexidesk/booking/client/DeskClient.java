package com.flexidesk.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.flexidesk.booking.model.Desk;

@FeignClient(name = "desk-service")
public interface DeskClient {

    @GetMapping("/internal/desks/{id}")
    Desk getDesk(@PathVariable Long id);

    @PutMapping("/internal/desks/{id}/reserve")
    void reserveDesk(@PathVariable Long id);

    @PutMapping("/internal/desks/{id}/release")
    void releaseDesk(@PathVariable Long id);

}