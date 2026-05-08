package com.pluralsight;

import java.util.ArrayList;


public class UserInterface {
    private Dealership dealership;

    public UserInterface() {

    }

    private void init() {
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

        System.out.println(menu);
    }
    public void display() {
        init();
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

    public void processGetAllVehiclesRequest() {

    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }
}