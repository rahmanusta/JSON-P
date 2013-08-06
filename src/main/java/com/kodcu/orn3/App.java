package com.kodcu.orn3;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usta
 * Date: 19.07.2013
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class App {

    private static StringWriter stringWriter;
    private static FileWriter fileWriter;
    private static String key;
    private static String value;

    public static void main(String[] args) throws IOException {

        try (JsonGenerator jsonUretec = Json
                .createGenerator(stringWriter = new StringWriter())) {

            jsonUretec.writeStartObject()
                    .write("ad", "Mahsun Doğan")
                    .write("yas", 25)
                    .writeEnd();
        }

        System.out.println(stringWriter.toString());

        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        File jsonFile=new File("./src/main/resources/file.json");
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
        }
        Files.delete(Paths.get("./src/main/resources/file.json"));

        try (JsonGenerator jsonUretec2 = Json
                .createGeneratorFactory(config)
                .createGenerator(fileWriter = new FileWriter(jsonFile))) {
            jsonUretec2.writeStartObject()
                    .write("ad", "Selçuk Bahadır")
                    .write("yas", 24)
                    .writeStartObject("adres")
                    .write("ulke", "Türkiye")
                    .write("il", "Hakkari")
                    .writeEnd()
                    .writeStartArray("hobiler")
                    .write("Yüzme").write("Atış").write("Futbol")
                    .writeEnd()
                    .writeEnd();
        }

        JsonParser jsonParser = Json.createParser(new URL("http://maps.googleapis.com/maps/api/geocode/json?address=Marmara+University&sensor=false").openStream());

        MultiMap multiMap = MultiValueMap.decorate(new HashMap());
        while (jsonParser.hasNext()) {

            JsonParser.Event next = jsonParser.next();
            switch (next) {
                case START_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case END_ARRAY:
                    break;
                case KEY_NAME:
                    key = jsonParser.getString();
                    break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                case VALUE_TRUE:
                case VALUE_FALSE:
                case VALUE_NULL:
                    value = jsonParser.getString();
                    multiMap.put(key, value);
                    break;

            }

        }
        System.out.println(multiMap);

    }
}
