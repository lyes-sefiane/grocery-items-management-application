package io.github.lyes_sefiane.grocery_items_management_desktop_ui.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-10-07
 */
@Component
public class FxmlLoader {

    private final ApplicationContext applicationContext;

    /**
     * Default Constructor
     *
     * @param applicationContext: Application Context to Set FXML Controllers as Bean.
     */
    public FxmlLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Load FXML File From FXML Directory
     *
     * @param url : FXML Path
     * @return Parent Node
     * @throws IOException : Throws an Exception On Load If Not Found
     */
    public Parent load(URL url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader.load();
    }
}
