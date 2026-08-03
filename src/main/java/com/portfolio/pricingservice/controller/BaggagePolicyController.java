package com.portfolio.pricingservice.controller;

import com.portfolio.payload.request.BaggagePolicyRequest;
import com.portfolio.payload.response.ApiResponse;
import com.portfolio.payload.response.BaggagePolicyResponse;
import com.portfolio.pricingservice.service.BaggagePolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baggage-policies")
@RequiredArgsConstructor
public class BaggagePolicyController {

    private final BaggagePolicyService baggagePolicyService;

    @PostMapping
    public ResponseEntity<BaggagePolicyResponse> createBaggagePolicy(@Valid BaggagePolicyRequest request) {
        BaggagePolicyResponse response = baggagePolicyService.createBaggagePolicy(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{baggagePolicyId}")
    public ResponseEntity<BaggagePolicyResponse> getBaggagePolicyById(@PathVariable Long baggagePolicyId) {
        BaggagePolicyResponse response = baggagePolicyService.getBaggagePolicyById(baggagePolicyId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fare/{fareId}")
    public ResponseEntity<BaggagePolicyResponse> getBaggagePolicyByFareId(@PathVariable Long fareId) {
        BaggagePolicyResponse response = baggagePolicyService.getBaggagePolicyByFareId(fareId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<BaggagePolicyResponse>> getBaggagePolicyByAirlineId(@PathVariable Long airlineId) {
        return ResponseEntity.ok(baggagePolicyService.getBaggagePolicyByAirlineId(airlineId));
    }

    @PutMapping("/{baggagePolicyId}")
    public ResponseEntity<BaggagePolicyResponse> updateBaggagePolicy(@PathVariable Long baggagePolicyId, @RequestBody BaggagePolicyRequest request) {
        BaggagePolicyResponse response = baggagePolicyService.updateBaggagePolicy(baggagePolicyId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{baggagePolicyId}")
    public ResponseEntity<ApiResponse> deleteBaggagePolicy(@PathVariable Long baggagePolicyId) {
        baggagePolicyService.deleteBaggagePolicy(baggagePolicyId);
        return ResponseEntity.ok(new ApiResponse("Baggage policy deleted successfully"));
    }

}
