package app.domain.services.base;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonService {
    JSONParser parser = new JSONParser();

    private String _filePath;

    public JsonService(String filePath) {
        this._filePath = filePath;
    }

    public FileReader readJsonData(String filepath) throws IOException {
        return new FileReader(filepath);
    }

    public JSONArray convertFileToJson(FileReader file) throws IOException, ParseException {
        Object object = parser.parse(file);
        JSONArray modelList = (JSONArray) object;
        return modelList;
    }

    public JSONArray readJSON() throws IOException, ParseException {
        FileReader file = readJsonData(_filePath);
        return convertFileToJson(file);
    }
}
