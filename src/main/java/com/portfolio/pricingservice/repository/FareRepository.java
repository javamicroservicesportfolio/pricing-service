package com.portfolio.pricingservice.repository;

import com.portfolio.payload.response.FareResponse;
import com.portfolio.pricingservice.model.Fare;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface FareRepository extends JpaRepository<Fare, Long> {
    boolean existsByFlightIdAndCabinClassIdAndName(Long flightId, Long cabinClassId, String name);

    boolean existsByFlightIdAndCabinClassIdAndNameAndIdNot(Long flightId, Long cabinClassId, String name, Long id);
    List<Fare> findByFlightIdAndCabinClassId(Long flightId, Long cabinClassId);

    List<Fare> findByFlightIdInAndCabinClassId(Iterable<Long> flightIds, Long cabinClassId);
}
