package model;


import java.util.*;

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

    // EFFECTS: ArrayList sorted by title
    public ArrayList<ArtPiece> sortByTitle() {
        ArrayList<ArtPiece> pieces2;
        pieces2 = (ArrayList<ArtPiece>) gallery.clone();
        pieces2.sort(new TitleComparator());
        return pieces2;
    }

    public ArrayList<ArtPiece> sortById() {
        ArrayList<ArtPiece> pieces2;
        pieces2 = (ArrayList<ArtPiece>) gallery.clone();
        pieces2.sort(new IdComparator());
        return pieces2;
    }

    public ArrayList<ArtPiece> getGalleryAsArrayList() {
        return gallery;
    }

    // EFFECTS: Returns ID of piece with title (Returns -1 if not in list)
    public int titleId(String title) {
        for (ArtPiece piece: gallery) {
            if (piece.getTitle().equals(title)) {
                return piece.getId();
            }
        }
        return -1;
    }

    // REQUIRES: valid id
    // EFFECTS: Returns ArtPiece with ID (Returns null if not in list)
    public ArtPiece pieceById(int id) {
        for (ArtPiece piece: gallery) {
            if (piece.getId() == id) {
                return piece;
            }
        }
        return null;
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
