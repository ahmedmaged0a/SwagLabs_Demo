package com.swaglabs.utils;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtils {
    private static final String JSON_FILE_PATH = "src/test/resources/";
    String jsonReader;
    String jsonFileName;

    /**
     * Constructor to initialize the JsonUtils with a specific JSON file.
     *
     * @param jsonFileName the name of the JSON file (without extension)
     */
    public JsonUtils(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject testData = (JSONObject) new JSONParser().parse(new FileReader(JSON_FILE_PATH + jsonFileName + ".json"));
            jsonReader = testData.toJSONString();
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }
    }

    /**
     * Reads JSON data from the specified path using JsonPath.
     *
     * @param JSON_PATH the path to the JSON data (e.g., "Login-credentials.valid.username")
     * @return the JSON data as a String, or null if an error occurs
     */
    public String getJsonData(String JSON_PATH) {
        String testData = "";
        try {
            testData = JsonPath.read(jsonReader, JSON_PATH);
        } catch (Exception e) {
            LogsManager.error("Error reading JSON data from path: " + JSON_PATH + " - " + e.getMessage());
            return null;
        }
        return testData;
    }

    // Method to overwrite JSON data at a specific path
    public void setJsonData(String JSON_PATH, String data) {
        String testData = "";
        try {
            jsonReader = JsonPath.parse(jsonReader).set(JSON_PATH, data).jsonString();
            LogsManager.info("Successfully set JSON data at path: " + JSON_PATH);
        } catch (Exception e) {
            LogsManager.error("Error setting JSON data at path: " + JSON_PATH + " - " + e.getMessage());
        }
    }
}
