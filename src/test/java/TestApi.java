import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.basic;
import static org.hamcrest.Matchers.*;

public class TestApi {
    protected static void setupRestAssured() {
        RestAssured.baseURI = "graph.facebook.com";
    }

    @BeforeTest
    public void setUp() {
        setupRestAssured();
    }

    @Test(description = "Create post", priority = 0)
    public void createPost(){
        Map<String,String> post = new HashMap<>();
        post.put("name", "morpheus" );
        post.put("job", "leader" );

        given().
                contentType("application/json")
                .body(post)
                .when().post("/api/users")
                .then().statusCode(201);
    }

    @Test(description = "Change post", priority = 1)
    public void changePost(){
        Map<String,String> user = new HashMap<>();
        user.put("name", "morpheus" );
        user.put("job", "zion resident" );

        given().
                contentType("application/json")
                .body(user)
                .when().put("/api/users")
                .then().statusCode(200);
    }

    @Test(description = "Delete post", priority = 2)
    public void deletePost(){
        given()
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }

}
