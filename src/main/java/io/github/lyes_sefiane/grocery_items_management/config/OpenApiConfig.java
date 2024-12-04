package io.github.lyes_sefiane.grocery_items_management.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenApi(@Value("${application.name}") String appTitle,
                                 @Value("${application.description}") String appDescription,
                                 @Value("${application.version}") String appVersion) {

        return new OpenAPI()
                .info(new Info()//
                        .title(appTitle)//
                        .description(appDescription)//
                        .version(appVersion)//
                        .license(new License()//
                                .name("CC-BY-NC-ND-4.0")//
                                .url("https://creativecommons.org/licenses/by-nc-nd/4.0/"))
                        .contact(new Contact()//
                                .name("Lyes Sefiane")//
                                .url("https://lyes-sefiane.web.app")//
                                .email("lyes.sefiane@gmail.com")))
                .externalDocs(new ExternalDocumentation()//
                        .description("GitHub Wiki")//
                        .url("https://github.com/lyes-sefiane/grocery-items-management-application/wiki"));

    }

}
