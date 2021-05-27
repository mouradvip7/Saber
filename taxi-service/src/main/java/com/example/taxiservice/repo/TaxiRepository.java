package com.example.taxiservice.repo;


import com.example.taxiservice.model.Taxi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends CrudRepository<Taxi, String> {

}
