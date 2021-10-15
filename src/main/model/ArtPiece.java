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

    public ArtPiece(String title, String medium, String subject) {
        id = idCount;
        idCount++;
        this.title = title;
        this.medium = medium;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMedium() {
        return medium;
    }

    public String getSubject() {
        return subject;
    }

}

