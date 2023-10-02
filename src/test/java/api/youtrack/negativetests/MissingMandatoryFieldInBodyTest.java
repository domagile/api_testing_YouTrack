package api.youtrack.negativetests;

import api.youtrack.config.TestConfig;
import api.youtrack.util.ResourceReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static api.youtrack.constants.Constants.ApiConfiguration.AUTHORIZATION_VALUE;
import static api.youtrack.constants.Constants.Endpoints.ISSUES;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class MissingMandatoryFieldInBodyTest extends TestConfig {

    @Test
    public void createIssueWithoutMandatoryFieldInBody() {
        String bodyWithMissingField = ResourceReader.read("missingMandatoryField.json");

        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .contentType(ContentType.JSON)
                .body(bodyWithMissingField)
                .post(ISSUES)
                .then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", containsString("invalid_properties"));
    }
}
