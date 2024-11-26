package io.github.lyes_sefiane.grocery_items_management_desktop_ui.config;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * <a href="https://github.com/ecovaci/javafx-spring-boot/tree/master">Reference</a>
 */
public class StageReadyEvent extends ApplicationEvent {

    public Stage getStage() {
        return (Stage) getSource();
    }

    public StageReadyEvent(Object source) {
        super(source);
    }
}
