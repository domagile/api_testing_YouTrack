package api.youtrack.config;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import static api.youtrack.constants.Constants.ApiConfiguration.YOUTRACK_BASE_URL;

public class TestConfig {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = YOUTRACK_BASE_URL;
    }

}
