package com.kodcu.orn1;

import java.math.BigInteger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

JsonObject jsonObject1 =
        Json.createObjectBuilder()
                .add("ad", "Fikret")
                .add("soyad", "Savaş")
                .add("yas", 25)
        .build();

    System.out.println("jsonObject1 -> "+jsonObject1+"\n\n");


JsonArray jsonArray1= Json.createArrayBuilder()
        .add("A") // String
        .add(5)   // int
        .add(6.3) // double
        .add(false)  // boolean
        .add(new BigInteger("30"))  // Big(Integer) | (Decimal)
        .add(jsonObject1)  // JsonValue
        .build();

    System.out.println("jsonArray1 -> "+jsonArray1+"\n\n");


JsonArray jsonArray2=Json.createArrayBuilder()
        .add("Emin").add("Şahin").build();

    System.out.println("jsonArray2 -> "+jsonArray2+"\n\n");

JsonObject jsonObject2 = Json.createObjectBuilder()
        .add("ad", jsonArray2)
        .add("soyad", "Demir")
        .build();

    System.out.println("jsonObject2 -> "+jsonObject2+"\n\n");

JsonObject jsonObject3 =
    Json.createObjectBuilder()
        .add("ad", "Ersin")
        .add("soyad", "Çetinkaya")
        .add("yas", 25)
        .add("adres",
              Json.createObjectBuilder()
                  .add("sehir", "Bursa")
                  .add("ulke", "Türkiye")
                  .add("Pk", "33444"))
        .add("telefonlar",
                      Json.createArrayBuilder()
                          .add("234234242")
                          .add("345345354"))
        .build();

    System.out.println("jsonObject3 -> "+jsonObject3+"\n\n");

}
}
