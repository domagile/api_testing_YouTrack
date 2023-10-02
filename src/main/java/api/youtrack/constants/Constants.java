package api.youtrack.constants;

public class Constants {
    public static class ApiConfiguration {
        public static String YOUTRACK_BASE_URL = "https://mytestapi.youtrack.cloud/api";
        public static String ACCESS_TOKEN = "perm:cm9vdA==.NDktMA==.3ofVVp8eR7daEp5mCgogSgBqqZx9IA";
        public static String AUTHORIZATION_VALUE = "Bearer " + ACCESS_TOKEN;
    }

    public static class Endpoints {
        public static String ISSUES = "issues";
        public static String PROJECT = "/admin/projects";
    }
}
