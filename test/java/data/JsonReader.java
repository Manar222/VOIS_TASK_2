package data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    static String jsonPath = "src/test/java/data/DataFile.json";

    public static String jsonData(String objectName , String dataFromJson) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(jsonPath));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject ValidUser = (JSONObject) jsonObject.get(objectName);
        String data = (String) ValidUser.get(dataFromJson);


        return data;
    }
}
