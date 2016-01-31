package william.whodgson_fueltrack;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
        DecimalFormat df = new DecimalFormat("0000000.0");
        this.odometer = Float.parseFloat(df.format(odometer));
        this.fuelGrade = fuelGrade;
        df = new DecimalFormat("0000.000");
        this.fuelAmount = Float.parseFloat(df.format(fuelAmount));
        df = new DecimalFormat("000.0");
        this.fuelUnitCost = Float.parseFloat(df.format(fuelUnitCost));
        df = new DecimalFormat("000000.00");
        this.fuelCost = Float.parseFloat(df.format(fuelCost));
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStation() {
        return this.station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public float getOdometer() {
        return this.odometer;
    }

    public void setOdometer(float odometer) {
        this.odometer = odometer;
    }

    public String getFuelGrade() {
        return this.fuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public float getFuelAmount() {
        return this.fuelAmount;
    }

    public void setFuelAmount(float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public float getFuelUnitCost() {
        return this.fuelUnitCost;
    }

    public void setFuelUnitCost(float fuelUnitCost) {
        this.fuelUnitCost = fuelUnitCost;
    }

    public float getFuelCost() {
        return this.fuelCost;
    }

    public void setFuelCost(float fuelCost) {
        this.fuelCost = fuelCost;
    }

}
