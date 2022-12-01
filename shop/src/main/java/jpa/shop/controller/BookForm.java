package jpa.shop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;
    private String name;
    private Long price;
    private Long stockQuantity;
    private String author;
    private String isbn;
}
