package com.pluralsight;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private Dealership dealership;
    ArrayList<Vehicle> vehicles;

    public UserInterface() {
        init();
        vehicles = dealership.getAllVehicles();
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

                    case 99 -> exit = true;

                    default -> System.err.println("Unexpected value: " + number);

                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
            }
        } while (!exit);
    }

    public void processGetByPriceRequest() {
        double minimumPrice = Double.MAX_VALUE;
        double maximumPrice = 0;

        for (Vehicle v : vehicles) {
            double currentPrice = v.getPrice();
            //checking the lowest price in the arraylist of vehicles, then compare: if the input price is lower than the minimum price in the list, then throw an error.
            if (currentPrice < minimumPrice) {
                minimumPrice = currentPrice;
            }

            //Vice versa, checking the input if the user entered an amount that is greater than the maximum price in the list, then throw an error
            if (currentPrice > maximumPrice) {
                maximumPrice = currentPrice;
            }
        }

        boolean running = false;
        do {

            try {

                System.out.println("Minimum Price: ");
                double minimumPriceInput = Double.parseDouble(scanner.nextLine());

                System.out.println("Maximum Price: ");
                double maximumPriceInput = Double.parseDouble(scanner.nextLine());

                if (minimumPriceInput < 0 || maximumPriceInput < 0) {

                    System.err.println("Price cannot be negative. Try again.");

                } else if (minimumPriceInput < minimumPrice || maximumPriceInput > maximumPrice) {
                    System.err.println("There are no available cars in this price range.");
                    System.err.printf("Valid range: $%.2f - $%.2f try again.%n", minimumPrice, maximumPrice);
                } else {
                    running = true;
                }

                System.out.println("Here's an available vehicle for you: ");
                printHeader();

                for (Vehicle v : vehicles) {
                    if (minimumPriceInput >= v.getPrice() && maximumPriceInput <= v.getPrice()) {
                        printVehicleFormat(v);
                    }
                }

                System.out.println("                   Do you want to search for another car or go back to the menu? Press V to search another car or press X to go back.");
                String input = scanner.nextLine();

                running = !input.equalsIgnoreCase("v");
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
            }

        } while (!running);
    }

    //todo: Try to make prompt if they want to search make or model or both
    public void processGetByMakeModelRequest() {
        boolean running = true;

        do {
            System.out.println("                  What specific make you're looking for? type the make of the vehicle otherwise press enter to skip");
            String make = scanner.nextLine();

            System.out.println("                  Do you have a particular model in mind? If yes, type the model otherwise press enter to skip.");
            String model = scanner.nextLine();

            if (make.matches("[a-zA-Z0-9]+") || model.matches("[a-zA-Z0-9]+")) {
                System.err.println("Please enter a valid input. Try again.");
            }

            if (make.isEmpty() && model.isEmpty()) {
                System.out.println("Undecided? Here are the available vehicles: ");
                for (Vehicle v : vehicles) {
                    printVehicleFormat(v);
                }
                running = false;
            }

            for (Vehicle v : vehicles) {

                boolean makeMatches = make.trim().equalsIgnoreCase(v.getMake());
                boolean modelMatches = model.trim().equalsIgnoreCase(v.getModel());

                if (makeMatches && modelMatches) {
                    //todo ask David how to filter this
                    printVehicleFormat(v);
                    running = false;
                    break;
                } else if (make.isEmpty() && modelMatches) {

                    printVehicleFormat(v);
                    running = false;
                    break;

                } else if (makeMatches && model.isEmpty()) {

                    printVehicleFormat(v);
                    running = false;
                    break;
                }
            }
        } while (running);
    }

    public void processGetByYearRequest() {
        int minimumYear = vehicles.get(0).getYear();
        int maximumYear = vehicles.get(0).getYear();

        for (Vehicle v : vehicles) {

            if (v.getYear() < minimumYear) {
                minimumYear = v.getYear();

            }

            if (v.getYear() > maximumYear) {
                maximumYear = v.getYear();

            }

        }

        boolean running = true;

        do {
            try {
                System.out.println("Minimum Year: ");
                int minimumYearInput = Integer.parseInt(scanner.nextLine());

                System.out.println("Maximum Year: ");
                int maximumYearInput = Integer.parseInt(scanner.nextLine());

                if ((minimumYearInput < minimumYear) || (maximumYearInput > maximumYear)) {

                    System.err.printf("Vehicle years available is from %s to %s. Try Again%n", minimumYear, maximumYear);

                } else if (minimumYearInput > maximumYearInput) {
                    System.err.println("Minimum year cannot be greater than maximum year. Try again.");
                } else {

                    for (Vehicle v : vehicles) {

                        if (v.getYear() >= minimumYearInput && v.getYear() <= maximumYearInput) {
                            printVehicleFormat(v);
                        }

                    }

                    running = false;

                }
            } catch (NumberFormatException e) {
                System.err.println("Enter a valid year. Try again.");
            }
        } while (running);


    }

    public void processGetByColorRequest() {


        boolean running = true;
        do {

            System.out.println("What color of a vehicle you're looking for? ");
            String colorInput = scanner.nextLine();

            if (!colorInput.matches("[a-zA-Z\\s]+")) {
                System.err.println("Please enter a valid input. Try again.");
                continue;
            }

            boolean found = false;
            for (Vehicle v : vehicles) {
                if (colorInput.equalsIgnoreCase(v.getColor())) {
                    printVehicleFormat(v);
                    found = true;
                }
            }

            if (found) {
                running = false;
            }
            if (!found) {
                System.err.printf("No %s color available on the vehicle list. Try again%n", colorInput);
            }

        } while (running);
    }

    public void processGetByMileageRequest() {


        boolean running = true;
        do {

            double minimumMileage = Double.MAX_VALUE;
            double maximumMileage = 0;

            for (Vehicle v : vehicles) {
                double currentMileage = v.getOdometer();
                //checking the lowest price in the arraylist of vehicles, then compare: if the input price is lower than the minimum price in the list, then throw an error.
                if (currentMileage < minimumMileage) {
                    minimumMileage = currentMileage;
                }

                //Vice versa, checking the input if the user entered an amount that is greater than the maximum price in the list, then throw an error
                if (currentMileage > maximumMileage) {
                    maximumMileage = currentMileage;
                }
            }


            try {

                System.out.println("Minimum Mileage: ");
                int minimumMileageInput = Integer.parseInt(scanner.nextLine());

                if (minimumMileageInput < 0) {
                    System.err.println("Mileage cannot be negative. Try Again.");
                    continue;
                }

                if (minimumMileageInput < minimumMileage) {
                    System.err.printf("Minimum mileage of a vehicle available is %s Try again.%n", minimumMileage);
                    continue;
                }

                System.out.println("Maximum Mileage: ");
                int maximumMileageInput = Integer.parseInt(scanner.nextLine());

                if (maximumMileageInput < 0) {
                    System.err.println("Mileage cannot be negative. Try Again.");
                    continue;
                }
                if (maximumMileageInput > maximumMileage) {
                    System.err.printf("Maximum mileage of a vehicle available is %s Try again.%n", maximumMileage);
                    continue;
                }

                if ( minimumMileageInput > maximumMileageInput) {
                    System.err.println("Maximum mileage cannot be less than minimum mileage. Try Again.");
                    continue;
                }

                boolean found = false;

                for (Vehicle v : vehicles) {

                    if (v.getOdometer() >= minimumMileageInput && v.getOdometer() <= maximumMileageInput) {

                        printVehicleFormat(v);
                        found = true;
                    }

                }

                if (!found) {
                    System.err.println("No vehicles found in that mileage range.");
                }

                running = false;

            } catch (NumberFormatException e) {
                System.err.println("Enter a valid number. Try again.");
            }

        } while (running);


    }

    public void processGetByVehicleTypeRequest() {
        System.out.println();
    }

    public void processAllVehiclesRequest() {
        ArrayList<Vehicle> v = dealership.getAllVehicles();
        displayVehicles(v);
    }

    public void processAddVehicleRequest() {
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
