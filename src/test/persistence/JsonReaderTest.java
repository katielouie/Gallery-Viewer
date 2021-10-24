package persistence;

import model.ArtPiece;
import model.Gallery;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Gallery gallery = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGallery() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGallery.json");
        try {
            Gallery gallery = reader.read();
            assertEquals(0,gallery.size());
        } catch (IOException e) {
            fail("No exception should have been thrown");
        }
    }

    @Test
    void testReaderGeneralGallery() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderGeneralGallery.json");
            Gallery gallery = reader.read();
            ArrayList<ArtPiece> artPieces = gallery.getGalleryAsArrayList();
            assertEquals(2, artPieces.size());
            checkArtPiece(artPieces.get(0),"Llama","digital","portrait");
            checkArtPiece(artPieces.get(1),"Alpaca","digital","portrait");
        } catch (IOException e) {
            fail("No exception should have been thrown");
        }
    }


}
