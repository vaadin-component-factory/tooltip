package com.vaadin.flow.component.incubator.vaadincom;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.incubator.Tooltip;
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

        add(tooltip,button);

        addCard("Tool tip basic example",button,tooltip);
    }
}
