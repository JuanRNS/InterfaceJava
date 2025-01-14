package app;

import entities.CarRental;
import entities.Vehicle;
import services.BrazilTax;
import services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados para o aluguel: ");
        System.out.println("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.println("Retirada do carro (dd/MM/yyyy): ");
        LocalDateTime carDateStart = LocalDateTime.parse(sc.nextLine(),fmt);
        System.out.println("Retorno do carro (dd/MM/yyyy): ");
        LocalDateTime carDateFinish = LocalDateTime.parse(sc.nextLine(),fmt);

        CarRental car = new CarRental(carDateStart,carDateFinish, new Vehicle(carModel));

        System.out.println("Digite o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.println("Digite o preço por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService renService = new RentalService(pricePerDay,pricePerHour,new BrazilTax());

        renService.processInvoice(car);

        System.out.println("Fatura: ");
        System.out.println("Pagamento básico: " + car.getInvoice().getBasicPayment());
        System.out.println("Imposto: " + car.getInvoice().getTax());
        System.out.println("Pagamento total: " + car.getInvoice().getTotalPayment());
    }
}