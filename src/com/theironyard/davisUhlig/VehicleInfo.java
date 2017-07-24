package com.theironyard.davisUhlig;

/**
 * Created by duhlig on 7/21/17.
 */
public class VehicleInfo {
    int vin;
    double odometer;
    double consumption;
    double odometerReadingForLastOilChange;
    double engineSizeLiters;

    public VehicleInfo() {
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getOdometerReadingForLastOilChange() {
        return odometerReadingForLastOilChange;
    }

    public void setOdometerReadingForLastOilChange(double odometerReadingForLastOilChange) {
        this.odometerReadingForLastOilChange = odometerReadingForLastOilChange;
    }

    public double getEngineSizeLiters() {
        return engineSizeLiters;
    }

    public void setEngineSizeLiters(double engineSizeLiters) {
        this.engineSizeLiters = engineSizeLiters;
    }
}
