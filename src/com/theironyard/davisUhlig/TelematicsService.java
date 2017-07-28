package com.theironyard.davisUhlig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by duhlig on 7/21/17.
 */
public class TelematicsService {
    String json;
//    String html;
    String line;
    VehicleInfo infoForVehicle;
    double vehicleMiles;
    double vehicleGasConsumption;
    double vehicleEngineSize;
    double vehicleLastOdometer;
    double averageVehicleMiles;
    double averageVehicleGasConsumption;
    double averageVehicleLastOdometer;
    double averageVehicleEngineSize;

    ArrayList<VehicleInfo> userVehicleInfo = new ArrayList<>();
    void report(VehicleInfo vehicleInfo){

        ObjectMapper mapper = new ObjectMapper();
        try {
            String name = vehicleInfo.getVin() + ".json";
            mapper.writeValue(new File(name), vehicleInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(".");
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {

                try {
                    ObjectMapper readMapper = new ObjectMapper();
                    infoForVehicle = readMapper.readValue(f, VehicleInfo.class);
                    userVehicleInfo.add(infoForVehicle);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Scanner fileScanner = new Scanner(f);
                    List<String> fileContents = new ArrayList<>();
                    while (fileScanner.hasNext()) {
                        fileContents.add(fileScanner.nextLine());
                    }
                    json = fileContents.toArray(new String[0])[0]; //Converts the list to an array
                    System.out.println(json);
                }
                catch (FileNotFoundException ex) {
                    System.out.println("Could not find file *" + json + "*");
                    ex.printStackTrace();

                }
            }
        }

        ReadHtmlFile htmlFile = new ReadHtmlFile();
            htmlFile.readFile();

        for (VehicleInfo info : userVehicleInfo){
            vehicleMiles += info.getOdometer();
            vehicleGasConsumption += info.getConsumption();
            vehicleLastOdometer += info.getOdometerReadingForLastOilChange();
            vehicleEngineSize += info.getEngineSizeLiters();
            averageVehicleMiles = vehicleMiles/userVehicleInfo.size();
            averageVehicleGasConsumption = vehicleGasConsumption/userVehicleInfo.size();
            averageVehicleLastOdometer = vehicleLastOdometer/userVehicleInfo.size();
            averageVehicleEngineSize = vehicleEngineSize/userVehicleInfo.size();
        }

        File dashboardHtml = new File("dashboard.html");
        Document doc = null;
        try {
            doc = Jsoup.parse(dashboardHtml, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------");
        Element odometerAverage = doc.getElementById("odometerAverage");
        Element consumptionAverage = doc.getElementById("consumptionAverage");
        Element oilChangeAverage = doc.getElementById("oilChangeAverage");
        Element engineSizeAverage = doc.getElementById("engineSizeAverage");

        odometerAverage.html("<td id=\"odometerAverage\" align=\"center\">" + String.format("%.1f", averageVehicleMiles) + "</td>");
        consumptionAverage.html("<td id=\"consumptionAverage\" align=\"center\">" + String.format("%.1f", averageVehicleGasConsumption) + "</td>");
        oilChangeAverage.html("<td id=\"oilChangeAverage\" align=\"center\">" + String.format("%.1f", averageVehicleLastOdometer) + "</td>");
        engineSizeAverage.html("<td id=\"engineSizeAverage\" align=\"center\">" + String.format("%.1f", averageVehicleEngineSize) + "</td>");

        System.out.println(odometerAverage);

        System.out.println(odometerAverage.html());
        System.out.println(consumptionAverage.html());
        System.out.println(oilChangeAverage.html());
        System.out.println(engineSizeAverage.html());

        Element historyTable = doc.getElementById("historyTable");

        historyTable.html(" ");
        Element historyHeaderTr = historyTable.appendElement("tr");
        Element vinHeader = historyHeaderTr.appendElement("th");
        Element odometerHeader = historyHeaderTr.appendElement("th");
        Element consumptionHeader = historyHeaderTr.appendElement("th");
        Element oilChangeHeader = historyHeaderTr.appendElement("th");
        Element engineSizeHeader = historyHeaderTr.appendElement("th");

        vinHeader.html("VIN");
        odometerHeader.html("Odometer (miles)");
        consumptionHeader.html("Consumption (gallons)");
        oilChangeHeader.html("Last Oil Change");
        engineSizeHeader.html("Engine Size (liters)");

        for (int i = 0; i < userVehicleInfo.size(); i++){
            Element historyValues = historyTable.appendElement("tr");
            Element vinValue = historyValues.appendElement("td");
            Element odometerValue = historyValues.appendElement("td");
            Element consumptionValue = historyValues.appendElement("td");
            Element oilChangeValue = historyValues.appendElement("td");
            Element engineSizeValue = historyValues.appendElement("td");
            vinValue.html("<td id=\"vin\" align=\"center\">" + userVehicleInfo.get(i).getVin() + "</td>");
            odometerValue.html("<td id=\"odometer\" align=\"center\">" + String.format("%.1f", userVehicleInfo.get(i).getOdometer()) + "</td>");
            consumptionValue.html(" <td id=\"consumption\" align=\"center\">" + String.format("%.1f", userVehicleInfo.get(i).getConsumption()) + "</td>");
            oilChangeValue.html("<td id=\"oilChange\" align=\"center\">" + String.format("%.1f", userVehicleInfo.get(i).getOdometerReadingForLastOilChange()) + "</td>");
            engineSizeValue.html("<td id=\"engineSize\" align=\"center\">" + String.format("%.1f", userVehicleInfo.get(i).getEngineSizeLiters()) + "</td>");

        }
        String html = doc.html();

        try {
            FileWriter dashboardFileWriter = new FileWriter(dashboardHtml);
            dashboardFileWriter.write(html);
            dashboardFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

