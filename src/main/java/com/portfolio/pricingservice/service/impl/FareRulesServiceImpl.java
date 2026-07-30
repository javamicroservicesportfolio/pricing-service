package com.portfolio.pricingservice.service.impl;

import com.portfolio.exception.DuplicateResourceException;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.payload.request.FareRulesRequest;
import com.portfolio.payload.response.FareRulesResponse;
import com.portfolio.pricingservice.mapper.FareRulesMapper;
import com.portfolio.pricingservice.model.Fare;
import com.portfolio.pricingservice.model.FareRules;
import com.portfolio.pricingservice.repository.FareRepository;
import com.portfolio.pricingservice.repository.FareRulesRepository;
import com.portfolio.pricingservice.service.FareRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FareRulesServiceImpl implements FareRulesService {
    private final FareRepository fareRepository;
    private final FareRulesRepository fareRulesRepository;

    @Override
    public FareRulesResponse createFareRules(FareRulesRequest request) {
        Fare fare = fareRepository.findById(request.getFareId())
                .orElseThrow(() -> new ResourceNotFoundException("Fare not found with id: " + request.getFareId()));

        if (fareRulesRepository.existsByFareId(fare.getId())) {
            throw new DuplicateResourceException("Fare rules already exist for the given fare id: " + request.getFareId());
        }

        FareRules fareRules = FareRulesMapper.toEntity(request, fare);
        fareRulesRepository.save(fareRules);

        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public FareRulesResponse getFareRulesById(Long id) {
        FareRules fareRules = fareRulesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fare rules not found with id: " + id));
        return FareRulesMapper.toResponse(fareRules);
    }


    @Override
    public FareRulesResponse getFareRulesByFareId(Long fareId) {
        FareRules fareRules = fareRulesRepository.findByFareId(fareId);
        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) {
        return fareRulesRepository.getFareRulesByAirlineId(airlineId)
                .stream()
                .map(FareRulesMapper::toResponse)
                .toList();
    }

    @Override
    public FareRulesResponse updateFareRules(Long id, FareRulesRequest request) {
        FareRules fareRules = fareRulesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fare rules not found with id: " + id));

        FareRulesMapper.updateEntity(fareRules, request);
        fareRulesRepository.save(fareRules);

        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public void deleteFareRules(Long id) {
        fareRulesRepository.deleteById(id);
    }
}
