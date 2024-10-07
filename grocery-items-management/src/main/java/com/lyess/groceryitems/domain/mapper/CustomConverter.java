package com.lyess.groceryitems.domain.mapper;

import com.lyess.groceryitems.domain.dto.GroceryItemRecord;
import com.lyess.groceryitems.domain.entity.GroceryItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.domain.mapper
 * Class : GroceryItemMapper.java
 * User : Lyes Sefiane
 * Created : 2023-09-27 12:46 PM
 */
@Component
public class CustomConverter implements Converter<GroceryItemRecord, GroceryItem> {

    public static final String DATE_FORMAT = "-yyyyMMdd-HHmmss";

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param groceryItemRecord the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public GroceryItem convert(GroceryItemRecord groceryItemRecord) {
        return new GroceryItem(UUID.randomUUID().toString().concat(new SimpleDateFormat(DATE_FORMAT).format(new java.util.Date())),//
                groceryItemRecord.name(),//
                groceryItemRecord.quantity(),//
                groceryItemRecord.category());
    }
}
