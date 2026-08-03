package com.portfolio.pricingservice.mapper;

import com.portfolio.payload.request.BaggagePolicyRequest;
import com.portfolio.payload.response.BaggagePolicyResponse;
import com.portfolio.pricingservice.model.BaggagePolicy;
import com.portfolio.pricingservice.model.Fare;

public class BaggagePolicyMapper {
    public static BaggagePolicy toEntity(BaggagePolicyRequest request, Fare fare) {
        if (request == null || fare == null) {
            return null;
        }

        return BaggagePolicy.builder()
                .fare(fare)
                .name(request.getName())
                .description(request.getDescription())
                .cabinBaggageMaxWeight(request.getCabinBaggageMaxWeight())
                .cabinBaggagePieces(request.getCabinBaggagePieces())
                .cabinBaggageWeightPerPiece(request.getCabinBaggageWeightPerPiece())
                .cabinBaggageMaxDimension(request.getCabinBaggageMaxDimension())
                .checkInBaggageMaxWeight(request.getCheckInBaggageMaxWeight())
                .checkInBaggagePieces(request.getCheckInBaggagePieces())
                .checkInBaggageWeightPerPiece(request.getCheckInBaggageWeightPerPiece())
                .freeCheckedBagsAllowance(request.getFreeCheckedBagsAllowance())
                .priorityBaggage(request.getPriorityBaggage())
                .extraBaggageAllowance(request.getExtraBaggageAllowance())
                .build();
    }

    public static BaggagePolicyResponse toResponse(BaggagePolicy baggagePolicy) {
        if (baggagePolicy == null) {
            return null;
        }

        return BaggagePolicyResponse.builder()
                .id(baggagePolicy.getId())
                .name(baggagePolicy.getName())
                .description(baggagePolicy.getDescription())
                .cabinBaggageMaxWeight(baggagePolicy.getCabinBaggageMaxWeight())
                .cabinBaggagePieces(baggagePolicy.getCabinBaggagePieces())
                .cabinBaggageWeightPerPiece(baggagePolicy.getCabinBaggageWeightPerPiece())
                .cabinBaggageMaxDimension(baggagePolicy.getCabinBaggageMaxDimension())
                .checkInBaggageMaxWeight(baggagePolicy.getCheckInBaggageMaxWeight())
                .checkInBaggagePieces(baggagePolicy.getCheckInBaggagePieces())
                .checkInBaggageWeightPerPiece(baggagePolicy.getCheckInBaggageWeightPerPiece())
                .freeCheckedBagsAllowance(baggagePolicy.getFreeCheckedBagsAllowance())
                .priorityBaggage(baggagePolicy.getPriorityBaggage())
                .extraBaggageAllowance(baggagePolicy.getExtraBaggageAllowance())
                .airlineId(baggagePolicy.getAirlineId())
                .fareId(baggagePolicy.getFare() != null ? baggagePolicy.getFare().getId() : null)
                .createdAt(baggagePolicy.getCreatedAt())
                .updatedAt(baggagePolicy.getUpdatedAt())
                .build();
    }

    public static void updateEntity(BaggagePolicy baggagePolicy, BaggagePolicyRequest request) {
        if (baggagePolicy == null || request == null) {
            return;
        }

        if (request.getName() != null) baggagePolicy.setName(request.getName());
        if (request.getDescription() != null) baggagePolicy.setDescription(request.getDescription());
        if (request.getCabinBaggageMaxWeight() != null) baggagePolicy.setCabinBaggageMaxWeight(request.getCabinBaggageMaxWeight());
        if (request.getCabinBaggagePieces() != null) baggagePolicy.setCabinBaggagePieces(request.getCabinBaggagePieces());
        if (request.getCabinBaggageWeightPerPiece() != null) baggagePolicy.setCabinBaggageWeightPerPiece(request.getCabinBaggageWeightPerPiece());
        if (request.getCabinBaggageMaxDimension() != null) baggagePolicy.setCabinBaggageMaxDimension(request.getCabinBaggageMaxDimension());
        if (request.getCheckInBaggageMaxWeight() != null) baggagePolicy.setCheckInBaggageMaxWeight(request.getCheckInBaggageMaxWeight());
        if (request.getCheckInBaggagePieces() != null) baggagePolicy.setCheckInBaggagePieces(request.getCheckInBaggagePieces());
        if (request.getCheckInBaggageWeightPerPiece() != null) baggagePolicy.setCheckInBaggageWeightPerPiece(request.getCheckInBaggageWeightPerPiece());
        if (request.getFreeCheckedBagsAllowance() != null) baggagePolicy.setFreeCheckedBagsAllowance(request.getFreeCheckedBagsAllowance());
        if (request.getPriorityBaggage() != null) baggagePolicy.setPriorityBaggage(request.getPriorityBaggage());
        if (request.getExtraBaggageAllowance() != null) baggagePolicy.setExtraBaggageAllowance(request.getExtraBaggageAllowance());
    }
}
