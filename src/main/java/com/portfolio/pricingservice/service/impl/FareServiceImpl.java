package com.portfolio.pricingservice.service.impl;


import com.portfolio.exception.BadRequestException;
import com.portfolio.exception.DuplicateResourceException;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.payload.request.FareRequest;
import com.portfolio.payload.response.FareResponse;
import com.portfolio.pricingservice.mapper.FareMapper;
import com.portfolio.pricingservice.model.Fare;
import com.portfolio.pricingservice.repository.FareRepository;
import com.portfolio.pricingservice.service.FareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FareServiceImpl implements FareService {
    private final FareRepository fareRepository;

    @Override
    public FareResponse createFare(FareRequest fareRequest) {
        if (fareRepository.existsByFlightIdAndCabinClassIdAndName(fareRequest.getFlightId(), fareRequest.getCabinClassId(), fareRequest.getName())) {
            throw new DuplicateResourceException("Fare with the same name already exists for this flight and cabin class.");
        }

        Fare fare = FareMapper.toEntity(fareRequest);
        Fare saved = fareRepository.save(fare);
        return FareMapper.toResponse(saved);
    }

    @Override
    public FareResponse getFareById(Long fareId) {
        Fare fare = fareRepository.findById(fareId).orElseThrow(() -> new ResourceNotFoundException("Fare not found with ID: " + fareId));
        return FareMapper.toResponse(fare);
    }

    @Override
    public List<FareResponse> getFaresByFlightIdAndCabinClassId(Long flightId, Long cabinClassId) {
        return fareRepository.findByFlightIdAndCabinClassId(flightId, cabinClassId)
                .stream()
                .map(FareMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FareResponse updateFare(Long fareId, FareRequest fareRequest) {
        Fare fare = fareRepository.findById(fareId).orElseThrow(() -> new ResourceNotFoundException("Fare not found with ID: " + fareId));

        if (fareRepository.existsByFlightIdAndCabinClassIdAndNameAndIdNot(fareRequest.getFlightId(), fareRequest.getCabinClassId(), fareRequest.getName(), fareId)) {
            throw new DuplicateResourceException("Fare with the same name already exists for this flight and cabin class.");
        }

        FareMapper.updateEntity(fareRequest, fare);
        Fare updated = fareRepository.save(fare);
        return FareMapper.toResponse(updated);
    }

    @Override
    public void deleteFare(Long fareId) {
        fareRepository.deleteById(fareId);
    }

    @Override
    public List<Fare> getFares() {
        return fareRepository.findAll();
    }

    /**
     * Retrieves the lowest fares for a given list of flight IDs and a specific cabin class.
     * <p>
     * Fetches all matching fares from the repository, groups them by flight ID, and selects
     * the fare with the lowest total price for each flight.
     *
     * @param flightIds    a {@code List<Long>} containing the flight IDs to retrieve fares for
     * @param cabinClassId a {@code Long} representing the target cabin class ID
     * @return a {@code Map<Long, FareResponse>} mapping each flight ID to its lowest available {@link FareResponse}
     * @throws BadRequestException if {@code flightIds} is {@code null} or empty
     */
    @Override
    public Map<Long, FareResponse> getLowestFaresByFlight(List<Long> flightIds, Long cabinClassId) {
        if (flightIds == null || flightIds.isEmpty()) {
            throw new BadRequestException("Flight IDs list cannot be null or empty.");
        }

        // Fetch all fares for the given flight IDs and cabin class ID
        List<Fare> lowestFares = fareRepository.findByFlightIdInAndCabinClassId(flightIds, cabinClassId);

        // Group fares by flight ID, select the lowest price, and map directly to FareResponse
        return lowestFares.stream()
                .collect(Collectors.toMap(
                        Fare::getFlightId,
                        FareMapper::toResponse,
                        (existing, candidate) -> candidate.getTotalPrice() < existing.getTotalPrice() ? candidate : existing
                ));
    }

    @Override
    public Map<Long, FareResponse> getFaresByIds(List<Long> ids) {
        return fareRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(Fare::getId, FareMapper::toResponse));
    }
}
