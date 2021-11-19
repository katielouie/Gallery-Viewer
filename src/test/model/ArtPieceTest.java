package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// EFFECTS: Tests mehods in Art Piece
public class ArtPieceTest {
    ArtPiece artPiece;
    String title = "t";
    String medium = "m";
    String subject = "s";
    String path = "p";

    @BeforeEach
    public void setup() {
        artPiece = new ArtPiece(title,medium,subject,path);
    }

    @Test
    public void testConstructor() {
        assertEquals(title, artPiece.getTitle());
        assertEquals(subject, artPiece.getSubject());
        assertEquals(medium, artPiece.getMedium());
        assertEquals(path, artPiece.getPath());
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
    public void testSetPath() {
        artPiece.setPath("n");
        assertEquals(artPiece.getPath(),"n");
    }

    @Test
    public void testToString() {
        assertEquals(artPiece.toString(),title);
    }


}
