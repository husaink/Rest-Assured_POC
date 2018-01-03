package automation;

import com.digital.config.ConfigProvider;
import com.digital.headers.Header;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.internal.path.json.JSONAssertion;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import utils.Helpers;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class RestTests {

    
    private Header header;

    
    @Before
    public void setUp() {
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.baseURI = ConfigProvider.config().getString("baseURI");
    }


    /**
     * Create a new Post
     * @throws Exception
     */
    
    @Test
    public void createNewPost() throws Exception {
        
    	String request = Helpers.readFile("request/CreateNewPost.json");
    	
    	given().
        headers(Header.setHeaders()).
        body(request).
        when().log().all().
            post("/posts").
        then().
            statusCode(HttpStatus.SC_CREATED).
            body("id", equalTo(101));
    }

    
    /**
     * Update Existing Post
     * @throws Exception
     */
    
    @Test
    public void updateExistingPost() throws Exception {
        Response response =
        given().
            formParam("title", "Foo").
        when().log().all().
            put("/posts/7");

        System.out.print(response.asString());
        response.then().
                body("title", equalTo("Foo")).
                statusCode(HttpStatus.SC_OK);
    }

    /**
     * Delete Specific Post
     * @throws Exception
     */
    @Test
    public void deleteSpecificPost() throws Exception {
        when().
                delete("/posts/32").
        then().log().all().
                statusCode(HttpStatus.SC_OK);
    }

    /**
     * Read comments for particular post.
     * @throws Exception
     */
    
    @Test
    public void readComments() throws Exception {
        given().
                param("postId", 1).
        when().
                get("/comments").
        then().log().all().
                body(".", hasSize(5));
    }
}
