package com.amshaik1.EmotiLog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/* Adapter between LogEntry and RecyclerView, takes each LogEntry and uses item_log.xml to display output */

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.VH> {
    private List<LogEntry> data;
    private DateFormat df = DateFormat.getDateTimeInstance();

    public LogAdapter(List<LogEntry> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //creates new row for RecyclerView
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) { //binds data in the row
        LogEntry e = data.get(position);
        holder.title.setText(e.emotion);
        holder.subtitle.setText(df.format(new Date(e.timestamp)));
    }

    @Override
    public int getItemCount() { //number of rows
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView title, subtitle;
        VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.logTitle);
            subtitle = itemView.findViewById(R.id.logSubtitle);
        }
    }
}
