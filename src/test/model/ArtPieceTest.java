package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArtPieceTest {
    ArtPiece artPiece;
    String title = "t";
    String medium = "m";
    String subject = "s";

    @BeforeEach
    public void setup() {
        artPiece = new ArtPiece(title,medium,subject);
        artPiece.resetId();
        artPiece = new ArtPiece(title,medium,subject);
    }

    @Test
    public void testConstructor() {
        assertEquals(title, artPiece.getTitle());
        assertEquals(subject, artPiece.getSubject());
        assertEquals(medium, artPiece.getMedium());
        assertEquals(1 ,artPiece.getId());
    }

    @Test
    public void testGetId() {
        assertEquals(1, artPiece.getId());
        int c = 10;
        for (int i = 2; i < c; i++) {
            ArtPiece p = new ArtPiece(title, medium, subject);
            assertEquals(i,p.getId());
        }
    }

    @Test
    public void testGetTitle() {
        assertEquals(title, artPiece.getTitle());
    }

    @Test
    public void testGetMedium() {
        assertEquals(medium, artPiece.getMedium());
    }

    @Test
    public void testGetSubject() {
        assertEquals(subject, artPiece.getSubject());
    }

    @Test
    public void testIdComparator() {
        ArtPiece p2 = new ArtPiece("a","b","c");
        IdComparator comparator = new IdComparator();
        assertEquals(-1,comparator.compare(artPiece,p2));
    }

}
