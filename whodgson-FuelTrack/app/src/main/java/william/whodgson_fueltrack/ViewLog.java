package william.whodgson_fueltrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ViewLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<String[]> logList = new ArrayList<String[]>();
        String[] fields = {"Date: ", "Station: ", "Odometer: ", "Fuel Grade: ", "Fuel Amount: ",
                "Fuel Unit Cost: ", "Fuel Cost: "};

        try {
            InputStream logStream = openFileInput("log.txt");
            if (logStream != null) {
                int i = 0;

                InputStreamReader logReader = new InputStreamReader(logStream);
                BufferedReader bufferedReader = new BufferedReader(logReader);
                String line = "";
                String[] logEntry = new String[7];
                while ((line = bufferedReader.readLine()) != null) {
                    logEntry[i] = fields[i] + line;
                    i++;
                    if (i == 7) {
                        i = 0;
                        logEntry[2] = logEntry[2] + "km";
                        logEntry[4] = logEntry[4] + "L";
                        logEntry[5] = logEntry[5] + "¢/L";
                        logEntry[6] = logEntry[6] + "$";
                        logList.add(logEntry);
                        logEntry = new String[7];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        for (String[] stringArray : logList) {
            GridLayout logLayout = new GridLayout(layout.getContext());
            logLayout.setOrientation(GridLayout.VERTICAL);
            logLayout.setColumnCount(3);
            logLayout.setRowCount(4);
            logLayout.setPadding(0,16,0,16);
            int i = 0, j = 0;
            for (String string : stringArray) {
                TextView view = new TextView(this);
                view.setText(string);
                view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                if (i == 3) i = 0;
                if (j == 4) {
                    i++;
                    j = 0;
                }
                GridLayout.Spec col = GridLayout.spec(GridLayout.UNDEFINED, i);
                GridLayout.Spec row = GridLayout.spec(GridLayout.UNDEFINED, j);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.columnSpec = GridLayout.spec(i);
                params.rowSpec = GridLayout.spec(j);
                params.setMargins(16,0,16,0);
                view.setLayoutParams(params);
                logLayout.addView(view);
                j++;
            }
            GridLayout.Spec col = GridLayout.spec(GridLayout.UNDEFINED, 2);
            GridLayout.Spec row = GridLayout.spec(GridLayout.UNDEFINED, 2);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.columnSpec = GridLayout.spec(2);
            params.rowSpec = GridLayout.spec(2);
            params.setMargins(16,0,16,0);
            Button button = new Button(logLayout.getContext());
            button.setLayoutParams(params);
            logLayout.addView(button);
            layout.addView(logLayout);
        }
        setContentView(layout);
    }

}
