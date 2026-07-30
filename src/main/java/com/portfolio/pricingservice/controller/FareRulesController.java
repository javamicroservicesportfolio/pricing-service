package com.portfolio.pricingservice.controller;

import com.portfolio.payload.request.FareRulesRequest;
import com.portfolio.payload.response.ApiResponse;
import com.portfolio.payload.response.FareRulesResponse;
import com.portfolio.pricingservice.service.FareRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fare-rules")
public class FareRulesController {
    private final FareRulesService fareRulesService;

    @PostMapping
    public ResponseEntity<FareRulesResponse> createFareRules(@RequestBody FareRulesRequest request) {
        FareRulesResponse response = fareRulesService.createFareRules(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FareRulesResponse> getFareRulesById(@PathVariable Long id) {
        FareRulesResponse response = fareRulesService.getFareRulesById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fare/{fareId}")
    public ResponseEntity<FareRulesResponse> getFareRulesByFareId(@PathVariable Long fareId) {
        FareRulesResponse response = fareRulesService.getFareRulesByFareId(fareId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<FareRulesResponse>> getFareRulesByAirlineId(@PathVariable Long airlineId) {
        List<FareRulesResponse> response = fareRulesService.getFareRulesByAirlineId(airlineId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FareRulesResponse> updateFareRules(@PathVariable Long id, @RequestBody FareRulesRequest request) {
        FareRulesResponse response = fareRulesService.updateFareRules(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFareRules(@PathVariable Long id) {
        fareRulesService.deleteFareRules(id);
        return ResponseEntity.ok(new ApiResponse("Fare rules deleted successfully"));
    }
}
