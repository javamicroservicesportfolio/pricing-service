package com.portfolio.pricingservice.service.impl;

import com.portfolio.exception.DuplicateResourceException;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.payload.request.BaggagePolicyRequest;
import com.portfolio.payload.response.BaggagePolicyResponse;
import com.portfolio.pricingservice.mapper.BaggagePolicyMapper;
import com.portfolio.pricingservice.model.BaggagePolicy;
import com.portfolio.pricingservice.model.Fare;
import com.portfolio.pricingservice.repository.BaggagePolicyRepository;
import com.portfolio.pricingservice.repository.FareRepository;
import com.portfolio.pricingservice.service.BaggagePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaggagePolicyServiceImpl implements BaggagePolicyService {

    private final FareRepository fareRepository;
    private final BaggagePolicyRepository baggagePolicyRepository;

    @Override
    public BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest request) {
        //Fetch fare
        Fare fare = fareRepository.findById(request.getFareId())
                .orElseThrow(() -> new ResourceNotFoundException("Fare not found with id: " + request.getFareId()));

        // Check if baggage policy exists for this fare id(One to one relationship)
        if (baggagePolicyRepository.existsByFareId((fare.getId()))) {
            throw new DuplicateResourceException("Baggage policy already exists for fare id: " + fare.getId());
        }

        BaggagePolicy baggagePolicy = BaggagePolicyMapper.toEntity(request, fare);
        BaggagePolicy savedBaggagePolicy = baggagePolicyRepository.save(baggagePolicy);
        return BaggagePolicyMapper.toResponse(savedBaggagePolicy);

    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyById(Long baggagePolicyId) {
        return baggagePolicyRepository.findById(baggagePolicyId)
                .map(BaggagePolicyMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage policy not found with id: " + baggagePolicyId));
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId) {
        BaggagePolicy baggagePolicy = baggagePolicyRepository.findByFareId(fareId);
        if (baggagePolicy == null) {
            throw new ResourceNotFoundException("Baggage policy not found for fare id: " + fareId);
        }
        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public List<BaggagePolicyResponse> getBaggagePolicyByAirlineId(Long airlineId) {
        List<BaggagePolicy> baggagePolicies = baggagePolicyRepository.findByAirlineId(airlineId);
        if (baggagePolicies.isEmpty()) {
            throw new ResourceNotFoundException("No baggage policies found for airline id: " + airlineId);
        }
        return baggagePolicies.stream()
                .map(BaggagePolicyMapper::toResponse)
                .toList();
    }

    @Override
    public BaggagePolicyResponse updateBaggagePolicy(Long baggagePolicyId, BaggagePolicyRequest baggagePolicy) {
        BaggagePolicy existingBaggagePolicy = baggagePolicyRepository.findById(baggagePolicyId)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage policy not found with id: " + baggagePolicyId));


        BaggagePolicyMapper.updateEntity(existingBaggagePolicy, baggagePolicy);
        BaggagePolicy updatedBaggagePolicy = baggagePolicyRepository.save(existingBaggagePolicy);
        return BaggagePolicyMapper.toResponse(updatedBaggagePolicy);
    }

    @Override
    public void deleteBaggagePolicy(Long baggagePolicyId) {
        BaggagePolicy existingBaggagePolicy = baggagePolicyRepository.findById(baggagePolicyId)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage policy not found with id: " + baggagePolicyId));
        baggagePolicyRepository.delete(existingBaggagePolicy);
    }
}
