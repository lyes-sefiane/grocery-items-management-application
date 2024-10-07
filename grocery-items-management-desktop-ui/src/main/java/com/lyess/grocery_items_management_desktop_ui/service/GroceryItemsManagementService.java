package com.lyess.grocery_items_management_desktop_ui.service;

import com.lyess.grocery_items_management_common.domain.GroceryItemResource;
import com.lyess.grocery_items_management_common.domain.dto.GroceryItemRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-09-11
 */
@Service
public class GroceryItemsManagementService implements IGroceryItemsManagementService {

    private static final String ID = "/{id}";

    private final Logger logger = LoggerFactory.getLogger(GroceryItemsManagementService.class);

    private final WebClient webClient;

    private final String baseUrl;

    public GroceryItemsManagementService(@Value("${base.url}") String baseUrl) {
        logger.info("In GroceryItemsManagementService");
        this.baseUrl = baseUrl;
        this.webClient = WebClient.create(baseUrl);
    }

    /**
     * Insert One Grocery Item to the DB
     *
     * @param groceryItemRecord: New Item to Insert into the DB
     * @return Mono of a Grocery Item
     */
    @Override
    public Mono<GroceryItemResource> insertOne(GroceryItemRecord groceryItemRecord) {
        logger.info("Insert One Item {}:", groceryItemRecord);
        return webClient
                .post()
                .uri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(groceryItemRecord.toString())
                .retrieve()
                .bodyToMono(GroceryItemResource.class)
                .log();
    }

    /**
     * Find All Grocery Items
     *
     * @return Flux of Grocery Item Resources
     */
    @Override
    public Flux<GroceryItemResource> findAll() {
        return  webClient//
                .get()//
                .uri(baseUrl)//
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)//
                .retrieve()//
                .bodyToFlux(GroceryItemResource.class)
                .log();
    }

    /**
     * Find By Id a Grocery Item
     *
     * @param id : Unique Identifier
     *
     * @return GroceryItemResource: Found Item
     */
    @Override
    public Mono<GroceryItemResource> findOneBy(String id) {
        logger.info("Find One Item By {} :", id);
        return webClient//
                .get()//
                .uri(baseUrl + ID, id)//
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)//
                .retrieve()//
                .bodyToMono(GroceryItemResource.class)//
                .log();
    }

    /**
     * Find and Update One Grocery Item
     *
     * @param groceryItemRecord: New Item to Put in the DB
     * @param id: Unique Identifier
     *
     * @return GroceryItemResource: Found Item
     */
    @Override
    public Mono<GroceryItemResource> findOneAndUpdate(String id, GroceryItemRecord groceryItemRecord) {
        logger.info("Find And Update {}, {}", id, groceryItemRecord);
        return webClient
                .put()//
                .uri(baseUrl + ID, id)//
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)//
                .bodyValue(groceryItemRecord.toString())//
                .retrieve()//
                .bodyToMono(GroceryItemResource.class)//
                .log();
    }

    /**
     * Find And Delete One Grocery Item
     *
     * @param id: Unique Identifier
     *
     * @return Mono of Void
     */
    @Override
    public Mono<Void> findOneAndDelete(String id) {
        return webClient
                .delete()
                .uri(baseUrl + ID, id)//
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)//
                .retrieve()
                .bodyToMono(Void.class)
                .log();
    }

}
