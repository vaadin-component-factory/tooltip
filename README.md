# Component Factory Tooltip for Vaadin 10+

This is server-side component of [&lt;vcf-tooltip&gt;](https://github.com/vaadin-component-factory/vcf-password-strength) Web Component.
Tooltip is a component that can be used in conjunction with any component to display contextual information.
When the attached component is hovered, the tooltip displays the contextual information.

[Live Demo ↗](https://incubator.app.fi/tooltip-demo/tooltip)

[<img src="https://raw.githubusercontent.com/vaadin/incubator-tooltip/master/screenshot.png" width="300" alt="Screenshot of vcf-tooltip">](https://vaadin.com/components/vcf-tooltip)


## Usage

A simple use of the tooltip component would be the following: create `Tooltip` and add some elements inside, then attach it to target component 
by calling `attachToComponent` method on tooltip object.

```java
Button button = new Button("Click me");
Tooltip tooltip = new Tooltip();

tooltip.attachToComponent(button);

tooltip.setPosition(TooltipPosition.RIGHT);
tooltip.setAlignment(TooltipAlignment.LEFT);

tooltip.add(new H5("Hello"));
tooltip.add(new Paragraph("This is an example of how to use it"));
```

It is possible to open and close the tooltip manually:

```java
Button open = new Button("Open tooltip", event -> {
    tooltip.open();
});

Button close = new Button("Close tooltip", event -> {
    tooltip.close();
});

button.addClickListener(event -> {
   tooltip.setEnabled(!tooltip.isEnabled());
});
```

Click listeners can be added to the tooltip:
```java
tooltip.addClickListener(event -> {
    Notification notification = new Notification(
            "You clicked on the avatar", 3000, Notification.Position.TOP_CENTER);
    notification.open();
});
```

## Setting up for development:
Clone the project in GitHub (or fork it if you plan on contributing)

```
git clone git@github.com/vaadin/vcf-tooltip-flow.git
```

in the root directory. `-DskipITs` will skip the integration tests, which require a TestBench license. If you want to run all tests as part of the build, run

```mvn install```


## Demo
The Demo can be run going to the project vcf-tooltip-flow-vaadincom-demo and executing the maven goal:

```mvn jetty:run```

# Vaadin Prime
This component is available in of Vaadin Prime subscription. It is still open source, but you need to have a valid CVAL license in order to use it. Read more at: https://vaadin.com/pricing


# License & Author
This Add-on is distributed under [Commercial Vaadin Add-on License version 3](http://vaadin.com/license/cval-3) (CVALv3). For license terms, see LICENSE.txt.

Incubator Tooltip is written by Vaadin Ltd.
