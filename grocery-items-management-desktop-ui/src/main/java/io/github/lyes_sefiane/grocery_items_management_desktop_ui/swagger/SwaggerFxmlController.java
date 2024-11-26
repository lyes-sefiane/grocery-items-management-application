package io.github.lyes_sefiane.grocery_items_management_desktop_ui.swagger;

import io.github.lyes_sefiane.grocery_items_management_desktop_ui.url.UrlFxmlController;
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
public class SwaggerFxmlController extends UrlFxmlController {

    @Value("${swagger-ui.url}")
    private String swaggerUiUrl;

    public SwaggerFxmlController(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    protected String getUrl() {
        return swaggerUiUrl;
    }
}
