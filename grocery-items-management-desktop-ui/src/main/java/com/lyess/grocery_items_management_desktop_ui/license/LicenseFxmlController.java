package com.lyess.grocery_items_management_desktop_ui.license;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.web.WebView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class LicenseFxmlController {

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private WebView webView;

    @Value("${creativecommons.url}")
    private String creativeCommonsUrl;

    private final ApplicationContext applicationContext;

    @Autowired
    public LicenseFxmlController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize() {
        hyperlink.setText(creativeCommonsUrl);
        Platform.runLater(() -> webView.getEngine().load(creativeCommonsUrl));
    }

    @FXML
    private void onClickAction(ActionEvent ignoredEvent) {
        applicationContext.getBean(HostServices.class).showDocument(creativeCommonsUrl);
    }

}
