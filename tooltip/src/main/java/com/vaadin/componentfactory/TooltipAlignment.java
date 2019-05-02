package com.vaadin.componentfactory;

/**
 * Helper enumeration to specify the alignment of the <code>Tooltip</code>.
 * <p>
 * The alignment determines the placement of the tooltip in the chosen position.
 * <p>
 * i.e.     alignment bottom    alignment top
 *          !!!!!!!!!!!!!
 *          !           !
 * ------   !           !       !!!!!!!!!!!!!
 * |Button| !           !       !           !
 * ------   !!!!!!!!!!!!!       !           !
 *                              !           !
 *                              !!!!!!!!!!!!!
 */
public enum TooltipAlignment {
    TOP("top"),
    RIGHT("right"),
    LEFT("left"),
    BOTTOM("bottom"),
    CENTER("center");

    private String align;

    TooltipAlignment(String align) {
        this.align = align;
    }

    public String getAlignmentText() {
        return align;
    }

    public static TooltipAlignment getAlignment(String text) {
        for (TooltipAlignment alignment : TooltipAlignment.values()) {
            if (alignment.getAlignmentText().equals(text)) {
                return alignment;
            }
        }
        return null;
    }
}