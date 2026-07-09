package com.flexidesk.desk.controller;

import com.flexidesk.desk.entity.Desk;
import com.flexidesk.desk.repository.DeskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/desks")
@RequiredArgsConstructor
public class InternalDeskController {

    private final DeskRepository deskRepository;

    @GetMapping("/{id}")
    public Desk getDeskById(@PathVariable Long id) {

        return deskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Desk not found"));

    }

    @PutMapping("/{id}/reserve")
    public void reserveDesk(@PathVariable Long id) {

        Desk desk = deskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Desk not found"));

        if (desk.getAvailable() <= 0) {
            throw new RuntimeException("Desk not available");
        }

        desk.setAvailable(desk.getAvailable() - 1);

        deskRepository.save(desk);
    }

    @PutMapping("/{id}/release")
    public void releaseDesk(@PathVariable Long id) {

        Desk desk = deskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Desk not found"));

        desk.setAvailable(
                Math.min(
                        desk.getAvailable() + 1,
                        desk.getTotalSlots()
                )
        );

        deskRepository.save(desk);
    }
}
