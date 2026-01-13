package com.nexuscrm.app.view;

import com.nexuscrm.app.model.User;
import com.nexuscrm.app.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("") // Esto le dice a Vaadin que esta es la página principal
public class MainView extends VerticalLayout {

    // Inyectamos el servicio como parametro para poder usarlo mediante el patrón singleton
    public MainView(UserService userService) {

        // Creamos un título para la vista
        H1 titulo = new H1("¡Bienvenido a NexusCRM!");

        // Instanciamos un grid para poder contener los datos de los usuarios
        Grid<User> gridUser = new Grid<>(User.class,false);

        // Definimos la estructura del grid en base a los datos del usuario
        gridUser.addColumn(User::getName).setHeader("Name").setAutoWidth(true);
        gridUser.addColumn(User::getLastName).setHeader("Last Name");
        gridUser.addColumn(User::getEmail).setHeader("Email");
        gridUser.addColumn(User::getBirthday).setHeader("Birthday");

        // Pasar los datos reales
        gridUser.setItems(userService.findAll());

        //Btn de prueba
        Button btn = new Button("Probar Vaadin", e -> {
            Notification.show("Usuarios cargados: " + userService.findAll().size());
        });

        // Ahora debemos añadir todos los elementos creados a la vista
        add(titulo,gridUser,btn);
        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }
}