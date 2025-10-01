package com.amshaik1.EmotiLog;

/* holds data for one log row */

public class LogEntry {
    public long id;
    public String emotion;
    public long timestamp;

    public LogEntry(long id, String emotion, long timestamp) {
        this.id = id;
        this.emotion = emotion;
        this.timestamp = timestamp;
    }
}

