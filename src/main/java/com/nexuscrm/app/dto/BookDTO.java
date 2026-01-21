package com.nexuscrm.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private LocalDate publishedDate;
    private Integer pages;
    private String authorName; // De Author.name
    private String isbn;       // De TechDetail.isbn
    private String editorial;  // De TechDetail.editorial
}