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


//<html>
//<title>Vehicle Telematics Dashboard</title>
//<body>
//<h1 id = "averages" align="center">Averages for # vehicles</h1>
//<table id="averagesTable" align="center">
//<tr>
//<th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>
//</tr>
//<tr>
//<td id="odometerAverage" align="center">#</td>
//<td id="consumptionAverage" align="center">#</td>
//<td id="oilChangeAverage" align="center">#</td>
//<td id="engineSizeAverage" align="center">#</td>
//</tr>
//</table>
//<h1 align="center">History</h1>
//<table id="historyTable" align="center" border="1">
//<tr>
//<th>VIN</th>
//<th>Odometer (miles)</th>
//<th>Consumption (gallons)</th>
//<th>Last Oil Change</th>
//<th>Engine Size (liters)</th>
//</tr>
//<tr>
//<td id="vin" align="center">#</td>
//<td id="odometer" align="center">#</td>
//<td id="consumption" align="center">#</td>
//<td id="oilChange" align="center">#</td>
//<td id="engineSize" align="center">#</td>
//</tr>
//<tr>
//<td align="center">45435</td>
//<td align="center">123</td>
//<td align="center">234</td>
//<td align="center">345</td>
//<td align="center">4.5</td>
//</tr>
//</table>
//</body>
//</html>
