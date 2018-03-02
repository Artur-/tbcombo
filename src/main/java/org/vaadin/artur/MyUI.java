package org.vaadin.artur;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout vertLayout = new VerticalLayout();
        vertLayout.setSizeFull();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(i + "");
        }
        ComboBox<String> combo = new ComboBox<>("Test");
        combo.setItems(items);
        combo.setEmptySelectionAllowed(false);
        combo.setNewItemHandler(inputString -> {
            items.add(inputString);
            combo.setItems(items);
            combo.setSelectedItem(inputString);
        });
        combo.setSelectedItem(items.iterator().next());
        vertLayout.addComponent(combo);
        setContent(vertLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
