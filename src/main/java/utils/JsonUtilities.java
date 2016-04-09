package utils;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Business_Book on 09.04.2016.
 */
public class JsonUtilities {

    private static final Logger logger = Logger.getLogger(JsonUtilities.class);

    @JsonCreator
    public static String entityToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    @JsonCreator
    public static <T> T jsonToEntity(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }
}
