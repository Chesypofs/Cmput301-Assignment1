package william.whodgson_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

/**
 * Created by William on 2016-01-30.
 */
public class LogEntryTest extends ActivityInstrumentationTestCase2 {
    public LogEntryTest() {
        super(LogEntry.class);
    }

    public void testGetDate() {
        Date date = new Date();
        LogEntry entry = new LogEntry(date, " ", 0, " ", 0, 0, 0);
        assertEquals(entry.getDate(), date);
    }
    public void testGetStation() {
        Date date = new Date();
        LogEntry entry = new LogEntry(date, "esso", 0, " ", 0, 0, 0);
        assertEquals(entry.getStation(), "esso");
    }
    public void testGetOdometer() {
        Date date = new Date();
        LogEntry entry = new LogEntry(date, " ", 100, " ", 0, 0, 0);
        assertEquals(entry.getOdometer(), 100.0f);
    }
    public void testGetFuelGrade() {
        Date date = new Date();
        LogEntry entry = new LogEntry(date, " ", 0, "good", 0, 0, 0);
        assertEquals(entry.getFuelGrade(), "good");
    }
    public void testGetFuelAmount() {
        Date date = new Date();
        LogEntry entry = new LogEntry(date, " ", 0, " ", 100, 0, 0);
        assertEquals(entry.getFuelAmount(), 100.0f);
    }
    public void testGetFuelUnitCost() {
        Date date = new Date();
        LogEntry entry = new LogEntry(date, " ", 0, " ", 0, 100, 0);
        assertEquals(entry.getFuelUnitCost(), 100.0f);
    }
    public void testGetFuelCost() {
        Date date = new Date();
        LogEntry entry = new LogEntry(date, " ", 0, " ", 0, 0, 100);
        assertEquals(entry.getFuelCost(), 100.0f);
    }
}
