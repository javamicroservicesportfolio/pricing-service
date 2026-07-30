package com.portfolio.pricingservice.mapper;

import com.portfolio.embeddable.*;
import com.portfolio.payload.request.FareRequest;
import com.portfolio.payload.response.FareResponse;
import com.portfolio.pricingservice.model.Fare;

public class FareMapper {
    public static Fare toEntity(FareRequest fareRequest) {

        if (fareRequest == null) {
            return null;
        }

        Double calculatedPrice = fareRequest.getCurrentPrice();
        if (calculatedPrice == null) {
            calculatedPrice = fareRequest.getBaseFare() + fareRequest.getTaxesAndFees() + fareRequest.getAirlineFees();
        }

        SeatBenefits seatBenefits = SeatBenefits.builder()
                .extraSeatSpace(bool(fareRequest.getExtraSeatSpace()))
                .preferredSeatChoice(bool(fareRequest.getPreferredSeatChoice()))
                .advanceSeatSelection(bool(fareRequest.getAdvanceSeatSelection()))
                .guaranteedSeatTogether(bool(fareRequest.getGuaranteedSeatTogether()))
                .build();

        BoardingBenefits boardingBenefits = BoardingBenefits.builder()
                .priorityBoarding(bool(fareRequest.getPriorityBoarding()))
                .priorityCheckin(bool(fareRequest.getPriorityCheckin()))
                .fastTrackSecurity(bool(fareRequest.getFastTrackSecurity()))
                .build();

        InFlightBenefits inFlightBenefits = InFlightBenefits.builder()
                .complimentaryMeals(bool(fareRequest.getComplimentaryMeals()))
                .premiumMealChoice(bool(fareRequest.getPremiumMealChoice()))
                .inFlightInternet(bool(fareRequest.getInFlightInternet()))
                .inFlightEntertainment(bool(fareRequest.getInFlightEntertainment()))
                .complimentaryBeverages(bool(fareRequest.getComplimentaryBeverages()))
                .build();

        FlexibilityBenefits flexibilityBenefits = FlexibilityBenefits.builder()
                .freeDateChange(bool(fareRequest.getFreeDateChange()))
                .partialRefund(bool(fareRequest.getPartialRefund()))
                .fullRefund(bool(fareRequest.getFullRefund()))
                .build();

        PremiumServiceBenefits premiumServiceBenefits = PremiumServiceBenefits.builder()
                .loungeAccess(bool(fareRequest.getLoungeAccess()))
                .airportTransfer(bool(fareRequest.getAirportTransfer()))
                .build();

        return Fare.builder()
                .name(fareRequest.getName())
                .rbdCode(fareRequest.getRbdCode())
                .flightId(fareRequest.getFlightId())
                .cabinClassId(fareRequest.getCabinClassId())
                .baseFare(fareRequest.getBaseFare())
                .taxesAndFees(fareRequest.getTaxesAndFees())
                .airlineFees(fareRequest.getAirlineFees())
                .currentPrice(calculatedPrice)
                .fareLabel(fareRequest.getFareLabel())
                .seatBenefits(seatBenefits)
                .boardingBenefits(boardingBenefits)
                .inFlightBenefits(inFlightBenefits)
                .flexibilityBenefits(flexibilityBenefits)
                .premiumServiceBenefits(premiumServiceBenefits)
                .build();
    }

    public static FareResponse toResponse(Fare fare) {
        if (fare == null) {
            return null;
        }

        return FareResponse.builder()
                .id(fare.getId())
                .name(fare.getName())
                .rbdCode(fare.getRbdCode())
                .flightId(fare.getFlightId())
                .cabinClassId(fare.getCabinClassId())
                .cabinClassType(fare.getCabinClass())
                .baseFare(fare.getBaseFare())
                .taxesAndFees(fare.getTaxesAndFees())
                .airlineFees(fare.getAirlineFees())
                .currentPrice(fare.getCurrentPrice())
                .totalPrice(fare.getTotalPrice())
                .fareLabel(fare.getFareLabel())
                .extraSeatSpace(fare.getSeatBenefits().getExtraSeatSpace())
                .preferredSeatChoice(fare.getSeatBenefits().getPreferredSeatChoice())
                .advanceSeatSelection(fare.getSeatBenefits().getAdvanceSeatSelection())
                .guaranteedSeatTogether(fare.getSeatBenefits().getGuaranteedSeatTogether())
                .priorityBoarding(fare.getBoardingBenefits().getPriorityBoarding())
                .priorityCheckin(fare.getBoardingBenefits().getPriorityCheckin())
                .fastTrackSecurity(fare.getBoardingBenefits().getFastTrackSecurity())
                .complimentaryMeals(fare.getInFlightBenefits().getComplimentaryMeals())
                .premiumMealChoice(fare.getInFlightBenefits().getPremiumMealChoice())
                .inFlightInternet(fare.getInFlightBenefits().getInFlightInternet())
                .inFlightEntertainment(fare.getInFlightBenefits().getInFlightEntertainment())
                .complimentaryBeverages(fare.getInFlightBenefits().getComplimentaryBeverages())
                .freeDateChange(fare.getFlexibilityBenefits().getFreeDateChange())
                .partialRefund(fare.getFlexibilityBenefits().getPartialRefund())
                .fullRefund(fare.getFlexibilityBenefits().getFullRefund())
                .loungeAccess(fare.getPremiumServiceBenefits().getLoungeAccess())
                .airportTransfer(fare.getPremiumServiceBenefits().getAirportTransfer())
                // To do, watch fare rules and baggage policy
                //.fareRulesId(fare.getFareRulesId())
                //.fareRules(fare.getFareRules())
                //.baggagePolicy(fare.getBaggagePolicy())
                .createdAt(fare.getCreatedAt())
                .updatedAt(fare.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FareRequest fareRequest, Fare fare) {
        if (fareRequest == null || fare == null) {
            return;
        }

        Double calculatedPrice = fareRequest.getCurrentPrice();
        if (calculatedPrice == null) {
            calculatedPrice = fareRequest.getBaseFare() + fareRequest.getTaxesAndFees() + fareRequest.getAirlineFees();
        }

        if (fareRequest.getName() != null) {
            fare.setName(fareRequest.getName());
        }
        if (fareRequest.getRbdCode() != null) {
            fare.setRbdCode(fareRequest.getRbdCode());
        }
        if (fareRequest.getFlightId() != null) {
            fare.setFlightId(fareRequest.getFlightId());
        }
        if (fareRequest.getCabinClassId() != null) {
            fare.setCabinClassId(fareRequest.getCabinClassId());
        }
        if (fareRequest.getBaseFare() != null) {
            fare.setBaseFare(fareRequest.getBaseFare());
        }
        if (fareRequest.getTaxesAndFees() != null) {
            fare.setTaxesAndFees(fareRequest.getTaxesAndFees());
        }
        if (fareRequest.getAirlineFees() != null) {
            fare.setAirlineFees(fareRequest.getAirlineFees());
        }
        fare.setCurrentPrice(calculatedPrice);
        if (fareRequest.getFareLabel() != null) {
            fare.setFareLabel(fareRequest.getFareLabel());
        }

        // Update embedded benefits
        if (fareRequest.getExtraSeatSpace() != null) {
            fare.getSeatBenefits().setExtraSeatSpace(bool(fareRequest.getExtraSeatSpace()));
        }
        if (fareRequest.getPreferredSeatChoice() != null) {
            fare.getSeatBenefits().setPreferredSeatChoice(bool(fareRequest.getPreferredSeatChoice()));
        }
        if (fareRequest.getAdvanceSeatSelection() != null) {
            fare.getSeatBenefits().setAdvanceSeatSelection(bool(fareRequest.getAdvanceSeatSelection()));
        }
        if (fareRequest.getGuaranteedSeatTogether() != null) {
            fare.getSeatBenefits().setGuaranteedSeatTogether(bool(fareRequest.getGuaranteedSeatTogether()));
        }

        if (fareRequest.getPriorityBoarding() != null) {
            fare.getBoardingBenefits().setPriorityBoarding(bool(fareRequest.getPriorityBoarding()));
        }
        if (fareRequest.getPriorityCheckin() != null) {
            fare.getBoardingBenefits().setPriorityCheckin(bool(fareRequest.getPriorityCheckin()));
        }
        if (fareRequest.getFastTrackSecurity() != null) {
            fare.getBoardingBenefits().setFastTrackSecurity(bool(fareRequest.getFastTrackSecurity()));
        }

        if (fareRequest.getComplimentaryMeals() != null) {
            fare.getInFlightBenefits().setComplimentaryMeals(bool(fareRequest.getComplimentaryMeals()));
        }
        if (fareRequest.getPremiumMealChoice() != null) {
            fare.getInFlightBenefits().setPremiumMealChoice(bool(fareRequest.getPremiumMealChoice()));
        }
        if (fareRequest.getInFlightInternet() != null) {
            fare.getInFlightBenefits().setInFlightInternet(bool(fareRequest.getInFlightInternet()));
        }
        if (fareRequest.getInFlightEntertainment() != null) {
            fare.getInFlightBenefits().setInFlightEntertainment(bool(fareRequest.getInFlightEntertainment()));
        }
        if (fareRequest.getComplimentaryBeverages() != null) {
            fare.getInFlightBenefits().setComplimentaryBeverages(bool(fareRequest.getComplimentaryBeverages()));
        }

        if (fareRequest.getFreeDateChange() != null) {
            fare.getFlexibilityBenefits().setFreeDateChange(bool(fareRequest.getFreeDateChange()));
        }
        if (fareRequest.getPartialRefund() != null) {
            fare.getFlexibilityBenefits().setPartialRefund(bool(fareRequest.getPartialRefund()));
        }
        if (fareRequest.getFullRefund() != null) {
            fare.getFlexibilityBenefits().setFullRefund(bool(fareRequest.getFullRefund()));
        }

        if (fareRequest.getLoungeAccess() != null) {
            fare.getPremiumServiceBenefits().setLoungeAccess(bool(fareRequest.getLoungeAccess()));
        }
        if (fareRequest.getAirportTransfer() != null) {
            fare.getPremiumServiceBenefits().setAirportTransfer(bool(fareRequest.getAirportTransfer()));
        }
    }

    private static boolean bool(Boolean value) {
        return value != null && value;
    }
}
