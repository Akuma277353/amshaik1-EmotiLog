package com.amshaik1.EmotiLog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.Collections;
import java.util.List;

/* shows all the logs in a scrollable list with the slider */

public class EventsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        List<LogEntry> logs = LogStore.getLogs(this);
        Collections.reverse(logs); //used to flip the list so latest clicked emoji is shown first
        RecyclerView rv = findViewById(R.id.logsRecycler); //RecycleView is setup here
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new LogAdapter(logs));

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { finish(); }
        });
    }
}
