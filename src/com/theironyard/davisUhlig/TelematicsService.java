package com.theironyard.davisUhlig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by duhlig on 7/21/17.
 */
public class TelematicsService {
    String json;
    String html;
    String line;
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

    }

}

