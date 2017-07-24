package com.theironyard.davisUhlig;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter Your VIN");
        int vinInput = scanner.nextInt();

        System.out.println("Please Enter Your Mileage");
        double odometerInput = scanner.nextDouble();

        System.out.println("Please Enter Your Gallons of Gas Consumed");
        double  consumptionInput = scanner.nextDouble();

        System.out.println("Please Enter Your Odometer Reading from Your Last Oil Change");
        double oilChangeInput = scanner.nextDouble();

        System.out.println("Please Enter Engine Size in Liters");
        double engineSizeInput = scanner.nextDouble();

        VehicleInfo newVehicle = new VehicleInfo();

        newVehicle.setVin(vinInput);
        newVehicle.setOdometer(odometerInput);
        newVehicle.setConsumption(consumptionInput);
        newVehicle.setOdometerReadingForLastOilChange(oilChangeInput);
        newVehicle.setEngineSizeLiters(engineSizeInput);

        TelematicsService newService = new TelematicsService();

        newService.report(newVehicle);
    }
}
