import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    public final Properties properties = new Properties();

    public String getProperty() {
        try {
            properties.load(new FileInputStream("test/resource/urlsAndKeys.properties"));
        } catch (IOException e) {

        }
       return null;
    }
}
