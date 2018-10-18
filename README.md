# Incubator Tooltip for Flow

# incubator-tooltip;

[Live Demo ↗](https://incubator.app.fi/incubator-tooltip-demo/)


[&lt;incubator-tooltip&gt;](https://vaadin.com/directory/component/vaadinincubator-tooltip) is a Web Component providing an easy way to display tooltips on any html element.


# What does the component do?

It allows to attach tooltips to elements.
# How is it used?

A simple use of the tooltip component would be the following.
```java
Button button = new Button("Click me");
Tooltip tooltip = new Tooltip();

button.setId("myButton");
tooltip.attachToComponent(button);

tooltip.setPosition(Tooltip.Position.RIGHT);
tooltip.setAlignment(Tooltip.Alignment.LEFT);

tooltip.add(new H5("Hello"));
tooltip.add(new Paragraph("This is an example of how to use it"));

add(tooltip,button);
```



# How to run the demo?

The Demo can be run going to the project incubator-tooltip-flow-vaadincom-demo and executing the maven goal:

```mvn jetty:run```


## License & Author

This Add-on is distributed under [Commercial Vaadin Add-on License version 3](http://vaadin.com/license/cval-3) (CVALv3). For license terms, see LICENSE.txt.

Incubator Tooltip is written by Vaadin Ltd.


## Setting up for development:

Clone the project in GitHub (or fork it if you plan on contributing)

```
git clone git@github.com/vaadin/incubator-tooltip-flow.git
```

To build and install the project into the local repository run 

```mvn install -DskipITs```

in the root directory. `-DskipITs` will skip the integration tests, which require a TestBench license. If you want to run all tests as part of the build, run

```mvn install```