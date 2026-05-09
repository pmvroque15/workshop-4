package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {
    private Dealership dealership;

    public UserInterface() {
        init();
    }

    private void init() {
        //Creating an instance of DealershipFileManagerClass
        DealershipFileManager dealershipFileManager = new DealershipFileManager();

        //Getting the values needed from dealershipFileManager by using its getters
        //and assigning it to variable to pass in the dealership's constructor arguments

        this.dealership = dealershipFileManager.getDealership();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        String menu = """  
                                                =================================================================
                                                                       D & B Used Cars
                                                =================================================================
                                                                Please select an option:
                                                        Press 1 to find vehicles within a price range
                                                        Press 2 to find vehicles by make / model
                                                        Press 3 to find vehicles by year range
                                                        Press 4 to find vehicles by color
                                                        Press 5 to find vehicles by mileage range
                                                        Press 6 to find vehicles by type(car, truck, SUV, van)
                                                        Press 7 to list all vehicles
                                                        Press 8 to add a vehicle
                                                        Press 9 to remove a vehicle
                                                        Press 99 to quit
                """;

        //flag for the while loop
        boolean isRunning = true;
        do {

            //Menu
            System.out.println();
            System.out.println(menu);
            System.out.println();
            System.out.print("                                          Pick an option: ");
            int number = Integer.parseInt(scanner.nextLine());

            switch (number) {
                case 1 -> processGetByPriceRequest();

                case 2 -> processGetByMakeModelRequest();

                case 3 -> processGetByYearRequest();

                case 4 -> processGetByColorRequest();

                case 5 -> processGetByMileageRequest();

                case 6 -> processGetByVehicleTypeRequest();

                case 7 -> processAllVehiclesRequest();

                case 8 -> processAddVehicleRequest();

                case 9 -> processRemoveVehicleRequest();

                case 99 -> isRunning = false;

                default -> throw new IllegalStateException("Unexpected value: " + number);

            }
        } while (isRunning);
    }

    public void processGetByPriceRequest() {

    }

    public void processGetByMakeModelRequest() {

    }

    public void processGetByYearRequest() {

    }

    public void processGetByColorRequest() {

    }

    public void processGetByMileageRequest() {

    }

    public void processGetByVehicleTypeRequest() {

    }

    public void processAllVehiclesRequest() {
        ArrayList<Vehicle> v = dealership.getAllVehicles();
        displayVehicles(v);
    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        System.out.println();
        printHeader();
        for (Vehicle v: vehicles) {
            printVehicleFormat(v);
        }
        System.out.println();
    }

    public void exit() {
        System.out.println("Thank you for using the app!");
    }

    public void printHeader() {
        String header = """
                            ==================================================================================================================
                                 %-15s   %-10s   %-10s   %-10s   %-15s   %-8s   %-10s   %-7s
                            ==================================================================================================================%n
                """;

        System.out.printf(header, "Vin Number", "Year", "Make", "Model", "Vehicle Type","Color", "Odometer", "Price");
    }
    public void printVehicleFormat(Vehicle v) {
        //Todo: make an if statement for colors!!!
        //Header
        String elements = """
                                   %-13s   %-8s   %-13s   %-12s   %-12s   %-9s   %-8s   $%-7.2f%n
                """;
        System.out.printf(elements, v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
    }
}