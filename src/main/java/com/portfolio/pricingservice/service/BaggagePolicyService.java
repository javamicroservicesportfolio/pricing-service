package com.portfolio.pricingservice.service;

import com.portfolio.payload.request.BaggagePolicyRequest;
import com.portfolio.payload.response.BaggagePolicyResponse;
import com.portfolio.pricingservice.model.BaggagePolicy;

import java.util.List;

public interface BaggagePolicyService {
    BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest baggagePolicy);

    BaggagePolicyResponse getBaggagePolicyById(Long baggagePolicyId);

    BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId);

    List<BaggagePolicyResponse> getBaggagePolicyByAirlineId(Long airlineId);

    BaggagePolicyResponse updateBaggagePolicy(Long baggagePolicyId, BaggagePolicyRequest baggagePolicy);

    void deleteBaggagePolicy(Long baggagePolicyId);
}
