package unit6.task1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyParser {

    public Map<Object,Object> parseProperty(String filePath) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(filePath));
        Map<Object,Object> result = new HashMap<>();
        prop.keySet().forEach(e -> result.put(e, prop.get(e)));
        return result;
    }
}
