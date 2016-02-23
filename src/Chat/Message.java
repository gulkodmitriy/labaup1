package Chat;

import java.util.Comparator;
import java.util.Random;
import java.util.UUID;

import org.json.simple.JSONObject;


public class Message {
    private String ID;
    private String text;
    private String author;
    private long timestamp;
    private String JsonString;

    Message(String ID, String text, String author, long timestamp) {
        this.ID = ID;
        this.text = text;
        this.author = author;
        this.timestamp = timestamp;
        this.JsonString = this.convertJSON().toString();
    }

    Message(String text, String author, long timestamp) {
        this.ID = IDGenerate();
        this.text = text;
        this.author = author;
        this.timestamp = timestamp;
        this.JsonString = this.convertJSON().toString();
    }

    public String getID() {
        return ID;
    }

    public String getText() {
        return text;
    }

    public String getAutor() {
        return author;
    }

    public String getJsonString() {
        return JsonString;
    }

    public long getDate() {
        return timestamp;
    }

    public String IDGenerate() {
        return UUID.randomUUID().toString();
    }

    public JSONObject convertJSON() {
        JSONObject jobject = new JSONObject();
        jobject.put("id", ID);
        jobject.put("author", author);
        jobject.put("message", text);
        jobject.put("timestamp", timestamp);
        return jobject;
    }

    @Override
    public String toString() {
        return "id: " + ID + "|| author: " +
                author + "|| Message: " + text + "|| timestamp: " + timestamp;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}

