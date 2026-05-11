package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    private final String INVENTORY_LIST = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(INVENTORY_LIST));
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
                String make = split[2];
                String model = split[3];
                String vehicleType = split[4];
                String color = split[5];
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

    public void saveDealership(Vehicle v) {

        File file = new File(INVENTORY_LIST);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(INVENTORY_LIST, true));

            if (file.exists() && file.length() > 0) {
                bufferedWriter.newLine();
            }

            bufferedWriter.write(v.toFileString());

            bufferedWriter.close();

        } catch (IOException e) {
            System.err.println("I/O error. Try Again");
        }

    }

    public void removerVehicleByVin(int vin) {
        File inputFile = new File(INVENTORY_LIST);
        File temporaryFile = new File("src/main/resources/inventory_temporary.csv");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(temporaryFile));


            String line = "";

            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {

                String[] parts = line.split("\\|");

                int vinNumber = Integer.parseInt(parts[0]);

                if (vinNumber != vin) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }

            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());

        }
            inputFile.delete();
            temporaryFile.renameTo(inputFile);

    }

}