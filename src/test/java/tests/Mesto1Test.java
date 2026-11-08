package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Mesto1Test {

    private String bearerToken;
    private String MESTO_TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2YWYwMjNiOTgyZDhjZTU0NWE4YjVmNGUiLCJpYXQiOjE3OTQxMjI3MDAsImV4cCI6MTc5NDcyNzUwMH0.claP4zhk1wrTss8kIcm1m3uLzm_Vw_0pEIwglgWU8FU";

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.education-services.ru";
        
        // Сначала пробуем получить токен из -Dtoken=... или переменной окружения MESTO_TOKEN
        bearerToken = System.getProperty("token", System.getenv("MESTO_TOKEN"));
        
        // Если токен не задан, авторизуемся по логину и паролю
        if (bearerToken == null || bearerToken.isBlank()) {
            bearerToken = login("caposevas@gmail.com", "123");
        }
        
        assertTrue(bearerToken != null && !bearerToken.isBlank(),
                "Не удалось получить токен: передайте -Dtoken=... или переменную окружения MESTO_TOKEN");
    }
    
    @Step("Login and get token")
    private String login(String email, String password) {
        return given()
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                .post("/api/signin")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

    @Test
    @DisplayName("Add a new photo")
    @Description("This test is for adding a new photo to Mesto.")
    void addNewPhoto() {
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body("{\"name\":\"Москва\",\"link\":\"https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenium.jpg\"}")
                .post("/api/cards")
                .then().statusCode(201);
    }

    @Test
    @DisplayName("Like the first photo")
    @Description("This test is for liking the first photo on Mesto.")
    public void likeTheFirstPhoto() {
        String photoId = getTheFirstPhotoId();

        likePhotoById(photoId);
        deleteLikePhotoById(photoId);
    }

    @Step("Take the first photo from the list")
    private String getTheFirstPhotoId() {
        return given()
                .auth().oauth2(bearerToken)
                .get("/api/cards")
                .then().statusCode(200)
                .extract().body().path("data[0]['_id']");
    }

    @Step("Like a photo by id")
    private void likePhotoById(String photoId) {
        given()
                .auth().oauth2(bearerToken)
                .put("/api/cards/{photoId}/likes", photoId)
                .then().assertThat().statusCode(200);
    }

    @Step("Delete like from the photo by id")
    private void deleteLikePhotoById(String photoId) {
        given()
                .auth().oauth2(bearerToken)
                .delete("/api/cards/{photoId}/likes", photoId)
                .then().assertThat().statusCode(200);
    }
}