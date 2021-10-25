package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads gallery from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads gallery from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Gallery read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGallery(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses gallery from JSON object and returns it
    private Gallery parseGallery(JSONObject jsonObject) {
        Gallery gallery = new Gallery();
        addArtPieces(gallery, jsonObject);
        return gallery;
    }

    // MODIFIES: gallery
    // EFFECTS: parses art piece from JSON object and adds them to gallery
    private void addArtPieces(Gallery gallery, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("gallery");
        for (Object json : jsonArray) {
            JSONObject nextArtPiece = (JSONObject) json;
            addArtPiece(gallery, nextArtPiece);
        }
    }

    // MODIFIES: gallery
    // EFFECTS: parses ArtPiece from JSON object and adds it to gallery
    private void addArtPiece(Gallery gallery, JSONObject jsonObject) {
        String title = String.valueOf(jsonObject.getString("title"));
        String medium = String.valueOf(jsonObject.getString("medium"));
        String subject = String.valueOf(jsonObject.getString("subject"));
        gallery.addPiece(title, medium, subject);
    }
}
