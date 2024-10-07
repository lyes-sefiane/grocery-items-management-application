package com.lyess.grocery_items_management_desktop_ui.entities.enums;

/**
 * Table Cell Enum
 */
public enum TableCellEnum {

    EDIT("/images/edit-icon.png", 20, 20, false, false),
    DELETE("/images/delete-icon.png", 20, 20, false, false);

    private final String icon;
    private final double width;
    private final double height;
    private final boolean preserveRatio;
    private final boolean smooth;

    TableCellEnum(String icon, double width, double height, boolean preserveRatio, boolean smooth) {
        this.icon = icon;
        this.width = width;
        this.height = height;
        this.preserveRatio = preserveRatio;
        this.smooth = smooth;


    }

    public double getWidth() {
        return width;
    }

    public String getIcon() {
        return icon;
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
