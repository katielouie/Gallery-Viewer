package model;

import java.util.Comparator;

public class ArtPiece {
    private static int idCount = 1;
    private final int id;
    private String title;
    private String medium;
    private String subject;

    // REQUIRES: ONLY TO BE USED FOR TESTING TO RESET THE ID!!!!
    // EFFECTS: RESETS ID
    public void resetId() {
        idCount = 1;
    }

    // REQUIRES: 3 strings: title, medium and subject
    // MODIFIES: this
    // EFFECTS: makes new artpiece
    public ArtPiece(String title, String medium, String subject) {
        id = idCount;
        idCount++;
        this.title = title;
        this.medium = medium;
        this.subject = subject;
    }

    // EFFECTS: returns id
    public int getId() {
        return id;
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

}

