package com.lyess.grocery_items_management_desktop_ui.entities.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Table Cell Enum
 */
public enum IconEnum {

    EDIT("/images/edit-icon.png", 20, 20, false, false),
    DELETE("/images/delete-icon.png", 20, 20, false, false),
    HOME("/images/home-icon.png", 32, 32, false, false),
    ITEMS("/images/items-icon.png",  28, 28, false, false),
    SWAGGER("/images/swagger-icon.png", 32, 32, false, false),
    ZIPKIN("/images/zipkin-icon.png", 32, 32, false, false),
    GITHUB("/images/github-icon.png",32, 32, false, false),
    LICENSE("/images/license-icon.png", 32, 32, false, false),
    ABOUT_ME("/images/about-me-icon.png", 32, 32, false, false),
    UNSUPPORTED(StringUtils.EMPTY, 0.0, 0.0, false, false);

    private final String location;
    private final double width;
    private final double height;
    private final boolean preserveRatio;
    private final boolean smooth;

    IconEnum(String location, double width, double height, boolean preserveRatio, boolean smooth) {
        this.location = location;
        this.width = width;
        this.height = height;
        this.preserveRatio = preserveRatio;
        this.smooth = smooth;


    }

    public double getWidth() {
        return width;
    }

    public String getLocation() {
        return location;
    }

    public double getHeight() {
        return height;
    }

    public boolean isPreserveRatio() {
        return preserveRatio;
    }

    public boolean isSmooth() {
        return smooth;
    }
}
