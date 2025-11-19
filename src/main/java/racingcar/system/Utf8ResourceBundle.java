package racingcar.system;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Utf8ResourceBundle extends ResourceBundle.Control {

    @Override
    public ResourceBundle newBundle(
            String baseName,
            Locale locale,
            String format,
            ClassLoader loader,
            boolean reload) throws IOException {
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");

        try (InputStream iStream = loader.getResourceAsStream(resourceName)) {
            if (iStream == null) {
                return null;
            }
            try (InputStreamReader reader = new InputStreamReader(iStream, StandardCharsets.UTF_8)) {
                return new PropertyResourceBundle(reader);
            }
        }
    }
}
