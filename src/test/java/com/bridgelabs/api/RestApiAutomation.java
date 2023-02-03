//************************************
//Author Name : Ekta Mohite
//Project     : Rest Assured APis/ Spotify
//Date        : 29/01/2023
//************************************

package com.bridgelabs.api;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class RestApiAutomation {
    public String Authorization;
    static String userId;
    static String playlistId;

    @BeforeTest
    public void setUp() {
        Authorization = "Bearer BQDTlI0AdBD9Di80f44omXqHCti8rxB0owCBTRuy2wz" +
                "1llOKVwUUI1xv8zuphNKvLiRRY-MuVTajghm67Ls5C__ENudMsqK1-p8Rq" +
                "AufLn70n_IMOqtOIiJ9__yX-ak0dgqI_fJAAJeNa3bs09qCnPGIRNUJDXg" +
                "_SaLLr5J59cqGCuVO6esjS3ijIjMmiQe68Yak17hmfMEyC2JymTDgKSdz2F" +
                "cOsCzL8EQyudIt6GaFqORaBABmTmvFa3avHjwF5EsMhZCRksLfqw";

        playlistId="57StehxjgYCGJe5E7RGjAM";
    }
    //users profile

    @Test(priority = 1)
    public void getCurrentUserProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/me");
        userId = result.path("id");
        System.out.println("my user id: " + userId);
        String userName = result.path("display_name");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        Assert.assertEquals("Ekta/LTWT", userName);
        System.out.println("UserName: " + userName);
        System.out.println(given().when().get("https://api.spotify.com/v1/me").timeIn(TimeUnit.MILLISECONDS));
    }

    @Test(priority = 2)
    public void getUsersProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/users/" + userId);
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/users/" + userId).timeIn(TimeUnit.MILLISECONDS));
    }

    //playlists
    @Test(priority = 3)
    public void CreatePlaylist() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name","Sam Playlist");
        requestBody.put("description","New playlist description");
        requestBody.put("public","false");
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body(requestBody.toJSONString())
                .when()
                .post("https://api.spotify.com/v1/users/" + userId + "/playlists");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(201);
        System.out.println(given().when().post("https://api.spotify.com/v1/users/" + userId + "/playlists").timeIn(TimeUnit.MILLISECONDS));

    }

    @Test(priority = 4)
    public void addItemstoPlaylist() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body("{\"uris\":[\"spotify:track:1301WleyT98MSxVHPZCA6M\",\"spotify:episode:512ojhOuo1ktJprKbVcKyQ\"]}")
                .when()
                .post("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(201);
        System.out.println(given().when().post("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks").timeIn(TimeUnit.MILLISECONDS));
    }

    @Test(priority = 5)
    public void updatePlaylistItems() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("range_start",1);
        requestBody.put("insert_before",3);
        requestBody.put("range_length",2);
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body(requestBody.toJSONString())
                .when()
                .put("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().put("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks").timeIn(TimeUnit.MILLISECONDS));
    }

    @Test(priority = 6)
    public void changePlaylistDetails() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body("{\"name\":\"UpdatedSam Playlist Name\",\"description\":\"Updated playlist description\",\"public\":false}")
                .when()
                .put("https://api.spotify.com/v1/playlists/" + playlistId);
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().put("https://api.spotify.com/v1/playlists/" + playlistId).timeIn(TimeUnit.MILLISECONDS));
    }

    @Test(priority = 7)
    public void getUsersPlaylists() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/users/" + userId + "/playlists");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/users/" + userId + "/playlists").timeIn(TimeUnit.MILLISECONDS));
    }


    @Test(priority = 8)
    public void getPlaylist() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/playlists/" + playlistId);
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/playlists/" + playlistId).timeIn(TimeUnit.MILLISECONDS));
    }


    @Test(priority = 9)
    public void getPlaylistItems() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/playlists/" + playlistId).timeIn(TimeUnit.MILLISECONDS));
    }


    @Test(priority = 10)
    public void getPlaylistCoverImage() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/playlists/" + playlistId + "/images");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/playlists/" + playlistId).timeIn(TimeUnit.MILLISECONDS));

    }

    @Test(priority = 11)
    public void getCurrentUsersPlaylists() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/playlists/" + playlistId).timeIn(TimeUnit.MILLISECONDS));
    }

    @Test(priority = 12)
    public void removePlaylistItems() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body("{\"tracks\":[{\"uri\":\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\"},{\"uri\":\"spotify:track:1301WleyT98MSxVHPZCA6M\"}]}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/playlists/" + playlistId).timeIn(TimeUnit.MILLISECONDS));
    }

    //	Tracks
    @Test(priority = 13)
    public void getTracksAudioAnalysis() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/audio-analysis/11dFghVXANMlKmJXsNCbNl");
        result.prettyPrint();
        result.then().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/audio-analysis/11dFghVXANMlKmJXsNCbNl").timeIn(TimeUnit.MILLISECONDS));
    }

    @Test(priority = 14)
    public void getTracksAudioFeatures() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/audio-features?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        result.prettyPrint();
        result.then().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/audio-features?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B").timeIn(TimeUnit.MILLISECONDS));
    }

    @Test(priority = 15)
    public void getSeveralTracks() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        result.prettyPrint();
        result.then().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B").timeIn(TimeUnit.MILLISECONDS));
    }

    @Test(priority = 16)
    public void getTrack() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl");
        result.prettyPrint();
        result.then().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl").timeIn(TimeUnit.MILLISECONDS));
    }

    //search
    @Test(priority = 17)
    public void SearchForItemQuery() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .queryParam("q", "artist")
                .queryParam("type", "track")
                .when()
                .get("https://api.spotify.com/v1/search?q=emaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis");
        result.prettyPrint();
        result.then().statusCode(200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/search?q=emaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis").timeIn(TimeUnit.MILLISECONDS));
    }

    //shows
    @Test(priority = 18)
    public void getSeveralShows() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5as3aKmN2k11yfDDDSrvaZ");
        result.prettyPrint();
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/shows?ids=5as3aKmN2k11yfDDDSrvaZ").timeIn(TimeUnit.MILLISECONDS));
    }
    @Test(priority = 19)
    public void getShowEpisodes() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes" );
        result.prettyPrint();
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes" ).timeIn(TimeUnit.MILLISECONDS));
    }
    @Test(priority = 20)
    public void getShow() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        result.then().log().ifValidationFails().statusCode(200);
        System.out.println(given().when().get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ").timeIn(TimeUnit.MILLISECONDS));
    }
}

