package william.whodgson_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import william.whodgson_fueltrack.LogEntry;

/**
 * Created by William on 2016-01-30.
 *
 * LogTest
 * Tests all the methods in the Log class
 */

public class LogTest extends ActivityInstrumentationTestCase2 {
    public LogTest() {
        super(Log.class);
    }

    public void testHasLogEntry() {
        // This line and the lines like it in each method taken from:
        // http://stackoverflow.com/questions/10539049/creating-files-in-android-junit
        // user: yorkw, date: 2016-01-31
        File f = this.getInstrumentation().getTargetContext().getDir("tmp", 0);
        File file = new File (f, "testHasLogEntry.txt");
        Log log = new Log(this.getInstrumentation().getTargetContext().getApplicationContext(), file);
        Date date = new Date();
        LogEntry logEntry = new LogEntry(date, " ", 0, " ", 0, 0, 0);
        assertFalse(log.hasLogEntry(logEntry));
        log.addLogEntry(logEntry);
        assertTrue(log.hasLogEntry(logEntry));
        file.delete();
    }

    public void testAddLogEntry() {
        File f = this.getInstrumentation().getTargetContext().getDir("tmp", 0);
        File file = new File (f, "testAddLogEntry.txt");
        Log log = new Log(this.getInstrumentation().getTargetContext().getApplicationContext(), file);
        Date date = new Date();
        LogEntry logEntry = new LogEntry(date, " ", 0, " ", 0, 0, 0);
        assertFalse(log.hasLogEntry(logEntry));
        log.addLogEntry(logEntry);
        assertTrue(log.hasLogEntry(logEntry));
        file.delete();
    }

    public void testGetLogEntry() {
        File f = this.getInstrumentation().getTargetContext().getDir("tmp", 0);
        File file = new File (f, "testGetLogEntry.txt");
        Log log = new Log(this.getInstrumentation().getTargetContext().getApplicationContext(), file);
        Date date = new Date();
        LogEntry logEntry = new LogEntry(date, " ", 0, " ", 0, 0, 0);
        log.addLogEntry(logEntry);
        LogEntry returnedEntry = log.getLogEntry(0);
        assertEquals(logEntry.getDate(), returnedEntry.getDate());
        assertEquals(logEntry.getStation(), returnedEntry.getStation());
        assertEquals(logEntry.getOdometer(), returnedEntry.getOdometer());
        assertEquals(logEntry.getFuelGrade(), returnedEntry.getFuelGrade());
        assertEquals(logEntry.getFuelAmount(), returnedEntry.getFuelAmount());
        assertEquals(logEntry.getFuelUnitCost(), returnedEntry.getFuelUnitCost());
        assertEquals(logEntry.getFuelCost(), returnedEntry.getFuelCost());
        file.delete();
    }

    public void testGetLength() {
        File f = this.getInstrumentation().getTargetContext().getDir("tmp", 0);
        File file = new File (f, "testGetLength.txt");
        Log log = new Log(this.getInstrumentation().getTargetContext().getApplicationContext(), file);
        Date date = new Date();
        LogEntry logEntry = new LogEntry(date, " ", 0, " ", 0, 0, 0);
        assertEquals(log.getLength(), 0);
        log.addLogEntry(logEntry);
        assertEquals(log.getLength(), 1);
        log.deleteLogEntry(0);
        assertEquals(log.getLength(), 0);
        file.delete();
    }

    public void testGetLog() {
        File f = this.getInstrumentation().getTargetContext().getDir("tmp", 0);
        File file = new File (f, "testGetLog.txt");
        Log log = new Log(this.getInstrumentation().getTargetContext().getApplicationContext(), file);
        Date date = new Date();
        LogEntry logEntry1 = new LogEntry(date, "1", 1, "1", 1, 1, 1);
        LogEntry logEntry2 = new LogEntry(date, "2", 2, "2", 2, 2, 2);
        LogEntry logEntry3 = new LogEntry(date, "3", 3, "3", 3, 3, 3);
        log.addLogEntry(logEntry1);
        log.addLogEntry(logEntry2);
        log.addLogEntry(logEntry3);
        ArrayList<LogEntry> returnedLog = log.getLog();
        LogEntry returnedEntry1 = returnedLog.get(0);
        LogEntry returnedEntry2 = returnedLog.get(1);
        LogEntry returnedEntry3 = returnedLog.get(2);
        assertEquals(logEntry1.getDate(), returnedEntry1.getDate());
        assertEquals(logEntry1.getStation(), returnedEntry1.getStation());
        assertEquals(logEntry1.getOdometer(), returnedEntry1.getOdometer());
        assertEquals(logEntry1.getFuelGrade(), returnedEntry1.getFuelGrade());
        assertEquals(logEntry1.getFuelAmount(), returnedEntry1.getFuelAmount());
        assertEquals(logEntry1.getFuelUnitCost(), returnedEntry1.getFuelUnitCost());
        assertEquals(logEntry1.getFuelCost(), returnedEntry1.getFuelCost());
        assertEquals(logEntry2.getDate(), returnedEntry2.getDate());
        assertEquals(logEntry2.getStation(), returnedEntry2.getStation());
        assertEquals(logEntry2.getOdometer(), returnedEntry2.getOdometer());
        assertEquals(logEntry2.getFuelGrade(), returnedEntry2.getFuelGrade());
        assertEquals(logEntry2.getFuelAmount(), returnedEntry2.getFuelAmount());
        assertEquals(logEntry2.getFuelUnitCost(), returnedEntry2.getFuelUnitCost());
        assertEquals(logEntry2.getFuelCost(), returnedEntry2.getFuelCost());
        assertEquals(logEntry3.getDate(), returnedEntry3.getDate());
        assertEquals(logEntry3.getStation(), returnedEntry3.getStation());
        assertEquals(logEntry3.getOdometer(), returnedEntry3.getOdometer());
        assertEquals(logEntry3.getFuelGrade(), returnedEntry3.getFuelGrade());
        assertEquals(logEntry3.getFuelAmount(), returnedEntry3.getFuelAmount());
        assertEquals(logEntry3.getFuelUnitCost(), returnedEntry3.getFuelUnitCost());
        assertEquals(logEntry3.getFuelCost(), returnedEntry3.getFuelCost());
        file.delete();
    }

    public void testEditLogEntry() {
        File f = this.getInstrumentation().getTargetContext().getDir("tmp", 0);
        File file = new File (f, "testEditLogEntry.txt");
        Log log = new Log(this.getInstrumentation().getTargetContext().getApplicationContext(), file);
        Date date = new Date();
        LogEntry logEntry = new LogEntry(date, "1", 0, " ", 0, 0, 0);
        log.addLogEntry(logEntry);
        LogEntry returnedEntry = log.getLogEntry(0);
        assertEquals(logEntry.getDate(), returnedEntry.getDate());
        assertEquals(logEntry.getStation(), returnedEntry.getStation());
        assertEquals(logEntry.getOdometer(), returnedEntry.getOdometer());
        assertEquals(logEntry.getFuelGrade(), returnedEntry.getFuelGrade());
        assertEquals(logEntry.getFuelAmount(), returnedEntry.getFuelAmount());
        assertEquals(logEntry.getFuelUnitCost(), returnedEntry.getFuelUnitCost());
        assertEquals(logEntry.getFuelCost(), returnedEntry.getFuelCost());
        LogEntry newLogEntry = new LogEntry(date, "2", 0, " ", 0, 0, 0);
        log.editLogEntry(0, newLogEntry);
        returnedEntry = log.getLogEntry(0);
        assertEquals(newLogEntry.getDate(), returnedEntry.getDate());
        assertEquals(newLogEntry.getStation(), returnedEntry.getStation());
        assertEquals(newLogEntry.getOdometer(), returnedEntry.getOdometer());
        assertEquals(newLogEntry.getFuelGrade(), returnedEntry.getFuelGrade());
        assertEquals(newLogEntry.getFuelAmount(), returnedEntry.getFuelAmount());
        assertEquals(newLogEntry.getFuelUnitCost(), returnedEntry.getFuelUnitCost());
        assertEquals(newLogEntry.getFuelCost(), returnedEntry.getFuelCost());
        file.delete();
    }

    public void testDeleteLogEntry() {
        File f = this.getInstrumentation().getTargetContext().getDir("tmp", 0);
        File file = new File (f, "testDeleteLogEntry.txt");
        Log log = new Log(this.getInstrumentation().getTargetContext().getApplicationContext(), file);
        Date date = new Date();
        LogEntry logEntry = new LogEntry(date, " ", 0, " ", 0, 0, 0);
        assertFalse(log.hasLogEntry(logEntry));
        log.addLogEntry(logEntry);
        assertTrue(log.hasLogEntry(logEntry));
        log.deleteLogEntry(0);
        assertFalse(log.hasLogEntry(logEntry));
        file.delete();
    }

    // called in addToFile and editLogEntry
    public void testSaveToFile() {
        File f = this.getInstrumentation().getTargetContext().getDir("tmp", 0);
        File file = new File (f, "testSaveToFile.txt");
        Log log = new Log(this.getInstrumentation().getTargetContext().getApplicationContext(), file);
        Date date = new Date();
        LogEntry logEntry = new LogEntry(date, " ", 0, " ", 0, 0, 0);
        log.addLogEntry(logEntry);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        try {
            if (in.readLine() != null) {
                fis.getChannel().position(0);
                in = new BufferedReader(new InputStreamReader(fis));
                Gson gson = new Gson();
                // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html January 19 2016
                Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
                ArrayList<LogEntry> returnedLog = gson.fromJson(in, listType);
                LogEntry returnedEntry = returnedLog.get(0);
                assertEquals(logEntry.getDate(), returnedEntry.getDate());
                assertEquals(logEntry.getStation(), returnedEntry.getStation());
                assertEquals(logEntry.getOdometer(), returnedEntry.getOdometer());
                assertEquals(logEntry.getFuelGrade(), returnedEntry.getFuelGrade());
                assertEquals(logEntry.getFuelAmount(), returnedEntry.getFuelAmount());
                assertEquals(logEntry.getFuelUnitCost(), returnedEntry.getFuelUnitCost());
                assertEquals(logEntry.getFuelCost(), returnedEntry.getFuelCost());
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        file.delete();
    }
}
