package com.amshaik1.EmotiLog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* Shows a summary counts and percentage of logs per emoji */

public class SummaryActivity extends AppCompatActivity {
    private Spinner spinner;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        spinner = findViewById(R.id.spinnerRange);
        listView = findViewById(R.id.listSummary);

        ArrayAdapter<String> sp = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Today", "Last 7 Days", "All Time"}); // Setting up spinner choices
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                refresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        // Back button
        findViewById(R.id.btnBack2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { finish(); }
        });

        // Initial load
        refresh();
    }

    private void refresh() { //this updates the summary whenever a different choice is picked from the dropdown menu
        int pos = spinner.getSelectedItemPosition();
        long start;
        long end = System.currentTimeMillis();

        if (pos == 0) {
            // Today
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            start = cal.getTimeInMillis();
        } else if (pos == 1) {
            // Last 7 days
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, -6);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            start = cal.getTimeInMillis();
        } else {
            // All time
            start = 0L;
        }

        List<LogEntry> logs = LogStore.getLogs(this);
        Map<String,Integer> counts = new HashMap<>();
        for (LogEntry e : logs) { //counts all logs in the selected time range
            if (e.timestamp >= start && e.timestamp <= end) {
                counts.put(e.emotion, counts.getOrDefault(e.emotion, 0) + 1);
            }
        }

        int total = 0;
        for (int c : counts.values()) total += c;

        List<String> lines = new ArrayList<>();
        lines.add("Total entries: " + total);
        for (String emo : counts.keySet()) {
            int cnt = counts.get(emo);
            //calculating percentage
            int pct = (total == 0) ? 0 : (int) Math.round((cnt * 100.0) / total);
            lines.add(emo + " — " + cnt + " (" + pct + "%)");
        }

        listView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lines));
    }
}
