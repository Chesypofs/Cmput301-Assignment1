package william.whodgson_fueltrack;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by William on 2016-01-14.
 */
public class Log {
    private Context context;
    private File file;
    private int length;
    private ArrayList<LogEntry> log = new ArrayList<LogEntry>();

    public Log(Context context, File file) {
        this.context = context;
        this.file = file;
        this.length = 0;
        if (this.file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(this.file);
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
                    Type listType = new TypeToken<ArrayList<LogEntry>> () {}.getType();
                    this.log = gson.fromJson(in, listType);
                    this.length = this.log.size();
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

    public Boolean hasLogEntry(LogEntry logEntry) {
        return this.log.contains(logEntry);
    }

    public void addLogEntry(LogEntry newLog) {
        this.log.add(newLog);
        this.saveToFile();
        this.length += 1;
    }

    public LogEntry getLogEntry(int logIndex) {
        return this.log.get(logIndex);
    }

    public ArrayList<LogEntry> getLog() {
        return this.log;
    }

    public int getLength() {
        return this.length;
    }

    public void editLogEntry(int logIndex, LogEntry newLogEntry) {
        this.log.set(logIndex, newLogEntry);
        this.saveToFile();
    }

    public void deleteLogEntry(int logIndex) {
        this.log.remove(logIndex);
        this.saveToFile();
        this.length -= 1;
    }

    private void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.file);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(this.log, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}