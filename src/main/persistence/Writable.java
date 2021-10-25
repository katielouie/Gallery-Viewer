package persistence;

import org.json.JSONObject;

// Adds ability to convert file to a JSON Object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
