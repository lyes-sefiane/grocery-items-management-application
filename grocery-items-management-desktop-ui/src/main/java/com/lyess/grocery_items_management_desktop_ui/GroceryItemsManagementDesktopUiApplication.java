package com.lyess.grocery_items_management_desktop_ui;

import com.lyess.grocery_items_management_desktop_ui.config.JavafxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroceryItemsManagementDesktopUiApplication {

	public static void main(String[] args) {
		Application.launch(JavafxApplication.class, args);
	}
}
