package com.nexuscrm.app.view;

import com.nexuscrm.app.model.Book;
import com.nexuscrm.app.service.BookService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("library") // URL: /library
public class LibraryView extends VerticalLayout {

    public LibraryView(BookService bookService) {

        H1 title = new H1("Biblioteca de Libros");

        Grid<Book> grid = new Grid<>(Book.class);
        grid.setItems(bookService.findAll());
        grid.setColumns("id", "title", "author", "publishedDate"); // Ajusta según tu entidad

        add(title, grid);
        setSizeFull();
    }
}
