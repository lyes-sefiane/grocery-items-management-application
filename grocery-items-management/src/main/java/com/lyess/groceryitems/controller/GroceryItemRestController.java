package com.lyess.groceryitems.controller;

import com.lyess.groceryitems.domain.dto.GroceryItemRecord;
import com.lyess.groceryitems.domain.dto.GroceryItemResource;
import com.lyess.groceryitems.domain.entity.GroceryItem;
import com.lyess.groceryitems.service.IGroceryItemService;
import io.github.lyes_sefiane.exception_response.entities.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.controller
 * Class : GroceryItemRestController.java
 * User : Lyes Sefiane
 * Created : 2023-09-24 1:14 PM
 */
@RestController
@RequestMapping(value = "/v1/items", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(name = "Grocery Items", description = "Grocery Items Management REST Controller")
@ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "415", description = "UNSUPPORTED_MEDIA_TYPE", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))})
public class GroceryItemRestController {

    Logger logger = LoggerFactory.getLogger(GroceryItemRestController.class);

    private final IGroceryItemService groceryItemService;

    private final Converter<GroceryItemRecord, GroceryItem> customConverter;

    @Autowired
    public GroceryItemRestController(IGroceryItemService groceryItemService, Converter<GroceryItemRecord, GroceryItem> customConverter) {
        this.groceryItemService = groceryItemService;
        this.customConverter = customConverter;
    }

    /**
     * Find All Grocery Items In The Database.
     *
     * @return List of Grocery Item.
     * @see GroceryItemResource
     */
    @GetMapping
    @Operation(summary = "Find Grocery Items")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = GroceryItemResource.class)))
    public List<GroceryItemResource> findAllGroceryItems() {
        logger.info("Find All Grocery Items ....");
        return groceryItemService.findAllGroceryItems().stream().map(groceryItem -> new GroceryItemResource(groceryItem, addLink(groceryItem))).toList();
    }

    /**
     * Find a Specific Grocery Item In The Database.
     *
     * @param id : String
     * @return Grocery Item Resource Found In The Database or Throw an Exception.
     * @see GroceryItem
     * @see GroceryItemResource
     */
    @GetMapping("/{id}")
    @Operation(summary = "Find Grocery Item")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = GroceryItemResource.class)))
    public GroceryItemResource findGroceryItem(@PathVariable @NotBlank(message = "Grocery Item Should Not Be Blank !") String id) {
        logger.info("Find Grocery Items With The Following Id : {}", id);
        GroceryItem groceryItem = groceryItemService.findGroceryItem(id);
        return new GroceryItemResource(groceryItem, addLink(groceryItem));
    }

    /**
     * Save Grocery Item Into The Database.
     *
     * @param groceryItemRecord : Data Transfer Object.
     * @return Grocery Item Resource Saved In The Database
     * @see GroceryItemRecord
     * @see GroceryItemResource
     */
    @PostMapping
    @Operation(summary = "Register Grocery Item")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = GroceryItemResource.class)))
    public GroceryItemResource saveGroceryItem(@RequestBody @Valid GroceryItemRecord groceryItemRecord) {
        logger.info("Register The Following Grocery Item : {}", groceryItemRecord);
        GroceryItem groceryItem = groceryItemService.saveGroceryItem(customConverter.convert(groceryItemRecord));
        return new GroceryItemResource(groceryItem, addLink(groceryItem));
    }

    /**
     * Update An Existing Grocery Item In The Database.
     *
     * @param groceryItemRecord : Data Transfer Object
     * @param id                : Grocery Item Identifier
     * @return Updated Grocery Item Resource
     * @see GroceryItemResource
     * @see GroceryItemRecord
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update Grocery Item")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = GroceryItemResource.class)))
    public GroceryItemResource updateGroceryItem(@RequestBody @Valid GroceryItemRecord groceryItemRecord, //
                                                 @PathVariable @NotBlank(message = "Grocery Id should Not Be Blank!") String id) {
        logger.info("Update Grocery Item With The Following Id : {}, {}", id, groceryItemRecord);
        GroceryItem groceryItem = groceryItemService.updateGroceryItem(customConverter.convert(groceryItemRecord), id);
        return new GroceryItemResource(groceryItem, addLink(groceryItem));
    }

    /**
     * Delete A Specific Grocery Item In The Database.
     *
     * @param id : Grocery Item Identifier.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Grocery Item")
    @ApiResponse(responseCode = "204", description = "NO_CONTENT", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public void deleteGroceryItem(@PathVariable @NotBlank(message = "Grocery Id should Not Be Blank!") String id) {
        logger.info("Delete Grocery Item With The Following Id : {}", id);
        groceryItemService.deleteGroceryItem(id);
    }

    /**
     * Add HATEOAS Link with Self Relation
     *
     * @param groceryItem: Entity Response
     *
     * @return HATEOAS Link
     */
    private Link addLink(GroceryItem groceryItem) {
        return linkTo(GroceryItemRestController.class).slash(groceryItem.getId()).withSelfRel();
    }


}
