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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.internal.nodefeature.ElementData;
import com.vaadin.flow.shared.Registration;

/**
 * Server-side component for the <code>incubator-tooltip</code> element.
 * Default tooltip's position and alignment are top and center respectively.
 *
 * @author Vaadin Ltd
 */
@Tag("incubator-tooltip")
@HtmlImport("bower_components/incubator-tooltip/src/incubator-tooltip.html")
public class Tooltip extends Component implements HasComponents, HasStyle {

    private final String ATTACHED_COMPONENT_ID_PROPERTY = "for";
    private final String POSITION_PROPERTY = "position";
    private final String ALIGNMENT_PROPERTY = "align";
    private final String HIDDEN_MSG_PROPERTY = "hidden";
    private final String MANUAL_PROPERTY = "manual";

    /**
     * Default constructor.
     */
    public Tooltip() {
        getElement().getStyle().set("margin","0px");
    }

    /**
     * Creates the tooltip attaching it to the component.
     *
     * @param component the tooltip is attached to this component
     */
    public Tooltip(Component component){
        this();
        attachToComponent(component);
    }

    /**
     * Creates the tooltip attaching it to the component and sets its position.
     *
     * @param component the tooltip is attached to this component
     * @param position "top","right","left" or "bottom"
     */
    public Tooltip(Component component, String position){
       this(component);
       setPosition(position);
    }

    /**
     * Creates the tooltip attaching it to the component and sets its position.
     *
     * @param component the tooltip is attached to this component
     * @param position The position of the tooltip {@link Position}
     */
    public Tooltip(Component component, Position position){
        this(component,position.getPositionText());
    }

    /**
     * Creates the tooltip attaching it to the component. It also sets its position
     * and its alignment.
     *
     * @param component the tooltip is attached to this component
     * @param position "top","right","left" or "bottom"
     * @param alignment "top","right","left","bottom" or "center"
     */
    public Tooltip(Component component, String position, String alignment){
        this(component,position);
        setAlignment(alignment);
    }

    /**
     * Creates the tooltip attaching it to the component. It also sets its position
     * and its alignment.
     *
     * @param component the tooltip is attached to this component
     * @param position The position of the tooltip {@link Position}
     * @param alignment The alignment of the tooltip {@link Alignment}
     */
    public Tooltip(Component component, Position position, Alignment alignment){
        this(component,position.getPositionText(),alignment.getAlignmentText());
    }

    /**
     * Assigns the tooltip to a specific component.
     *
     * @param component the tooltip is attached to this component
     */
    public void attachToComponent(Component component) {
        Objects.requireNonNull(component);

        getElement().getNode().runWhenAttached(ui ->
                ui.getPage().executeJavaScript("$0.targetElement = $1;",
                        getElement(), component.getElement()
                ));
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
     * Opens the content of the tooltip.
     */
    public void open() {
        getElement().setProperty(HIDDEN_MSG_PROPERTY, false);
    }

    /**
     * Hides the content of the tooltip.
     */
    public void close() {
        getElement().setProperty(HIDDEN_MSG_PROPERTY, true);
    }

    /**
     * Handle component enable state when the enabled state changes.
     * <p>
     * By default this sets or removes the 'disabled' attribute from the
     * element. This can be overridden to have custom handling.
     *
     * @param enabled
     *            the new enabled state of the component
     */
    @Override
    public void onEnabledStateChanged(boolean enabled) {
        // If the node has feature ElementData, then we know that the state
        // provider accepts attributes
        if (getElement().getNode().hasFeature(ElementData.class)) {
            getElement().callFunction("hide"); // needed to close tooltip
            getElement().setAttribute(MANUAL_PROPERTY, !enabled);
        }
    }

    /**
     * Checks the manual mode of the tooltip.
     *
     * @return manualMode <code>true</code> the tooltip is controlled programmatically
     *                  <code>false</code>, it is controlled automatically
     */
    public boolean isManualMode(){
        return getElement().getProperty(MANUAL_PROPERTY,false);
    }

    /**
     * Sets the manual mode of the tooltip.
     * <p>
     * manualMode requires to open or close the tooltip manually.
     *
     * @param manualMode <code>true</code> the tooltip is controlled programmatically
     *                  <code>false</code>, it is controlled automatically
     */
    public void setManualMode(boolean manualMode){
        getElement().setProperty(MANUAL_PROPERTY,manualMode);
    }

    /**
     * Adds a listener for {@code ClickEvent}.
     *
     * @param listener the listener
     * @return a {@link Registration} for removing the event listener
     */
    public Registration addClickListener(ComponentEventListener<ClickEvent> listener) {
        return addListener(ClickEvent.class, listener);
    }

    /**
     * Click event on the component.
     */
    @DomEvent("click")
    public static class ClickEvent extends ComponentEvent<Tooltip> {

        public ClickEvent(Tooltip source, boolean fromClient) {
            super(source, fromClient);
        }
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
     * @param alignment alignment "top","right","left","bottom" or "center"
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
     * @return alignment "top","right","left","bottom" or center
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
     * Position determines if the tooltip will be positioned on the top, bottom,
     * left or right of the attached component.
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
    public enum Alignment {
        TOP("top"),
        RIGHT("right"),
        LEFT("left"),
        BOTTOM("bottom"),
        CENTER("center");

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
