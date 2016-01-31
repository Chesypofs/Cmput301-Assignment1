package william.whodgson_fueltrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;


public class ViewLog extends AppCompatActivity {
    private File file;
    private Log log;
    private LogAdapter adapter;
    private ListView listView;
    public static TextView totalFuelCostView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);
        Bundle bundle = getIntent().getExtras();
        file = (File) bundle.get("FILE");
        this.listView = (ListView) findViewById(R.id.log_view);
        this.totalFuelCostView = (TextView) findViewById(R.id.total_fuel_cost_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        log = new Log(getApplicationContext(), file);
        this.adapter = new LogAdapter(getApplicationContext(), R.layout.list_item, this.log, this.file, this.totalFuelCostView);
        this.listView.setAdapter(adapter);
        this.adapter.notifyDataSetChanged();
        float totalFuelCost = 0;
        for (int i = 0; i < this.log.getLength(); i++) {
            totalFuelCost += this.log.getLogEntry(i).getFuelCost();
        }
        this.totalFuelCostView.setText("Total Fuel Cost: " + Float.toString(totalFuelCost));
    }

}
