package com.lyess.grocery_items_management_desktop_ui.about;

import com.lyess.grocery_items_management_desktop_ui.url.UrlFxmlController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class AboutMeFxmlController extends UrlFxmlController {

    @Value("${website.url}")
    private String websiteUrl;

    public AboutMeFxmlController(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    protected String getUrl() {
        return websiteUrl;
    }

}
