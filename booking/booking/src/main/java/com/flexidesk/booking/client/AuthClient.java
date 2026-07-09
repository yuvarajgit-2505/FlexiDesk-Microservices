package com.flexidesk.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flexidesk.booking.model.User;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/internal/users/{id}")
    User getUser(@PathVariable Long id);

}