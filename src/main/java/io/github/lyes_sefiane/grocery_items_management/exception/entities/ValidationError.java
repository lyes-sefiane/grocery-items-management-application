package io.github.lyes_sefiane.grocery_items_management.exception.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Project Name : grocery-items-management-application
 * Package : com.lyess.groceryitems.exception.entities
 * Class : ValidationError.java
 * User : Lyes Sefiane
 * Created : 2023-09-29 11:31 AM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ValidationError(String object, String field, Object rejectedValue,
                              String message) implements ErrorResponse {

    public static final class Builder {
        String object;
        String field;
        Object rejectedValue;
        String message;

        public Builder object(String object) {
            this.object = object;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder rejectedValue(Object rejectedValue) {
            this.rejectedValue = rejectedValue;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ValidationError build() {
            return new ValidationError(object, field, rejectedValue, message);
        }
    }
}
