package sourceryacademy.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sourceryacademy.library.dtos.BookDTO;
import sourceryacademy.library.exceptions.BookNotFoundException;
import sourceryacademy.library.services.realizations.BookService;

import java.util.List;

//I calculate avg only when it is necessary, better approach would be to create separate table to contain data about avg rate for each book

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/by-author")
    public List<BookDTO> getBooksByAuthor(
            @RequestParam String author,
            @RequestParam(defaultValue = "0") int page) {
        return bookService.getBookDtosByAuthor(author, page);
    }

    @GetMapping("/books/sorted-by-rating")
    public List<BookDTO> getBooksSortedByRating(
            @RequestParam(defaultValue = "0") int page) {
        return bookService.getBookDtosPagedAndSortedByRates(page);
    }

    @GetMapping("/books/by-title-author")
    public BookDTO getBookByTitleAndAuthor(
            @RequestParam String title,
            @RequestParam String author) {
        return bookService.getBookByTitleAndAuthor(title, author);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book Not Found")
    @ExceptionHandler(BookNotFoundException.class)
    public void handleBookNotFound() {
    }

}
