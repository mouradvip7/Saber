package com.example.taximodel.dto.request;

import com.example.taximodel.enums.TaxiType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBookedEventDTO {

    private String taxiBookingId = UUID.randomUUID().toString();

    private LocationDTO start;

    private LocationDTO end;

    private Date bookedTime = new Date();

    private Long customerId;

    private TaxiType taxiType;

}

