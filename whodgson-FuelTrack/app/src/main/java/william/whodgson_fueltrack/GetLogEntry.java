package william.whodgson_fueltrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * GetLogEntry
 * Allows a user to add a new log entry or edit an existing one, and
 * checks to make sure the user enters valid input.
 *
 * Problem: In order to dynamically update the total fuel cost in the
 *          ViewLog, GetLogEntry accesses a TextView instance variable
 *          of ViewLog.
 *          Need to change to method call.
 */

public class GetLogEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log_entry);
        Bundle bundle = getIntent().getExtras();
        final Boolean edit = (Boolean) bundle.get("EDIT");
        final File file = (File) bundle.get("FILE");
        final Log log = new Log(getApplicationContext(), file);
        final int editIndex;
        if (edit) {
            editIndex = getIntent().getIntExtra("EDITINDEX", -1);
        } else {
            editIndex = -1;
        }

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

        if (edit) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date.setText(dateFormat.format(log.getLogEntry(editIndex).getDate()));
            station.setText(log.getLogEntry(editIndex).getStation());
            odometer.setText(Float.toString(log.getLogEntry(editIndex).getOdometer()));
            fuelGrade.setText(log.getLogEntry(editIndex).getFuelGrade());
            fuelAmount.setText(Float.toString(log.getLogEntry(editIndex).getFuelAmount()));
            fuelUnitCost.setText(Float.toString(log.getLogEntry(editIndex).getFuelUnitCost()));
            fuelCost.setText(Float.toString(log.getLogEntry(editIndex).getFuelCost()));
            addToLogAddToLogButton.setText("EDIT ENTRY");
        }

        addToLogAddToLogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean properInput = true;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                String dateStr = date.getText().toString().trim();
                Date parsedDate = new Date();
                float parsedOdometer = -1, parsedFuelAmount = -1, parsedFuelUnitCost = -1, parsedFuelCost = -1;

                // Check for valid inputs
                try {
                    parsedDate = format.parse(dateStr);
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
                    parsedOdometer = Float.parseFloat(odometerStr);
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
                    parsedFuelAmount = Float.parseFloat(fuelAmountStr);
                } catch (NumberFormatException e) {
                    fuelAmount.setError("Please enter a fuel amount in liters");
                    properInput = false;
                }

                String fuelUnitCostStr = fuelUnitCost.getText().toString().trim();
                try {
                    parsedFuelUnitCost = Float.parseFloat(fuelUnitCostStr);
                } catch (NumberFormatException e) {
                    fuelUnitCost.setError("Please enter a fuel unit cost in cents/L");
                    properInput = false;
                }

                String fuelCostStr = fuelCost.getText().toString().trim();
                try {
                    parsedFuelCost = Float.parseFloat(fuelCostStr);
                } catch (NumberFormatException e) {
                    if ((parsedFuelUnitCost == -1) || (parsedFuelAmount == -1)) {
                        fuelCost.setError("Please enter a fuel cost in dollars");
                        properInput = false;
                    }
                    else {
                        parsedFuelCost = parsedFuelUnitCost * parsedFuelAmount / 100;
                    }
                }

                // Write to log
                if (properInput) {
                    if (edit) {
                        LogEntry logEntry = new LogEntry(parsedDate, stationStr, parsedOdometer, fuelGradeStr,
                                parsedFuelAmount, parsedFuelUnitCost, parsedFuelCost);
                        log.editLogEntry(editIndex, logEntry);
                        float total = 0;
                        for (int i = 0; i < log.getLength(); i++) {
                            total += log.getLogEntry(i).getFuelCost();
                        }
                        //TODO: change this to method call
                        ViewLog.totalFuelCostView.setText(Float.toString(total));

                    } else {
                        LogEntry logEntry = new LogEntry(parsedDate, stationStr, parsedOdometer, fuelGradeStr,
                                parsedFuelAmount, parsedFuelUnitCost, parsedFuelCost);
                        log.addLogEntry(logEntry);
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
