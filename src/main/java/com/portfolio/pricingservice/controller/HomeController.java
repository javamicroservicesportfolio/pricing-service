package com.portfolio.pricingservice.controller;

import com.portfolio.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse homeController(){
        return new ApiResponse(
                "Welcome to the Airline Pricing Service API"
        );
    }
}
