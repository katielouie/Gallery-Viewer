package model;

import java.util.Comparator;

public class ArtPiece {
    private static int idCount = 1;
    private final int id;
    private String title;
    private String medium;
    private String subject;

    public ArtPiece(String title) {
        id = idCount;
        idCount++;
        this.title = title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // EFFECTS: Prints out details (title, medium, subject) of piece`
    public void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Medium: " + medium);
        System.out.println("Subject: " + subject);
        //stub
    }

    public class IdComparator implements Comparator<ArtPiece> {
        @Override
        public int compare(ArtPiece p1, ArtPiece p2) {
            return p1.getId() - p2.getId();
        }
    }

    public class TitleComparator implements Comparator<ArtPiece> {
        @Override
        public int compare(ArtPiece p1, ArtPiece p2) {
            return p1.getTitle().compareTo(p2.getTitle());
        }
    }

}

