package unit6.task1;

import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws IOException {
        var propertyParser = new PropertyParser();
        var properties = propertyParser.parseProperty("unit6/src/main/resources/test.properties");
        System.out.println(properties);
    }
}
