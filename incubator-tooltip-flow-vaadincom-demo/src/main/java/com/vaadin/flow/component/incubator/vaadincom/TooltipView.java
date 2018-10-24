package com.vaadin.flow.component.incubator.vaadincom;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.incubator.Tooltip;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("tooltip")
public class TooltipView extends DemoView {


    @Override
    protected void initView() {
        createBasicExample();
        createDisabledTooltipExample();
        createOpenCloseTooltipExample();
        createOnClickTooltipExample();
    }

    private void createBasicExample() {

        Button button = new Button("Click me");
        Tooltip tooltip = new Tooltip();

        button.setId("myButton");
        tooltip.attachToComponent(button);

        tooltip.setPosition(Tooltip.Position.RIGHT);
        tooltip.setAlignment(Tooltip.Alignment.LEFT);

        tooltip.add(new H5("Hello"));
        tooltip.add(new Paragraph("This is an example of how to use it"));

        addCard("Tooltip basic example", button, tooltip);
    }

    private void createDisabledTooltipExample() {

        Button button = new Button("Click me");
        Tooltip tooltip = new Tooltip();

        button.setId("myButton");
        tooltip.attachToComponent(button);

        tooltip.setPosition(Tooltip.Position.RIGHT);
        tooltip.setAlignment(Tooltip.Alignment.LEFT);

        tooltip.add(new H5("Bye"));
        tooltip.add(new Paragraph("This is an example of how to use it"));

        button.addClickListener(event -> {
            tooltip.setEnabled(!tooltip.isEnabled());
        });

        tooltip.setEnabled(false);
        addCard("Tooltip disable/disable example", button, tooltip);
    }

    private void createOpenCloseTooltipExample() {

        Button button = new Button("Click me");
        Tooltip tooltip = new Tooltip();

        button.setId("myButton");
        tooltip.attachToComponent(button);

        tooltip.setPosition(Tooltip.Position.RIGHT);
        tooltip.setAlignment(Tooltip.Alignment.LEFT);

        tooltip.add(new H5("Bye"));
        tooltip.add(new Paragraph("This is an example of how to use it"));

        Button open = new Button("open", event -> {
            tooltip.open();
        });

        Button close = new Button("close", event -> {
            tooltip.close();
        });

        button.addClickListener(event -> {
            tooltip.setEnabled(!tooltip.isEnabled());
        });

        tooltip.setEnabled(false);
        addCard("Opening and closing a tooltip programmatically", button, tooltip, open, close);
    }

    private void createOnClickTooltipExample() {
        Button button = new Button("Click me");
        Tooltip tooltip = new Tooltip();

        button.setId("myButton");
        tooltip.attachToComponent(button);

        tooltip.setPosition(Tooltip.Position.RIGHT);
        tooltip.setAlignment(Tooltip.Alignment.LEFT);

        tooltip.add(new H5("Hello"));
        tooltip.add(new Paragraph("This is an example of how to use it"));

        tooltip.addClickListener(event -> {
            Notification notification = new Notification(
                    "You clicked on the avatar", 3000, Notification.Position.TOP_CENTER);
            notification.open();
        });

        addCard("Tooltip click listener example", button, tooltip);
    }
}
