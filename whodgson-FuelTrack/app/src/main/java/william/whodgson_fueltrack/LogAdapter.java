package william.whodgson_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by William on 2016-01-29.
 *
 * LogAdapter
 * Dynamically updates the view in ViewLog to show the log entries
 * contained in the ArrayList of the log.
 *
 * Problems: In order to dynamically update the total fuel cost in the
 *           ViewLog, LogAdapter accesses a TextView instance variable
 *           of ViewLog.
 *           Need to change to method call.
 */

public class LogAdapter extends ArrayAdapter<LogEntry> {
    private Context context;
    private Log log;
    private File logFile;
    private TextView totalFuelCostView;

    public LogAdapter(Context context, int id, Log log, File logFile, TextView totalFuelCostView) {
        super(context, id, log.getLog());
        this.context = context;
        this.log = log;
        this.logFile = logFile;
        this.totalFuelCostView = totalFuelCostView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

        LogEntry logEntry = this.log.getLog().get(position);

        if (logEntry != null) {
            TextView dateView = (TextView) v.findViewById(R.id.date_view);
            TextView stationView = (TextView) v.findViewById(R.id.station_view);
            TextView odometerView = (TextView) v.findViewById(R.id.odometer_view);
            TextView fuelGradeView = (TextView) v.findViewById(R.id.fuel_grade_view);
            TextView fuelAmountView = (TextView) v.findViewById(R.id.fuel_amount_view);
            TextView fuelUnitCostView = (TextView) v.findViewById(R.id.fuel_unit_cost_view);
            TextView fuelCostView = (TextView) v.findViewById(R.id.fuel_cost_view);
            Button editButton = (Button) v.findViewById(R.id.edit_button_view);
            Button deleteButton = (Button) v.findViewById(R.id.delete_button_view);

            String[] fields = {"Date: ", "Station: ", "Odometer: ", "Fuel Grade: ", "Fuel Amount: ",
                    "Fuel Unit Cost: ", "Fuel Cost: "};

            if (dateView != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateView.setText(fields[0] + dateFormat.format(logEntry.getDate()));
            }
            if (stationView != null) {
                stationView.setText(fields[1] + logEntry.getStation());
            }
            if (odometerView != null) {
                odometerView.setText(fields[2] + Float.toString(logEntry.getOdometer()));
            }
            if (fuelGradeView != null) {
                fuelGradeView.setText(fields[3] + logEntry.getFuelGrade());
            }
            if (fuelAmountView != null) {
                fuelAmountView.setText(fields[4] + Float.toString(logEntry.getFuelAmount()));
            }
            if (fuelUnitCostView != null) {
                fuelUnitCostView.setText(fields[5] + Float.toString(logEntry.getFuelUnitCost()));
            }
            if (fuelCostView != null) {
                fuelCostView.setText(fields[6] + Float.toString(logEntry.getFuelCost()));
            }
            if (editButton != null) {
                editButton.setText("EDIT");
                editButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("FILE", logFile);
                        Intent intent = new Intent(LogAdapter.this.context, GetLogEntry.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        bundle.putInt("EDITINDEX", position);
                        bundle.putBoolean("EDIT", true);
                        intent.putExtras(bundle);
                        LogAdapter.this.context.startActivity(intent);
                    }
                });
            }
            if (deleteButton != null) {
                deleteButton.setText("DELETE");
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        log.deleteLogEntry(position);
                        LogAdapter.this.notifyDataSetChanged();
                        float total = 0;
                        for (int i = 0; i < LogAdapter.this.log.getLength(); i++) {
                            total += LogAdapter.this.log.getLogEntry(i).getFuelCost();
                        }
                        LogAdapter.this.totalFuelCostView.setText(Float.toString(total));
                    }
                });
            }
        }
        return v;
    }
}
