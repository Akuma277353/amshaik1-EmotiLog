package com.amshaik1.EmotiLog;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


/* Main Screen, Has all the buttons and most of the functionality begins here*/

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onEmotionClick(View v) { //for logging emoji
        String emotion = (String) v.getTag();
        if (emotion == null) emotion = "Unknown";
        LogStore.addLog(this, emotion);
        Toast.makeText(this, "Logged: " + emotion, Toast.LENGTH_SHORT).show();
    }
    public void onOpenEvents(View v) { //opens the list screen
        startActivity(new Intent(this, EventsActivity.class));
    }
    public void onOpenSummary(View v) { //opens the summary screen
        startActivity(new Intent(this, SummaryActivity.class));
    }
    public void onClearLogs(View v) { //clears all data if required
        LogStore.clearLogs(this);
        Toast.makeText(this, "All logs cleared!", Toast.LENGTH_SHORT).show();
    }

}