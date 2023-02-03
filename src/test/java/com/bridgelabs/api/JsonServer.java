//**********************************************
//Author Name : Ekta Mohite
//Project     : Rest Assured APis/ Json-Server
//Date        : 29/01/2023
//**********************************************
package com.bridgelabs.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JsonServer {
    //posts
   @Test
    public void getPostData(){
       Response result = given().contentType("application/json")
               .accept("application/json")
               .when()
               .get("http://localhost:3000/posts");
       result.prettyPrint();
       result.then().assertThat().statusCode(200);
    }
    @Test
    public void postAllData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "        \"id\": 4,\n" +
                        "        \"title\": \"json-server\",\n" +
                        "        \"author\": \"Sam\"\n" +
                        "    }")
                .when()
                .post("http://localhost:3000/posts");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
    }
    @Test
    public void updateAllData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "        \"id\": 4,\n" +
                        "        \"title\": \"json-server\",\n" +
                        "        \"author\": \"Ekta\"\n" +
                        "    }")
                .when()
                .put("http://localhost:3000/posts/4");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
   }
   @Test
    public void deleteDataInPost() {
       Response result = given().contentType("application/json")
               .accept("application/json")
               .when()
               .queryParam("id", "2")
               .delete("http://localhost:3000/posts/2");
       result.prettyPrint();
       result.then().assertThat().statusCode(200);
   }
   //comments
   @Test
   public void getCommentsData() {
       Response result = given().contentType("application/json")
               .accept("application/json")
               .when()
               .get("http://localhost:3000/comments");
       result.prettyPrint();
       result.then().assertThat().statusCode(200);
   }
    @Test
    public void postCommentsData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "        \"id\": 2,\n" +
                        "        \"body\": \"some comment\",\n" +
                        "        \"postId\": 2\n" +
                        "    }")
                .when()
                .post("http://localhost:3000/comments");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
    }
    @Test
    public void updateCommentsData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "      \"id\": 2,\n" +
                        "      \"body\": \"ment\",\n" +
                        "      \"postId\": 2\n" +
                        "    }")
                .when()
                .put("http://localhost:3000/comments/2");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }
    @Test
    public void deleteCommentsData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .queryParam("id", "2")
                .delete("http://localhost:3000/comments/2");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }
    //profile
    @Test
    public void getProfileData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .get("http://localhost:3000/profile");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }
    @Test
    public void postprofileData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "    \"name\": \"code\"\n" +
                        "  }")
                .when()
                .post("http://localhost:3000/profile");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
    }
    @Test
    public void updateProfileData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "    \"name\": \"typicode\"\n" +
                        "  }")
                .when()
                .put("http://localhost:3000/profile");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }
    @Test
    public void deleteProfileData() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .delete("http://localhost:3000/profile/1");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }
}
