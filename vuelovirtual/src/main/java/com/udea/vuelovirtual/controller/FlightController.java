package com.udea.vuelovirtual.controller;

import com.udea.vuelovirtual.model.Flight;
import com.udea.vuelovirtual.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private FlightService flightService;



    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam("startDate") String startDate,
                                      @RequestParam("endDate") String endDate,
                                      @RequestParam(value="origin", required = false) String origin,
                                      @RequestParam(value="destination", required = false) String destination,
                                      @RequestParam(value="maxPrice", required = false) Double maxPrice,
                                      @RequestParam(value="equipmentType", required = false) String equipmentType,
                                      @RequestParam(value="flightClass", required = false) String flightClass,
                                      @RequestParam(value="maxPassengers", required = false) Integer maxPassengers
                                      ){
        return flightService.findFlights(LocalDate.parse(startDate), LocalDate.parse(endDate), origin, destination,
                maxPrice, equipmentType, flightClass, maxPassengers);

    }

}


