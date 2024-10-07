package com.lyess.grocery_items_management_api_gateway.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lyes Sefiane
 * @email:  lyes.sefiane@gmail.com
 * @version 1.0
 * @since 2024-09-21
 */
@RestController
public class FallBackRestController {
    /**
     * Throw Exception Instead.
     */
    @GetMapping("/service-unavailable")
    ResponseEntity<String> serviceUnavailableFallback() {
        return new ResponseEntity<>("We are sorry! The service is currently unavailable. \nPlease try later",
                HttpStatusCode.valueOf(503));
    }

}
