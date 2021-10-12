package model;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.*;

public class Gallery {
    private ArrayList<ArtPiece> gallery;

    public Gallery() {
        gallery = new ArrayList<>();
    }

    // REQUIRES: title, medium, and subject
    // MODIFIES: this
    // EFFECTS: Adds new piece to gallery and returns if piece was added (returns false if already contains)
    public boolean addPiece(String title, String medium, String subject) {
        if (titleId(title) == -1) {
            ArtPiece newPiece = new ArtPiece(title, medium, subject);
            gallery.add(newPiece);
            return true;
        }
        return false;
    }

    // REQUIRES: ArtPiece ArrayList
    // EFFECTS: Displays titles of all Artpieces in ArrayList by id
    public void displayListById(ArrayList<ArtPiece> gallery) {
        for (ArtPiece artPiece: gallery) {
            System.out.println(artPiece.getTitle());
        }
    }

    // EFFECTS: ArrayList sorted by title
    public ArrayList<ArtPiece> sortByTitle(ArrayList<ArtPiece> pieces) {
        ArrayList<ArtPiece> pieces2;
        pieces2 = (ArrayList<ArtPiece>) pieces.clone();
        pieces2.sort(new TitleComparator());
        return pieces2;
    }

    public ArrayList<ArtPiece> sortById(ArrayList<ArtPiece> pieces) {
        ArrayList<ArtPiece> pieces2;
        pieces2 = (ArrayList<ArtPiece>) pieces.clone();
        pieces2.sort(new IdComparator());
        return pieces2;
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
    // EFFECTS: Returns ArtPiece with ID (Returns -1 if not in list)
    public ArtPiece pieceById(int id) {
        for (ArtPiece piece: gallery) {
            if (piece.getId() == id) {
                return piece;
            }
        }
        throw new NoSuchElementException();
    }

    // EFFECTS: Returns ArrayList with only a certain medium
    public ArrayList<ArtPiece> limitByMedium(String medium) {
        ArrayList<ArtPiece> mediumList = new ArrayList<>();
        for (ArtPiece piece: gallery) {
            if (piece.getMedium().equals(medium)) {
                mediumList.add(piece);
            }
        }
        return mediumList;

    }

    // EFFECTS: Returns ArrayList with only a certain subject
    public ArrayList<ArtPiece> limitBySubject(String subject) {
        ArrayList<ArtPiece> subjectList = new ArrayList<>();
        for (ArtPiece piece: gallery) {
            if (piece.getMedium().equals(subject)) {
                subjectList.add(piece);
            }
        }
        return subjectList;

    }

}
