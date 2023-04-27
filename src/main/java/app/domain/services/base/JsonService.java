package app.domain.services.base;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public abstract class JsonService {
    JSONParser parser = new JSONParser();

    private String _filePath;

    public JsonService(String filePath) {
        this._filePath = filePath;
    }

    protected FileReader readJsonData(String filepath) throws IOException {
        return new FileReader(filepath);
    }

    protected JSONArray convertFileToJson(FileReader file) throws IOException, ParseException {
        Object object = parser.parse(file);
        JSONArray modelList = (JSONArray) object;
        return modelList;
    }

    protected JSONArray readJSON() throws IOException, ParseException {
        FileReader file = readJsonData(_filePath);
        return convertFileToJson(file);
    }
}
