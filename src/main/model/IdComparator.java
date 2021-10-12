package model;

import java.util.Comparator;

public class IdComparator implements Comparator<ArtPiece> {
    @Override
    public int compare(ArtPiece p1, ArtPiece p2) {
        return p1.getId() - p2.getId();
    }
}
