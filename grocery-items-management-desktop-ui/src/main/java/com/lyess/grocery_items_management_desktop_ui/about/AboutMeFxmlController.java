package com.lyess.grocery_items_management_desktop_ui.about;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 *
 */
@Component
public class AboutMeFxmlController {

    @FXML
    private Circle circleInside;

    @FXML
    private Label aboutMeLabel;

    @FXML
    private Hyperlink gitHubHyperlink;

    @FXML
    private Hyperlink websiteHyperlink;

    @Value("${about.me.text}")
    private String aboutMeTex;

    @Value("${github.url}")
    private String gitHubUrl;

    @Value("${website.url}")
    private String websiteUrl;

    private final ApplicationContext applicationContext;

    public AboutMeFxmlController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize () {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/lyes-sefiane.jpg")));
        circleInside.setFill(new ImagePattern(image));
        aboutMeLabel.setText(aboutMeTex);
        gitHubHyperlink.setText(gitHubUrl);
        websiteHyperlink.setText(websiteUrl);
    }

    @FXML
    private void gitHubHyperlinkOnAction(ActionEvent ignoredEvent) {
        showDocument(gitHubHyperlink);
    }

    @FXML
    private void websiteHyperlinkOnAction(ActionEvent ignoredEvent) {
        showDocument(websiteHyperlink);
    }

    private void showDocument(Hyperlink gitHubHyperlink) {
        applicationContext.getBean(HostServices.class).showDocument(gitHubHyperlink.getText());
    }
}
