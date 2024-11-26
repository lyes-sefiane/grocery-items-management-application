package io.github.lyes_sefiane.grocery_items_management_desktop_ui.alert;

import javafx.scene.control.Alert;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-09-09
 */
public class AlertMessage {

    /**
     * Default
     */
    public AlertMessage() {
        //
    }

    public AlertMessage(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }
}