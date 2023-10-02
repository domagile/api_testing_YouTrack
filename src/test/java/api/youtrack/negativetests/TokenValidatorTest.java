package api.youtrack.negativetests;

import api.youtrack.config.TestConfig;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static api.youtrack.constants.Constants.Endpoints.ISSUES;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class TokenValidatorTest extends TestConfig {
    public static String OPTIONS = "fields=id,name,shortName,createdBy(login,name,id),leader(login,name,id),key&$top=10";
    private static final String INVALID_TOKEN = "perm:cm9vdA==.NDktMA==.3ofVVp8eR7daEp5mCgogSinvalid";
    private static final String AUTHORIZATION_INVALID_TOKEN = "Bearer " + INVALID_TOKEN;

    @Test
    public void invalidTokenValidation() {
        given()
                .header("Authorization", AUTHORIZATION_INVALID_TOKEN)
                .get("/{issueEndpoint}?{options}", ISSUES, OPTIONS)
                .then().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("error", containsString("Unauthorized"))
                .body("error_description", containsString("You are not logged in."));
    }
}
