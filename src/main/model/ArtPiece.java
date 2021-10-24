package model;

import org.json.JSONObject;
import persistence.Writable;

//EFFECTS: Represents an artpiece with a title, medium, and subject
public class ArtPiece implements Writable {
    private String title;
    private String medium;
    private String subject;

    // REQUIRES: 3 strings: title, medium and subject
    // MODIFIES: this
    // EFFECTS: makes new art piece
    public ArtPiece(String title, String medium, String subject) {
        this.title = title;
        this.medium = medium;
        this.subject = subject;
    }

    // EFFECTS: returns Title
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns Medium
    public String getMedium() {
        return medium;
    }

    // EFFECTS: returns Subject
    public String getSubject() {
        return subject;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("medium", medium);
        json.put("subject", subject);
        return json;
    }
}

