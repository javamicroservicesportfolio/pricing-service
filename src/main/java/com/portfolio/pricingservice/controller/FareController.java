package com.portfolio.pricingservice.controller;

import com.portfolio.payload.request.FareRequest;
import com.portfolio.payload.response.ApiResponse;
import com.portfolio.payload.response.FareResponse;
import com.portfolio.pricingservice.service.FareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fares")
public class FareController {

    private final FareService fareService;

    @PostMapping
    public ResponseEntity<FareResponse> createFare(@RequestBody FareRequest fareRequest) {
        FareResponse fareResponse = fareService.createFare(fareRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(fareResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FareResponse> getFareById(@PathVariable Long id) {
        FareResponse fareResponse = fareService.getFareById(id);
        return ResponseEntity.ok(fareResponse);
    }

    @GetMapping
    public ResponseEntity<?> getFares() {
        return ResponseEntity.ok(fareService.getFares());
    }

    @GetMapping("/flight/{flightId}/cabin-class/{cabinClassId}")
    public ResponseEntity<?> getFaresByFlightAndCabinClass(@PathVariable Long flightId, @PathVariable Long cabinClassId) {
        return ResponseEntity.ok(fareService.getFaresByFlightIdAndCabinClassId(flightId, cabinClassId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FareResponse> updateFare(@PathVariable Long id, @RequestBody FareRequest fareRequest) {
        FareResponse fareResponse = fareService.updateFare(id, fareRequest);
        return ResponseEntity.ok(fareResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFare(@PathVariable Long id) {
        fareService.deleteFare(id);
        return ResponseEntity.ok(new ApiResponse("Fare deleted successfully"));
    }

    @PostMapping("/search")
    public ResponseEntity<Map<Long, FareResponse>> getLowestFaresByFlight(@RequestBody List<Long> flightIds, @RequestParam Long cabinClassId) {
        Map<Long, FareResponse> lowestFares = fareService.getLowestFaresByFlight(flightIds, cabinClassId);
        return ResponseEntity.ok(lowestFares);
    }

    @PostMapping("/batch-by-ids")
    public ResponseEntity<Map<Long, FareResponse>> getFaresByIds(@RequestBody List<Long> ids) {
        Map<Long, FareResponse> fares = fareService.getFaresByIds(ids);
        return ResponseEntity.ok(fares);
    }

}
