package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    public static Dealership getDealership() {
        String fileName = "src/main/resources/inventory.csv";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            line = line.trim();
            String[] splitLine = line.split("\\|");

            String name = splitLine[0];
            String address = splitLine[1];
            String phoneNumber = splitLine[2];

            Dealership dealership = new Dealership(name, address, phoneNumber);

            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("\\|");
                line = line.trim();

                int vin = Integer.parseInt(split[0]);
                int year = Integer.parseInt(split[1]);
                String make = split[3];
                String model = split[4];
                String vehicleType = split[5];
                String color = split[6];
                int odometer = Integer.parseInt(split[6]);
                double price = Double.parseDouble(split[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                dealership.addVehicle(vehicle);
            }

            bufferedReader.close();
            return dealership;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}