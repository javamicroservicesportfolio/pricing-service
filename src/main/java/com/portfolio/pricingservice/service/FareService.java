package com.portfolio.pricingservice.service;


import com.portfolio.payload.request.FareRequest;
import com.portfolio.payload.response.FareResponse;
import com.portfolio.pricingservice.model.Fare;

import java.util.List;
import java.util.Map;

public interface FareService {
    FareResponse createFare(FareRequest fareRequest);

    FareResponse getFareById(Long fareId);

    List<FareResponse> getFaresByFlightIdAndCabinClassId(Long flightId, Long cabinClassId);

    FareResponse updateFare(Long fareId, FareRequest fareRequest);

    void deleteFare(Long fareId);

    List<Fare> getFares();

    Map<Long, FareResponse> getLowestFaresByFlight(List<Long> flightIds, Long cabinClassId);

    Map<Long, FareResponse> getFaresByIds(List<Long> ids);
}
