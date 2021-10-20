package model;


import model.comparators.TitleComparator;

import java.util.*;

// EFFECTS: Stores artpieces
public class Gallery {
    private ArrayList<ArtPiece> gallery;

    public Gallery() {
        gallery = new ArrayList<>();
    }

    // REQUIRES: title, medium, and subject
    // MODIFIES: this
    // EFFECTS: Adds new piece to gallery
    public void addPiece(String title, String medium, String subject) {
        ArtPiece newPiece = new ArtPiece(title, medium, subject);
        gallery.add(newPiece);
    }

    public void addPiece(ArtPiece artPiece) {
        gallery.add(artPiece);
    }

    // EFFECTS: Returns gallery as ArrayList sorted by title
    public ArrayList<ArtPiece> sortByTitle() {
        ArrayList<ArtPiece> pieces2;
        pieces2 = (ArrayList<ArtPiece>) gallery.clone();
        pieces2.sort(new TitleComparator());
        return pieces2;
    }

    // EFFECTS: Returns gallery as arraylist sorted by ID
    public ArrayList<ArtPiece> sortById() {
        ArrayList<ArtPiece> pieces2;
        pieces2 = (ArrayList<ArtPiece>) gallery.clone();
        return pieces2;
    }

    // EFFECTS: Returns gallery as arraylist
    public ArrayList<ArtPiece> getGalleryAsArrayList() {
        return gallery;
    }

    // EFFECTS: Returns ID of piece with title (Returns -1 if not in list)
    public int titleIndex(String title) {
        int i = 1;
        for (ArtPiece piece: gallery) {
            if (piece.getTitle().equals(title)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    // EFFECTS: Returns ArtPiece with Index (Returns null if id is not in list)
    public ArtPiece pieceByIndex(int index) {
        if (index > gallery.size() - 1) {
            return null;
        }
        return gallery.get(index);
    }

    // EFFECTS: Returns ArrayList with only a certain medium
    public ArrayList<ArtPiece> filterByMedium(String medium) {
        ArrayList<ArtPiece> mediumList = new ArrayList<>();
        for (ArtPiece piece: gallery) {
            if (piece.getMedium().equalsIgnoreCase(medium)) {
                mediumList.add(piece);
            }
        }
        return mediumList;
    }

    // EFFECTS: Returns ArrayList with only a certain subject
    public ArrayList<ArtPiece> filterBySubject(String subject) {
        ArrayList<ArtPiece> subjectList = new ArrayList<>();
        for (ArtPiece piece: gallery) {
            if (piece.getSubject().equalsIgnoreCase(subject)) {
                subjectList.add(piece);
            }
        }
        return subjectList;

    }

    // EFFECTS: Returns if there's an artpiece with medium in gallery
    public boolean containsMedium(String medium) {
        for (ArtPiece piece: gallery) {
            if (piece.getMedium().equalsIgnoreCase(medium)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns if there's an artpiece with subject in gallery
    public boolean containsSubject(String subject) {
        for (ArtPiece piece: gallery) {
            if (piece.getSubject().equalsIgnoreCase(subject)) {
                return true;
            }
        }
        return false;
    }

}
