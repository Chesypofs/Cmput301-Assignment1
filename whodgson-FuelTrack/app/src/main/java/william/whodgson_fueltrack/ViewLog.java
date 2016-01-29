package william.whodgson_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ViewLog extends AppCompatActivity {
    private LinearLayout layout;
    private GridLayout logLayout;
    private Log log;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        final File file = (File) bundle.get("FILE");
        log = new Log(getApplicationContext(), file);

        String[] fields = {"Date: ", "Station: ", "Odometer: ", "Fuel Grade: ", "Fuel Amount: ",
                "Fuel Unit Cost: ", "Fuel Cost: "};
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        for (int i = 0; i < log.getLength(); i++) {
            LogEntry logEntry = log.getLogEntry(i);
            String[] stringArray = {
                    fields[0] + dateFormat.format(logEntry.getDate()),
                    fields[1] + logEntry.getStation(),
                    fields[2] + Float.toString(logEntry.getOdometer()),
                    fields[3] + logEntry.getFuelGrade(),
                    fields[4] + Float.toString(logEntry.getFuelAmount()),
                    fields[5] + Float.toString(logEntry.getFuelUnitCost()),
                    fields[6] + Float.toString(logEntry.getFuelCost()) };

            logLayout = new GridLayout(layout.getContext());
            logLayout.setOrientation(GridLayout.VERTICAL);
            logLayout.setColumnCount(3);
            logLayout.setRowCount(4);
            logLayout.setPadding(0,16,0,16);
            int j = 0, k = 0;
            for (String string : stringArray) {
                TextView view = new TextView(this);
                view.setText(string);
                view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                if (j == 3) j = 0;
                if (k == 4) {
                    j++;
                    k = 0;
                }
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.columnSpec = GridLayout.spec(j);
                params.rowSpec = GridLayout.spec(k);
                params.setMargins(16,0,16,0);
                view.setLayoutParams(params);
                logLayout.addView(view);
                k++;
            }
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.columnSpec = GridLayout.spec(2);
            params.rowSpec = GridLayout.spec(1, 3);
            params.setMargins(16, 0, 16, 0);
            Button button = new Button(logLayout.getContext());
            button.setLayoutParams(params);
            button.setText("Edit");
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), GetLogEntry.class);
                    final Bundle bundle = new Bundle();
                    bundle.putSerializable("FILE", file);
                    bundle.putBoolean("EDIT", true);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            logLayout.addView(button);
            layout.addView(logLayout);
        }
        setContentView(layout);
    }

}
