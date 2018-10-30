package com.vaadin.flow.component.incubator;

/**
 * Helper enumeration to specify the position of the <code>Tooltip</code>.
 * Position determines if the tooltip will be positioned on the top, bottom,
 * left or right of the attached component.
 */
public enum TooltipPosition {
    TOP("top"),
    RIGHT("right"),
    LEFT("left"),
    BOTTOM("bottom");

    private String pos;

    TooltipPosition(String pos) {
        this.pos = pos;
    }

    public String getPositionText() {
        return pos;
    }

    public static TooltipPosition getPosition(String text) {
        for (TooltipPosition position : TooltipPosition.values()) {
            if (position.getPositionText().equals(text)) {
                return position;
            }
        }
        return null;
    }
}