package persistence;

import model.ArtPiece;
import model.Gallery;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Gallery gallery = new Gallery();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            System.out.println("Success");
        }
    }

    @Test
    void testWriterEmptyGallery() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGallery.json");
        try {
            Gallery gallery = new Gallery();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGallery.json");
            writer.open();
            writer.write(gallery);
            writer.close();

            gallery = reader.read();
            assertEquals(0,gallery.size());
        } catch (IOException e) {
            fail("No exception should have been thrown");
        }
    }

    @Test
    void testWriterGeneralGallery() {
        try {
            Gallery gallery = new Gallery();
            gallery.addPiece("Whale","digital","portrait");
            gallery.addPiece("Dolphin","digital","portrait");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGallery.json");
            writer.open();
            writer.write(gallery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGallery.json");
            gallery = reader.read();
            ArrayList<ArtPiece> artPieces = gallery.getGalleryAsArrayList();
            assertEquals(2, artPieces.size());
            checkArtPiece(artPieces.get(0),"Whale","digital","portrait");
            checkArtPiece(artPieces.get(1),"Dolphin","digital","portrait");
        } catch (IOException e) {
            fail("No exception should have been thrown");
        }
    }
}
