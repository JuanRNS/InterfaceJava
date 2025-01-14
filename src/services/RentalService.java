package services;


import entities.CarRental;
import entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerDay;
    private Double pricePerHour;

    private final Tax tax;

    public RentalService(Double pricePerDay, Double pricePerHour, Tax tax) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.tax = tax;
    }

    public void processInvoice(CarRental carRental) {

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;
        if (hours <= 12.0) {
            basicPayment = pricePerHour * Math.ceil(hours);
        }
        else {
            basicPayment = pricePerDay * Math.ceil(hours / 24);
        }

        double taxService = tax.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, taxService));
    }
}
