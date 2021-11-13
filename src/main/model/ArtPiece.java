package model;

import org.json.JSONObject;
import persistence.Writable;

//EFFECTS: Represents an artpiece with a title, medium, and subject
public class ArtPiece implements Writable {
    private String title;
    private String medium;
    private String subject;
    private String path = "";

    // REQUIRES: 3 strings: title, medium and subject
    // MODIFIES: this
    // EFFECTS: makes new art piece
    public ArtPiece(String title, String medium, String subject) {
        this.title = title;
        this.medium = medium;
        this.subject = subject;
    }

    // REQUIRES: 3 strings: title, medium and subject
    // MODIFIES: this
    // EFFECTS: makes new art piece
    public ArtPiece(String title, String medium, String subject, String path) {
        this.title = title;
        this.medium = medium;
        this.subject = subject;
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getPath() {
        return path;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("medium", medium);
        json.put("subject", subject);
        json.put("path", path);
        return json;
    }

    @Override
    public String toString() {
        return title;
    }
}

