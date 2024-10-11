package com.lyess.grocery_items_management_desktop_ui.zipkin;

import com.lyess.grocery_items_management_desktop_ui.url.UrlFxmlController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-10-10
 */
@Component
public class ZipkinFxmlController extends UrlFxmlController {

    @Value("${zipkin.url}")
    private String zipkinUrl;

    protected ZipkinFxmlController(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    protected String getUrl() {
        return zipkinUrl;
    }
}