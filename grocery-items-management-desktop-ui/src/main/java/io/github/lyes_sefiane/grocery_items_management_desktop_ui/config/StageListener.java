package io.github.lyes_sefiane.grocery_items_management_desktop_ui.config;

import io.github.lyes_sefiane.grocery_items_management_desktop_ui.alert.AlertMessage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * <a href="https://github.com/ecovaci/javafx-spring-boot/tree/master">Reference</a>
 */
@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {

    Logger logger = LoggerFactory.getLogger(StageListener.class);

    private final String applicationTitle;

    private final Resource fxmlResource;

    private final FxmlLoader fxmlLoader;

    public StageListener(@Value("${spring.application.ui.title}") String applicationTitle,
                         @Value("classpath:/fxml/welcome/main.fxml") Resource fxmlResource, FxmlLoader fxmlLoader) {
        this.applicationTitle = applicationTitle;
        this.fxmlResource = fxmlResource;
        this.fxmlLoader = fxmlLoader;
    }

    @Override
    public void onApplicationEvent(@NonNull StageReadyEvent stageReadyEvent) {
        logger.info("Resource FXML {}", fxmlResource);
        try {
            Stage stage = stageReadyEvent.getStage();
            URL url = fxmlResource.getURL();
            Scene scene = new Scene(fxmlLoader.load(url));
            stage.setScene(scene);
            stage.setTitle(this.applicationTitle);
            stage.setMaximized(true);
            stage.getIcons().add(new Image("/images/application-icon.png"));
            stage.show();
        } catch (IOException e) {
            Platform.runLater(() -> new AlertMessage(Alert.AlertType.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()));
        }
    }
}
