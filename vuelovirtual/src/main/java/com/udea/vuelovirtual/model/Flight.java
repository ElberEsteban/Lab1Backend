package com.udea.vuelovirtual.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity

public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String origin;
    private String destination;
    private LocalDate date;
    private double price;
    private int maxPassengers;
    private double cabinPriceIncrement;
    private double holdPriceIncrement;
    private double executiveClassIncrement;

    public Flight() {
    }

    public Flight(long id, String origin, String destination, LocalDate date, double price, int maxPassengers,
                  double cabinPriceIncrement, double holdPriceIncrement, double executiveClassIncrement ) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public double getCabinPriceIncrement() {
        return cabinPriceIncrement;
    }

    public void setCabinPriceIncrement(double cabinPriceIncrement) {
        this.cabinPriceIncrement = cabinPriceIncrement;
    }

    public double getHoldPriceIncrement() {
        return holdPriceIncrement;
    }

    public void setHoldPriceIncrement(double holdPriceIncrement) {
        this.holdPriceIncrement = holdPriceIncrement;
    }

    public double getExecutiveClassIncrement() {
        return executiveClassIncrement;
    }

    public void setExecutiveClassIncrement(double executiveClassIncrement) {
        this.executiveClassIncrement = executiveClassIncrement;
    }

    //IDIOMS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return getId() == flight.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", maxPassengers=" + maxPassengers +
                ", cabinPriceIncrement=" + cabinPriceIncrement +
                ", holdPriceIncrement=" + holdPriceIncrement +
                ", executiveClassIncrement=" + executiveClassIncrement +
                '}';
    }
}
