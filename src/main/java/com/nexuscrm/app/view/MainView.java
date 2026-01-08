package com.nexuscrm.app.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("") // Esto le dice a Vaadin que esta es la página principal
public class MainView extends VerticalLayout {

    public MainView() {
        // Añadimos un título
        H1 titulo = new H1("¡Bienvenido a NexusCRM!");

        // Añadimos un botón de prueba
        Button boton = new Button("Haz clic aquí", e -> {
            Notification.show("¡Vaadin funciona correctamente!");
        });

        // Agregamos los componentes al layout
        add(titulo, boton);

        // Alineamos todo al centro para que se vea bien
        setAlignItems(Alignment.CENTER);
    }
}