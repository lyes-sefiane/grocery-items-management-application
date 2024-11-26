package io.github.lyes_sefiane.groceryitems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GroceryItemsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceryItemsManagementApplication.class, args);
    }

}
