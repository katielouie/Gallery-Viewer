package model;

import java.util.Comparator;

public class TitleComparator implements Comparator<ArtPiece> {
    @Override
    public int compare(ArtPiece p1, ArtPiece p2) {
        return p1.getTitle().compareTo(p2.getTitle());
    }
}
