package com.pluralsight;

import java.util.ArrayList;

public class SalesContract extends Contract {
    private final double SALES_TAX = 1.05;
    private final double RECORDING_FEE = 100;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String contractDate, String name, String email, Vehicle vehicleSold, double totalPrice, double monthlyPayment, boolean isFinanced) {
        super(contractDate, name, email, vehicleSold, totalPrice, monthlyPayment);

        if(totalPrice <= 10_000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }

        this.isFinanced = isFinanced;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {

    }

    public double getMonthlyPayment() {
        return 0;
    }
}
