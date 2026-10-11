package org.example;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


class BookingApiTest {


    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";


    @Test
    @DisplayName("接口测试")
    void createPost() {

        String body = """
                {
                    "title": "foo",
                    "body": "bar",
                    "userId": 1
                }
                """;

        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }


    @Test
    @DisplayName("查询1号帖子")
    void getPost() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1));
    }

    @Test
    @DisplayName("更新 1 号帖子")
    void updatePost() {

        String body = """
                {
                   "id": 1,
                   "title": "wtt",
                   "body": "bar",
                   "userId": 1
                }
                """;
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("wtt"));
    }


    @Test
    @DisplayName("删除 1 号帖子")
    void deletePost() {
        given()
                .baseUri(BASE_URL)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }


    @Disabled("JSONPlaceholder 不真正保存数据，串联测试无法验证")
    @Test
    @DisplayName("接口串联测试")
    void createThenGet() {
        String body = """
                {
                    "title": "串联测试",
                    "body": "先创建再查询",
                    "userId": 1
                }
                """;
        int postId = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
                .baseUri(BASE_URL)
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .body("id", equalTo(postId));

    }

}