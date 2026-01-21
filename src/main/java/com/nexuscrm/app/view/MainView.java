package com.nexuscrm.app.view;

import com.nexuscrm.app.model.User;
import com.nexuscrm.app.service.UserService;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView(UserService userService) {
        // 1. Configuración del layout para centrar todo en pantalla completa
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 title = new H1("Nexus CRM");

        // 2. Usar el componente especializado LoginForm
        LoginForm loginForm = new LoginForm();
        loginForm.setForgotPasswordButtonVisible(false); // Ocultar si no tienes lógica de recuperación

        // 3. Lógica de Login
        loginForm.addLoginListener(event -> {
            User user = userService.findUserByName(event.getUsername());

            // NOTA: Aquí deberías usar BCrypt.checkpw() en el futuro
            if (user != null && user.getPassword().equals(event.getPassword())) {
                Notification.show("Bienvenido " + user.getName());
                getUI().ifPresent(ui -> ui.navigate("library"));
            } else {
                loginForm.setError(true); // Muestra el error visual propio del componente
            }
        });

        add(title, loginForm);
    }
}