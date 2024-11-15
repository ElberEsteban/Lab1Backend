package com.udea.vuelovirtual.service;

import com.udea.vuelovirtual.model.Flight;
import com.udea.vuelovirtual.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findFlights(LocalDate startDate, LocalDate endDate, String origin,
                                    String destination, Double maxPrice,
                                    String equipmentType, String flightClass, Integer maxPassengers) {

        // Obtenemos los vuelos en el rango de fechas
        List<Flight> flights = flightRepository.findByDateBetween(startDate, endDate);

        // Ajustamos el precio según el tipo de equipaje y tipo de clase seleccionados
        flights.forEach(flight -> {
            double basePrice = flight.getPrice(); // Precio original

            // Ajuste según tipo de equipaje
            if (equipmentType != null) {
                switch (equipmentType.toLowerCase()) {
                    case "cabin":
                        basePrice += flight.getCabinPriceIncrement();
                        break;
                    case "hold":
                        basePrice += flight.getHoldPriceIncrement();
                        break;
                    default: // Caso "hand" o no especificado (sin incremento)
                        break;
                }
            }

            // Ajuste según tipo de clase
            if (flightClass != null) {
                switch (flightClass.toLowerCase()) {
                    case "executive":
                        basePrice += flight.getExecutiveClassIncrement();
                        break;
                    default: // Caso "economic" o no especificado (sin incremento)
                        break;
                }
            }

            // Actualizamos el precio del vuelo con el precio ajustado
            flight.setPrice(basePrice);
        });

        // Filtrar por origen
        if (origin != null && !origin.isEmpty()) {
            flights = flights.stream()
                    .filter(flight -> flight.getOrigin().equalsIgnoreCase(origin))
                    .collect(Collectors.toList());
        }

        // Filtrar por destino
        if (destination != null && !destination.isEmpty()) {
            flights = flights.stream()
                    .filter(flight -> flight.getDestination().equalsIgnoreCase(destination))
                    .collect(Collectors.toList());
        }

        // Filtrar por precio máximo (luego de ajustar el precio)
        if (maxPrice != null) {
            flights = flights.stream()
                    .filter(flight -> flight.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }

        // Filtrar por número máximo de pasajeros
        if (maxPassengers != null) {
            flights = flights.stream()
                    .filter(flight -> flight.getMaxPassengers() <= maxPassengers)
                    .collect(Collectors.toList());
        }

        return flights;
    }
}
