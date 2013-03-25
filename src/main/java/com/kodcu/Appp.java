package com.kodcu;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: usta
 * Date: 3/24/13
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Appp {
    public static void main(String[] args) throws IOException {
        Map<String, Boolean> config = new HashMap<>();

        // Basit bir dizi
        JsonArray jsonArray = Json.createArrayBuilder()
                .add("Emin").add("Şahin").build();

        // Json dizisi içeren, basit bir nesne
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("ad", jsonArray)
                .add("soyad", "Demir")
                .build();

        try (PrintWriter pw = new PrintWriter("./src/main/resources/jsonObject.json")) {

            //  JsonWriter jsonWriter = Json.createWriter(pw);

            //   jsonWriter.writeObject(jsonObject);


            config.put(JsonGenerator.PRETTY_PRINTING, true);

            Json.createWriterFactory(config).createWriter(pw).writeObject(jsonObject);
        }

        JsonReader reader = Json
                .createReader(new FileReader("./src/main/resources/jsonObject.json"));

        JsonObject obj = reader.readObject();

        System.out.println("Okunan: "+obj.toString());

        URL url = new URL("http://search.twitter.com/search.json?q=kodcucom&page=1");
        InputStream is = url.openStream();

        JsonObject twitterObj = Json
                                .createReader(is)
                                .readObject();

        try (PrintWriter pw = new PrintWriter("./src/main/resources/twitterObj.json")) {

          //  JsonWriter jsonWriter = Json.createWriter((pw));
          //  jsonWriter.write(twitterObj);

            Json
                .createWriterFactory(config)
                .createWriter(pw)
                .writeObject(twitterObj);
        }

    }
}
