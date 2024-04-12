package PandaZooData;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.net.URI;

public class Reference {

   public static void openReference(String reference)
    {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(reference));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
