package william.whodgson_fueltrack;

import java.util.Date;

/**
 * Created by William on 2016-01-20.
 */
public class LogEntry {
    private Date date;
    private String station;
    private float odometer;
    private String fuelGrade;
    private float fuelAmount;
    private float fuelUnitCost;
    private float fuelCost;

    public LogEntry(Date date, String station, float odometer, String fuelGrade, float fuelAmount,
                    float fuelUnitCost, float fuelCost) {
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.fuelGrade = fuelGrade;
        this.fuelAmount = fuelAmount;
        this.fuelUnitCost = fuelUnitCost;
        this.fuelCost = fuelCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public float getOdometer() {
        return odometer;
    }

    public void setOdometer(float odometer) {
        this.odometer = odometer;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public float getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public float getFuelUnitCost() {
        return fuelUnitCost;
    }

    public void setFuelUnitCost(float fuelUnitCost) {
        this.fuelUnitCost = fuelUnitCost;
    }

    public float getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(float fuelCost) {
        this.fuelCost = fuelCost;
    }
}
