package com.pluralsight;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        double minimumPrice = Double.MAX_VALUE;
        double maximumPrice = 0;

        for (Vehicle v : this.inventory) {
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

        for (Vehicle v : this.inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

//        int minimumYear = vehicles.get(0).getYear();
//        int maximumYear = vehicles.get(0).getYear();

        int minimumYear = Integer.MAX_VALUE;
        int maximumYear = Integer.MIN_VALUE;

        for (Vehicle v : this.inventory) {

            if (v.getYear() < minimumYear) {
                minimumYear = v.getYear();

            }

            if (v.getYear() > maximumYear) {
                maximumYear = v.getYear();

            }

        }

        for (Vehicle v : this.inventory) {

            boolean makeMatches = make.trim().equalsIgnoreCase(v.getMake());
            boolean modelMatches = model.trim().equalsIgnoreCase(v.getModel());

            if (makeMatches && modelMatches) {
                //todo ask David how to filter this
                vehicles.add(v);
                break;
            } else if (make.isEmpty() && modelMatches) {
                vehicles.add(v);
                break;

            } else if (makeMatches && model.isEmpty()) {
                vehicles.add(v);
                break;
            }

        }
        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        UserInterface ui = new UserInterface();

//        int minimumYear = vehicles.get(0).getYear();
//        int maximumYear = vehicles.get(0).getYear();

        int minimumYear = Integer.MAX_VALUE;
        int maximumYear = Integer.MIN_VALUE;

        for (Vehicle v : this.inventory) {

            if (v.getYear() < minimumYear) {
                minimumYear = v.getYear();

            }

            if (v.getYear() > maximumYear) {
                maximumYear = v.getYear();

            }
        }

        if ((min < minimumYear) || (max > maximumYear)) {

            System.err.printf("Vehicle years available is from %s to %s. Try Again%n", minimumYear, maximumYear);

        } else if (min > max) {
            System.err.println("Minimum year cannot be greater than maximum year. Try again.");
        } else {

            for (Vehicle v : this.inventory) {

                if (v.getYear() >= min && v.getYear() <= max) {
                    vehicles.add(v);
                }

            }
        }

        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : this.inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                vehicles.add(v);

            }
        }

        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        double minimumMileage = Double.MAX_VALUE;
        double maximumMileage = 0;

        for (Vehicle v : this.inventory) {
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

        for (Vehicle v : this.inventory) {

            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : this.inventory) {

            if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public ArrayList<Vehicle> getAllVehicles() {

        return new ArrayList<>(this.inventory);
    }

    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

}
