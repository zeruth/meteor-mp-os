package nulled;

import java.net.MalformedURLException;
import java.net.URL;

public class AppletContext {
    public static void showDocument(URL url, String subUrl) {

    }

    public static URL getCodeBase() {
        try {
            return new URL("http://127.0.0.1");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getDocumentBase() {
        return getCodeBase();
    }

    public static void setFocusCycleRoot(boolean b) {

    }
}
