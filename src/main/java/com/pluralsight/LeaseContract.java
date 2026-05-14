package com.pluralsight;

public class LeaseContract extends Contract{
    private final double EXPECTED_ENDING_VALUE = 1.50;
    private final double LEASE_FEE = 1.07;

    public LeaseContract(String contractDate, String name, String email, Vehicle vehicleSold) {
        super(contractDate, name, email, vehicleSold);
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    public double getMonthlyPayment() {
        return 0;
    }
}
