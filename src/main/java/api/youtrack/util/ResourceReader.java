package api.youtrack.util;

import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResourceReader {
    public static String read(String resourcePath) {
        try {
            URL resourceUrl = Resources.getResource(resourcePath);
            return Resources.toString(resourceUrl, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
