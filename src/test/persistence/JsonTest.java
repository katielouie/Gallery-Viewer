package persistence;

import model.ArtPiece;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkArtPiece(ArtPiece artPiece, String title, String medium, String subject) {
        assertEquals(title, artPiece.getTitle());
        assertEquals(medium, artPiece.getMedium());
        assertEquals(subject, artPiece.getSubject());
    }
}
