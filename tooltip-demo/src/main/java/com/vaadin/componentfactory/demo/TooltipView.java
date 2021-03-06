package com.vaadin.componentfactory.demo;

import com.vaadin.componentfactory.Tooltip;
import com.vaadin.componentfactory.TooltipAlignment;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.componentfactory.TooltipPosition;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("tooltip")
public class TooltipView extends DemoView {


    @Override
    protected void initView() {
        createPositionAlignmentExamples();
        createBasicExample();
        createDisabledTooltipExample();
        createOpenCloseTooltipExample();
        createOnClickTooltipExample();
        createScrollableContentExamples();
        createTooltipInDialog();
    }

    private void createBasicExample() {

        Button button = new Button("Hover on me");
        Tooltip tooltip = new Tooltip();

        tooltip.attachToComponent(button);

        tooltip.setPosition(TooltipPosition.RIGHT);
        tooltip.setAlignment(TooltipAlignment.LEFT);

        tooltip.add(new H5("Hello"));
        tooltip.add(new Paragraph("This is an example of how to use it"));

        addCard("Tooltip basic example", button, tooltip);
    }

    private void createDisabledTooltipExample() {

        Button button = new Button("Enable/Disable tooltip");
        Tooltip tooltip = new Tooltip();

        tooltip.attachToComponent(button);

        tooltip.setPosition(TooltipPosition.RIGHT);
        tooltip.setAlignment(TooltipAlignment.LEFT);

        tooltip.add(new H5("I am enable"));
        tooltip.add(new Paragraph("I am enable"));

        button.addClickListener(event -> {
            tooltip.setEnabled(!tooltip.isEnabled());
        });

        addCard("Tooltip disable/disable example", button, tooltip);
    }

    private void createOpenCloseTooltipExample() {

        Button button = new Button("Button");
        Tooltip tooltip = new Tooltip();
        tooltip.setThemeName("light");
//        tooltip.getStyle().set("--lumo-base-color", "var(--lumo-contrast-20pct");

        tooltip.attachToComponent(button);

        tooltip.setPosition(TooltipPosition.RIGHT);
        tooltip.setAlignment(TooltipAlignment.LEFT);

        tooltip.add(new H5("Manual tooltip"));
        tooltip.add(new Paragraph("The tooltip is controlled programmatically"));
        tooltip.setCloseButtonVisible(true);
        
        Button open = new Button("Open tooltip", event -> {
            tooltip.open();
        });

        Button close = new Button("Close tooltip", event -> {
            tooltip.close();
        });

        button.addClickListener(event -> {
            tooltip.setEnabled(!tooltip.isEnabled());
        });

        tooltip.setManualMode(true);
        addCard("Opening and closing a tooltip programmatically", button, tooltip, new Div(open, close));
    }

    private void createOnClickTooltipExample() {
        Button button = new Button("Hover on me");
        Tooltip tooltip = new Tooltip();

        tooltip.attachToComponent(button);

        tooltip.setPosition(TooltipPosition.RIGHT);
        tooltip.setAlignment(TooltipAlignment.LEFT);

        tooltip.add(new H5("Click on me"));
        tooltip.add(new Paragraph("Click on the tooltip to see the notification"));

        tooltip.addClickListener(event -> {
            Notification notification = new Notification(
                    "You clicked on the tooltip", 3000, Notification.Position.TOP_CENTER);
            notification.open();
        });

        addCard("Tooltip click listener example", button, tooltip);
    }

    private void createPositionAlignmentExamples() {
        VerticalLayout verticalLayout = new VerticalLayout();
        for (TooltipPosition position : TooltipPosition.values()) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            for (TooltipAlignment alignment : TooltipAlignment.values()) {
                Button button = new Button(position.getPositionText()
                    + " " + alignment.getAlignmentText());
                Tooltip tooltip = new Tooltip(button, position, alignment);

                tooltip.add(new Paragraph("Position: " + position.getPositionText()));
                tooltip.add(new Paragraph("Alignment: " + alignment.getAlignmentText()));
                horizontalLayout.add(button, tooltip);
            }
            verticalLayout.add(horizontalLayout);
        }

        addCard("All Tooltip's Positions and Alignments", verticalLayout);
    }

    private void createScrollableContentExamples() {
        VerticalLayout verticalLayout = new VerticalLayout();
        VerticalLayout container = new VerticalLayout();
        Scroller scroller = new Scroller(container);
        for (TooltipPosition position : TooltipPosition.values()) {
            scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);

            scroller.setHeight("320px");
            for (TooltipAlignment alignment : TooltipAlignment.values()) {
                Button button = new Button(position.getPositionText()
                        + " " + alignment.getAlignmentText());
                Tooltip tooltip = new Tooltip(button, position, alignment);

                tooltip.add(new Paragraph("Position: " + position.getPositionText()));
                tooltip.add(new Paragraph("Alignment: " + alignment.getAlignmentText()));
                container.add(button, tooltip);
            }
        }
        verticalLayout.add(scroller);

        addCard("Tooltip in scrollable content", verticalLayout);
    }


    private void createTooltipInDialog() {
        VerticalLayout dialogLayout = new VerticalLayout();
        for (TooltipPosition position : TooltipPosition.values()) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            for (TooltipAlignment alignment : TooltipAlignment.values()) {
                Button button = new Button(position.getPositionText()
                    + " " + alignment.getAlignmentText());
                Tooltip tooltip = new Tooltip(button, position, alignment);

                tooltip.add(new Paragraph("Position: " + position.getPositionText()));
                tooltip.add(new Paragraph("Alignment: " + alignment.getAlignmentText()));
                horizontalLayout.add(button, tooltip);
            }
            dialogLayout.add(horizontalLayout);
        }
        VerticalLayout container = new VerticalLayout();
        Scroller scroller = new Scroller(container);
        // set the scroller has the parent container for tooltip --> the tooltip will follow be kept inside this container
        scroller.getStyle().set("position", "relative");
        for (TooltipPosition position : TooltipPosition.values()) {
            scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);

            scroller.setHeight("320px");
            scroller.setWidthFull();
            for (TooltipAlignment alignment : TooltipAlignment.values()) {
                Button button = new Button(position.getPositionText()
                    + " " + alignment.getAlignmentText());
                Tooltip tooltip = new Tooltip(button, position, alignment);

                tooltip.add(new Paragraph("Position: " + position.getPositionText()));
                tooltip.add(new Paragraph("Alignment: " + alignment.getAlignmentText()));
                container.add(button, tooltip);
            }
        }
        dialogLayout.add(scroller);

        Button button = new Button("Button");
        Tooltip tooltip = new Tooltip();
        tooltip.setThemeName("light");

        tooltip.attachToComponent(button);

        tooltip.setPosition(TooltipPosition.RIGHT);
        tooltip.setAlignment(TooltipAlignment.LEFT);

        tooltip.add(new H5("Manual tooltip"));
        tooltip.add(new Paragraph("The tooltip is controlled programmatically"));
        tooltip.setCloseButtonVisible(true);

        Button open = new Button("Open tooltip", event -> {
            tooltip.open();
        });

        Button close = new Button("Close tooltip", event -> {
            tooltip.close();
        });

        button.addClickListener(event -> {
            tooltip.setEnabled(!tooltip.isEnabled());
        });

        tooltip.setManualMode(true);
        dialogLayout.add(button, open, close, tooltip);
        Dialog dialog = new Dialog();
        dialog.add(dialogLayout);
        dialogLayout.add(new Button("Close Dialog", e-> dialog.close()));
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new Button("Open Dialog", e-> dialog.open()));
        addCard("All Tooltip's Positions and Alignments in a Dialog", verticalLayout);
    }
}
