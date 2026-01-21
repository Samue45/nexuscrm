package com.nexuscrm.app.view;

import com.nexuscrm.app.dto.BookDTO;
import com.nexuscrm.app.service.BookService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route("library")
@PermitAll
public class LibraryView extends VerticalLayout {

    // Usamos BookService que es el que ahora tiene el método de DTOs
    public LibraryView(BookService bookService) {

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 title = new H1("Mi Biblioteca Personal");

        // CAMBIO 1: El Grid ahora es de tipo BookDTO
        Grid<BookDTO> grid = new Grid<>(BookDTO.class, false);

        // CAMBIO 2: Las columnas leen directamente del DTO (campos planos)
        grid.addColumn(BookDTO::getTitle)
                .setHeader("Título")
                .setSortable(true)
                .setFlexGrow(2); // Le damos más espacio al título

        grid.addColumn(BookDTO::getAuthorName)
                .setHeader("Autor")
                .setSortable(true);

        grid.addColumn(BookDTO::getPublishedDate)
                .setHeader("Fecha Publicación");

        grid.addColumn(BookDTO::getIsbn)
                .setHeader("ISBN");

        grid.addColumn(BookDTO::getPages)
                .setHeader("Páginas");

        // CAMBIO 3: Llamamos al método que devuelve DTOs
        // Este método ya maneja la transacción y el mapeo internamente
        grid.setItems(bookService.findBooksDTOForCurrentUser());

        add(title, grid);

        // Estilo extra para que el grid ocupe el espacio disponible
        grid.setSizeFull();
        expand(grid);
    }
}