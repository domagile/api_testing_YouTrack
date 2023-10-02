package api.youtrack.negativetests;

import api.youtrack.config.TestConfig;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static api.youtrack.constants.Constants.Endpoints.ISSUES;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class UnauthorizedUserTest extends TestConfig {

    @Test
    public void invalidTokenValidation() {
        given()
                .get(ISSUES)
                .then().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .statusLine(containsString("Unauthorized"));
    }
}
