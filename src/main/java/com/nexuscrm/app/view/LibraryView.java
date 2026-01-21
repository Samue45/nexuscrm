package com.nexuscrm.app.view;

import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.service.BookService;
import com.nexuscrm.app.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll; // Importante para seguridad

@Route("library")
@PermitAll
public class LibraryView extends VerticalLayout {

    public LibraryView(UserService userService) {

        H1 title = new H1("Mis Libros"); // Cambiamos el título para reflejar el contexto

        Grid<Book> grid = new Grid<>(Book.class, false); // false para configurar columnas manualmente

        // Configuramos columnas manualmente para controlar qué se muestra (Author es objeto)
        grid.addColumn(Book::getTitle).setHeader("Título");
        grid.addColumn(book -> {
            if (book.getAuthor() != null) {
                return book.getAuthor().getName();
            }
            return "Autor desconocido";
        }).setHeader("Autor");// Asumiendo que Author tiene getName()
        grid.addColumn(Book::getPublishedDate).setHeader("Fecha Publicación");
        grid.addColumn(Book::getPages).setHeader("Páginas");

        // --- EL CAMBIO CLAVE ---
        // En lugar de findAll(), llamamos al método filtrado
        // Dentro del constructor de LibraryView
        grid.setItems(userService.findBooksForCurrentUser());

        add(title, grid);
        setSizeFull();
    }
}