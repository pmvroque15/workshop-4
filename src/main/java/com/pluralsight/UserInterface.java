package com.pluralsight;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private Dealership dealership;

    public UserInterface() {
        init();
    }

    private void init() {
        //Creating an instance of DealershipFileManagerClass

        //Getting the values needed from dealershipFileManager by using its getters
        //and assigning it to variable to pass in the dealership's constructor arguments

        this.dealership = DealershipFileManager.getDealership();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        String menu = """  
                                                =================================================================
                                                                         %s
                                                          %s   |   Phone: %s
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
                                                        Type "S" to sell a vehicle or "L" to lease a vehicle
                                                        Press 99 to quit%n
                """;

        //flag for the while loop
        boolean exit = false;
        do {

            //Menu

            try {
                System.out.println();
                System.out.printf(menu, dealership.getName(), dealership.getAddress(), dealership.getPhone());
                System.out.println();
                System.out.print("                                          What brings you in today: ");
                String choice = String.format(scanner.nextLine());
                switch (choice) {
                    case "1" -> processGetByPriceRequest();

                    case "2" -> processGetByMakeModelRequest();

                    case "3" -> processGetByYearRequest();

                    case "4" -> processGetByColorRequest();

                    case "5" -> processGetByMileageRequest();

                    case "6" -> processGetByVehicleTypeRequest();

                    case "7" -> processAllVehiclesRequest();

                    case "8" -> processAddVehicleRequest();

                    case "9" -> processRemoveVehicleRequest();

                    case "99" -> exit = true;

                    case "S" -> System.out.println("Sell");

                    case "L" -> System.out.println("Lease");

                    default -> System.err.println("Unexpected value: " + choice);

                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
            }
        } while (!exit);
    }

    public void processGetByPriceRequest() {
        try {
            System.out.println("Minimum Price: ");
            double minimumPriceInput = Double.parseDouble(scanner.nextLine());

            System.out.println("Maximum Price: ");
            double maximumPriceInput = Double.parseDouble(scanner.nextLine());

            if (minimumPriceInput < 0 || maximumPriceInput < 0) {
                System.err.println("Price cannot be negative. Try again.");
            }

            ArrayList<Vehicle> matchPrice = dealership.getVehiclesByPrice(minimumPriceInput, maximumPriceInput);

            if (matchPrice.isEmpty()) {
                System.err.println("No vehicles available within the price range.");
                return;
            }
            displayVehicles(matchPrice);
        } catch (NumberFormatException e) {
            System.err.println("Please enter a valid number.");
        }

        boolean running = false;
        do {

            System.out.println("                   Do you want to search for another car or go back to the menu? Type \"search\" to search another car or press X to go back.");
            String input = scanner.nextLine();

            running = !input.equalsIgnoreCase("search");

        } while (!running);
//        else if (minimumPriceInput < minimumPrice || max > maximumPrice) {
//            System.err.println("There are no available cars in this price range.");
//            System.err.printf("Valid range: $%.2f - $%.2f try again.%n", minimumPrice, maximumPrice);
//        } else {
//            running = true;
//        }
    }

    //todo: Try to make prompt if they want to search make or model or both
    public void processGetByMakeModelRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

        System.out.println("                  What specific make you're looking for? type the make of the vehicle otherwise press enter to skip");
        String make = scanner.nextLine();

        System.out.println("                  Do you have a particular model in mind? If yes, type the model otherwise press enter to skip.");
        String model = scanner.nextLine();

//        if (make.matches("[a-zA-Z0-9]+") || model.matches("[a-zA-Z0-9]+")) {
//            System.err.println("Please enter a valid input. Try again.");
//        }

        ArrayList<Vehicle> matchMakeModel = dealership.getVehiclesByMakeModel(make, model);
        if (matchMakeModel.isEmpty()) {
            System.out.println("Undecided? Here are the available vehicles: ");
            printHeader();

            for (Vehicle v : vehicles) {
                printVehicleFormat(v);
            }
        }
        displayVehicles(matchMakeModel);
    }

    public void processGetByYearRequest() {

        try {
            System.out.println("Minimum Year: ");
            int minimumYearInput = Integer.parseInt(scanner.nextLine());

            System.out.println("Maximum Year: ");
            int maximumYearInput = Integer.parseInt(scanner.nextLine());

            ArrayList<Vehicle> matchYear = dealership.getVehiclesByYear(minimumYearInput, maximumYearInput);

            if (matchYear.isEmpty()) {
                System.err.println("No vehicles available in the years given. Try again.");
                return;
            }
            displayVehicles(matchYear);

        } catch (NumberFormatException e) {
            System.err.println("Enter a valid year. Try again.");
        }
    }

    public void processGetByColorRequest() {

        System.out.println("What color of a vehicle you're looking for? ");
        String colorInput = scanner.nextLine();

        if (!colorInput.matches("[a-zA-Z\\s]+")) {
            System.err.println("Please enter a valid input. Try again.");
        }

        ArrayList<Vehicle> matches = dealership.getVehiclesByColor(colorInput);

        if (matches.isEmpty()) {
            System.err.printf("No %s color available on the vehicle list. Try again%n", colorInput);
            return;
        }

        displayVehicles(matches);

    }

    public void processGetByMileageRequest() {
//        double minimumMileage = 0;
//        double maximumMileage = 0;
        try {
            System.out.println("Minimum Mileage: ");
            int minimumMileageInput = Integer.parseInt(scanner.nextLine());

            if (minimumMileageInput < 0) {
                System.err.println("Mileage cannot be negative. Try Again.");

            }

//                if (minimumMileageInput < minimumMileage) {
//                    System.err.printf("Minimum mileage of a vehicle available is %s Try again.%n", minimumMileage);
//
//                }

            System.out.println("Maximum Mileage: ");
            int maximumMileageInput = Integer.parseInt(scanner.nextLine());

            if (maximumMileageInput < 0) {
                System.err.println("Mileage cannot be negative. Try Again.");

            }

//                if (maximumMileageInput > maximumMileage) {
//                    System.err.printf("Maximum mileage of a vehicle available is %s Try again.%n", maximumMileage);
//
//                }

//                if ( minimumMileageInput > maximumMileageInput) {
//                    System.err.println("Maximum mileage cannot be less than minimum mileage. Try Again.");
//                }
            ArrayList<Vehicle> matchMileageRange = dealership.getVehiclesByMileage(minimumMileageInput, maximumMileageInput);

            if (matchMileageRange.isEmpty()) {
                System.err.println("No vehicles found in that mileage range.");
                return;
            }
            displayVehicles(matchMileageRange);
        } catch (NumberFormatException e) {
            System.err.println("Enter a valid number. Try again.");
        }
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("What vehicle type you are looking for: ");
        String vehicleType = scanner.nextLine();

        ArrayList<Vehicle> matchVehicleType = dealership.getVehiclesByType(vehicleType);

        if (matchVehicleType.isEmpty()) {
            System.err.printf("No vehicle available for %s", vehicleType);
            return;
        }

        displayVehicles(matchVehicleType);
    }

    public void processAllVehiclesRequest() {
        ArrayList<Vehicle> v = dealership.getAllVehicles();
        displayVehicles(v);
    }

    public void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        DealershipFileManager dealershipFileManager = new DealershipFileManager();

        System.out.println("Please enter the VIN number: ");
        int vin = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter the year of the vehicle: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter the make of the vehicle: ");
        String make = scanner.nextLine();

        System.out.println("Please enter the model of the vehicle: ");
        String model = scanner.nextLine();

        System.out.println("Please enter the vehicle type: ");
        String vehicleType = scanner.nextLine();

        System.out.println("Please enter the color of the vehicle: ");
        String color = scanner.nextLine();

        System.out.println("Please enter the mileage of the vehicle: ");
        int odometer = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter the price of the vehicle: ");
        double price = Double.parseDouble(scanner.nextLine());

        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

        dealershipFileManager.saveDealership(v);
    }

    public void processRemoveVehicleRequest() {

        System.out.println("Enter the VIN number you wish to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.removerVehicleByVin(vin);

        System.out.println("If the VIN existed, it has been removed from file.");

    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        System.out.println();
        printHeader();
        for (Vehicle v : vehicles) {
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
                            ==================================================================================================================
                """;

        System.out.printf(header, "Vin Number", "Year", "Make", "Model", "Vehicle Type", "Color", "Odometer", "Price");
    }

    public void printVehicleFormat(Vehicle v) {
        //Todo: make an if statement for colors!!!
        //Header
        String elements = """
                                   %-13s   %-8s   %-13s   %-12s   %-12s   %-9s   %-8s   $%-7.2f
                                  _________________________________________________________________________________________________________%n
                """;
        System.out.printf(elements, v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
    }
}
