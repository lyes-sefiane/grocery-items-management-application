package com.lyess.grocery_items_management_desktop_ui.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
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
    private final Resource fxml;
    private final ApplicationContext applicationContext;

    public StageListener(@Value("${spring.application.ui.title}") String applicationTitle,
                         @Value("classpath:welcome/main.fxml") Resource fxml, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.fxml = fxml;
        this.applicationContext = applicationContext;
        logger.info("Resource FXML {}", fxml);
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            Stage stage = stageReadyEvent.getStage();
            URL url = fxml.getURL();
            logger.info("URL URL {}", url);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(this.applicationTitle);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
