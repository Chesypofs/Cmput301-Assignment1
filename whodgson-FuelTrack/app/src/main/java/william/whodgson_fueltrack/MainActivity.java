package william.whodgson_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

/**
 * MainActivity
 * The starting point of the FuelTrack app. Either starts the GetLogEntry
 * activity to add a new log entry or starts the ViewLog activity to view
 * the previously entered log entries.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final File logFile = new File(getFilesDir() + "/log.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        final Button buttonViewLog = (Button) findViewById(R.id.button_view_log);
        final Button buttonAddLogEntry = (Button) findViewById(R.id.button_add_log_entry);
        final Button buttonExit = (Button) findViewById(R.id.button_exit);

        final Bundle bundle = new Bundle();
        bundle.putSerializable("FILE", logFile);

        buttonViewLog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewLog.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        buttonAddLogEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GetLogEntry.class);
                bundle.putBoolean("EDIT", false);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}