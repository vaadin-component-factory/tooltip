package com.vaadin.flow.component.incubator;

/*
 * #%L
 * Vaadin IncubatorTooltip for Vaadin 10
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file license.html distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */


import java.util.Objects;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

/**
 * Server-side component for the <code>incubator-tooltip</code> element.
 * <p>
 * Note:
 * There are still some bugs in the implementation of incubator-tooltip.
 *
 * @author Vaadin Ltd
 */
@Tag("incubator-tooltip")
@HtmlImport("bower_components/incubator-tooltip/src/incubator-tooltip.html")
public class Tooltip extends Component implements HasComponents, HasStyle {

    private final String ATTACHED_COMPONENT_ID_PROPERTY = "for";
    private final String POSITION_PROPERTY = "position";
    private final String ALIGNMENT_PROPERTY = "alignment";
    private final String MANUAL_PROPERTY = "manual";
    private final String IS_ATTACHED_PROPERTY = "isAttached";

    /**
     * Default constructor.
     */
    public Tooltip() {
    }

    /**
     * Assigns the tooltip to a specific component.
     *
     * @param component the tooltip is attached to this component
     */
    public void attachToComponent(Component component) {
        Optional<String> componentId = component.getId();
        if (componentId.isPresent()) {
            attachToComponent(componentId.get());
        } else {
            throw new IllegalArgumentException("Tooltip: The component does not have an id.");
        }
    }

    /**
     * Assigns the tooltip to a component with an specific id.
     *
     * @param id the id of the component that we want to attach.
     */
    public void attachToComponent(String id) {
        Objects.requireNonNull(id);
        getElement().setProperty(ATTACHED_COMPONENT_ID_PROPERTY, id);
    }

    /**
     * Checks if the tooltip is attached to an element or not.
     *
     * @return <code>true</code> the tooltip is attached to an element
     * <code>false</code>, otherwise
     */
    public boolean isAttached() {
        return getElement().getProperty(IS_ATTACHED_PROPERTY, false);
    }


    /**
     * Sets the position of the tooltip.
     *
     * @param position "top","right","left" or "bottom"
     */
    public void setPosition(String position) {
        getElement().setProperty(POSITION_PROPERTY, position);
    }

    /**
     * Sets the position of the tooltip.
     *
     * @param position The position of the tooltip {@link Position}
     */
    public void setPosition(Position position) {
        getElement().setProperty(POSITION_PROPERTY, position.getPositionText());
    }

    /**
     * Gets the position of the tooltip.
     *
     * <p>
     * This property is not synchronized automatically from the client side, so
     * the returned value may not be the same as in client side.
     * </p>
     *
     * @return position "top","right","left" or "bottom"
     */
    public String getPositionText() {
        return getElement().getProperty(POSITION_PROPERTY);
    }

    /**
     * Gets the position of the tooltip.
     *
     * <p>
     * This property is not synchronized automatically from the client side, so
     * the returned value may not be the same as in client side.
     * </p>
     *
     * @return position The position of the tooltip {@link Position}
     **/
    public Position getPosition() {
        return Position.getPosition(getPositionText());
    }


    /**
     * Sets the alignment of the tooltip.
     *
     * @param alignment alignment "top","right","left" or "bottom"
     */
    public void setAlignment(String alignment) {
        getElement().setProperty(ALIGNMENT_PROPERTY, alignment);
    }

    /**
     * Sets the alignment of the tooltip.
     *
     * @param alignment The alignment of the tooltip {@link Alignment}
     */
    public void setAlignment(Alignment alignment) {
        getElement().setProperty(ALIGNMENT_PROPERTY, alignment.getAlignmentText());
    }

    /**
     * Gets the alignment of the tooltip.
     *
     * <p>
     * This property is not synchronized automatically from the client side, so
     * the returned value may not be the same as in client side.
     * </p>
     *
     * @return alignment "top","right","left" or "bottom"
     */
    public String getAlignmentText() {
        return getElement().getProperty(ALIGNMENT_PROPERTY);
    }

    /**
     * Gets the alignment of the tooltip.
     *
     * <p>
     * This property is not synchronized automatically from the client side, so
     * the returned value may not be the same as in client side.
     * </p>
     *
     * @return alignment The alignment of the tooltip {@link Alignment}
     **/
    public Alignment getAlignment() {
        return Alignment.getAlignment(getAlignmentText());
    }

    /**
     * Helper enumeration to specify the position of the <code>Tooltip</code>.
     */
    public enum Position {
        TOP("top"),
        RIGHT("right"),
        LEFT("left"),
        BOTTOM("bottom");

        private String pos;

        Position(String pos) {
            this.pos = pos;
        }

        public String getPositionText() {
            return pos;
        }

        public static Position getPosition(String text) {
            for (Position position : Position.values()) {
                if (position.getPositionText().equals(text)) {
                    return position;
                }
            }
            return null;
        }
    }

    /**
     * Helper enumeration to specify the alignment of the <code>Tooltip</code>.
     */
    public enum Alignment {
        TOP("top"),
        RIGHT("right"),
        LEFT("left"),
        BOTTOM("bottom");

        private String align;

        Alignment(String align) {
            this.align = align;
        }

        public String getAlignmentText() {
            return align;
        }

        public static Alignment getAlignment(String text) {
            for (Alignment alignment : Alignment.values()) {
                if (alignment.getAlignmentText().equals(text)) {
                    return alignment;
                }
            }
            return null;
        }
    }

}
