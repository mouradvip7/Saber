package com.example.taxiservice.repo;

import com.example.taxiservice.model.TaxiBooking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiBookingRepository extends CrudRepository<TaxiBooking, String> {

}
