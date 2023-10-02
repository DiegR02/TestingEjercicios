package Ejercicio3;

import com.google.gson.*;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class Ejercicio3 {
    public Ejercicio3() {
        RestAssured.config = RestAssuredConfig.newConfig().sslConfig(RestAssuredConfig.newConfig().getSSLConfig().relaxedHTTPSValidation());
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void test1() throws FileNotFoundException {

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        FileReader fr;

        fr = new FileReader("src/json/request.json");
        JsonElement datos = parser.parse(fr);
        JsonObject jobject = datos.getAsJsonObject();
        JsonPrimitive username = jobject.getAsJsonPrimitive("username");

        String usernameValue = username.getAsString();

        datos.isJsonArray();

        String jsonFilePath = "src/json/request.json";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new File(jsonFilePath))
                .post("/user")
                .then()
                .statusCode(200);

        System.out.println("\n**** Se ha realizado el Test 1. La solicitud se completó correctamente. ****\n");


        RestAssured.given()
                .get("/user/" + usernameValue)
                .then()
                .statusCode(200);

    }

    @Test
    public void test2() {
        List<Map<String, Object>> petInfo = RestAssured.given()
                .get("/pet/findByStatus?status=sold")
                .then()
                .statusCode(200)
                .body("status", everyItem(equalTo("sold")))
                .extract()
                .jsonPath()
                .getList("");

        System.out.println("\n**** Se ha realizado el Test 2. La solicitud se completó correctamente. ****\n");

        for (Map<String, Object> pet : petInfo) {
            String id = pet.get("id").toString();
            String name = (String) pet.get("name");
            String status = (String) pet.get("status");

            System.out.println("Id: " + id + ", Nombre de la mascota: " + name);
        }
    }

    @Test
    public void test3() {
        List<Map<String, Object>> petInfo = RestAssured.given()
                .get("/pet/findByStatus?status=sold")
                .then()
                .statusCode(200)
                .body("status", everyItem(equalTo("sold")))
                .extract()
                .jsonPath()
                .getList("");

        System.out.println("\n**** Se ha realizado el Test 3. La solicitud se completó correctamente. ****\n");

        Map<String, Long> petCountByName = PetCounter.countPetsByName(petInfo);

        petCountByName.forEach((name, count) -> {
            System.out.println("    Nombre de la mascota: " + name + ", Cantidad: " + count);
        });
    }

}



