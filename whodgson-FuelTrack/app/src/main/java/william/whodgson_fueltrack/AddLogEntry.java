package william.whodgson_fueltrack;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
public class AddLogEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView dateText = (TextView) findViewById(R.id.date_text);
        final EditText date = (EditText) findViewById(R.id.date_enter_text);
        TextView stationText = (TextView) findViewById(R.id.station_text);
        final EditText station = (EditText) findViewById(R.id.station_enter_text);
        TextView odometerText = (TextView) findViewById(R.id.odometer_text);
        final EditText odometer = (EditText) findViewById(R.id.odometer_enter_text);
        TextView fuelGradeText = (TextView) findViewById(R.id.fuel_grade_text);
        final EditText fuelGrade = (EditText) findViewById(R.id.fuel_grade_enter_text);
        TextView fuel_amountText = (TextView) findViewById(R.id.fuel_amount_text);
        final EditText fuelAmount = (EditText) findViewById(R.id.fuel_amount_enter_text);
        TextView fuelUnitCostText = (TextView) findViewById(R.id.fuel_unit_cost_text);
        final EditText fuelUnitCost = (EditText) findViewById(R.id.fuel_unit_cost_enter_text);
        TextView fuelCostText = (TextView) findViewById(R.id.fuel_cost_text);
        final EditText fuelCost = (EditText) findViewById(R.id.fuel_cost_enter_text);

        final Button addToLogAddToLogButton = (Button) findViewById(R.id.add_to_log_add_to_log_button);
        final Button addToLogCancelLogButton = (Button) findViewById(R.id.add_to_log_cancel_log_button);

        addToLogAddToLogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean properInput = true;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                String dateStr = date.getText().toString().trim();
                try {
                    Date parsedDate = format.parse(dateStr);
                } catch (ParseException e) {
                    date.setError("Please enter date in yyyy-mm-dd format");
                    properInput = false;
                }

                String stationStr = station.getText().toString().trim();
                if (stationStr.length() == 0) {
                    station.setError("Please enter a station");
                    properInput = false;
                }

                String odometerStr = odometer.getText().toString().trim();
                try {
                    float parsedOdometer = Float.parseFloat(odometerStr);
                } catch (NumberFormatException e) {
                    odometer.setError("Please enter odometer reading in kilometers");
                    properInput = false;
                }

                String fuelGradeStr = fuelGrade.getText().toString().trim();
                if (fuelGradeStr.length() == 0) {
                    fuelGrade.setError("Please enter a fuel grade");
                    properInput = false;
                }

                String fuelAmountStr = fuelAmount.getText().toString().trim();
                try {
                    float parsedFuelAmount = Float.parseFloat(fuelAmountStr);
                } catch (NumberFormatException e) {
                    fuelAmount.setError("Please enter a fuel amount in liters");
                    properInput = false;
                }

                String fuelUnitCostStr = fuelUnitCost.getText().toString().trim();
                try {
                    float parsedFuelUnitCost = Float.parseFloat(fuelUnitCostStr);
                } catch (NumberFormatException e) {
                    fuelUnitCost.setError("Please enter a fuel unit cost in cents/L");
                    properInput = false;
                }

                String fuelCostStr = fuelCost.getText().toString().trim();
                try {
                    float parsedFuelCost = Float.parseFloat(fuelCostStr);
                } catch (NumberFormatException e) {
                    fuelCost.setError("Please enter a fuel cost in dollars");
                    properInput = false;
                }

                // Write to log
                if (properInput) {
                    File logFile = new File(getFilesDir(), "log.txt");
                    if (!logFile.exists()) {
                        try {
                            logFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    OutputStream logStream = null;
                    try {
                        logStream = new FileOutputStream(logFile, true);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    OutputStreamWriter logWriter = new OutputStreamWriter(logStream);
                    try {
                        logWriter.append(dateStr + "\n");
                        logWriter.append(stationStr+"\n");
                        logWriter.append(odometerStr+"\n");
                        logWriter.append(fuelGradeStr+"\n");
                        logWriter.append(fuelAmountStr+"\n");
                        logWriter.append(fuelUnitCostStr+"\n");
                        logWriter.append(fuelCostStr+"\n");
                        logStream.flush();
                        logWriter.flush();
                        logStream.close();
                        logWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        });

        addToLogCancelLogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
