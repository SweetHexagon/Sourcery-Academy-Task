package sourceryacademy.library.controllers.unit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sourceryacademy.library.configuration.SecurityConfig;
import sourceryacademy.library.controllers.BookController;
import sourceryacademy.library.dtos.BookDTO;
import sourceryacademy.library.services.realizations.BookService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Import(SecurityConfig.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetBooksByAuthor() throws Exception {
        List<BookDTO> books = Arrays.asList(new BookDTO("Book1", "Author1", 2021, 8.0));

        Mockito.when(bookService.getBookDtosByAuthor(anyString(), anyInt())).thenReturn(books);

        mockMvc.perform(get("/books/by-author")
                        .param("author", "Author1")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"))
                .andExpect(jsonPath("$[0].author").value("Author1"))
                .andExpect(jsonPath("$[0].avgRating").value(8.0));
    }

    @Test
    public void testGetBookByTitleAndAuthor() throws Exception {
        BookDTO book = new BookDTO("Book1", "Author1", 2021, 8.0);
        Mockito.when(bookService.getBookByTitleAndAuthor(anyString(), anyString())).thenReturn(book);

        mockMvc.perform(get("/books/by-title-author")
                        .param("title", "Book1")
                        .param("author", "Author1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book1"))
                .andExpect(jsonPath("$.author").value("Author1"))
                .andExpect(jsonPath("$.avgRating").value(8.0));
    }

    @Test
    public void testBookNotFound() throws Exception {
        Mockito.when(bookService.getBookByTitleAndAuthor(anyString(), anyString()))
                .thenThrow(new sourceryacademy.library.exceptions.BookNotFoundException("Book not found"));

        mockMvc.perform(get("/books/by-title-author")
                        .param("title", "NonExistentBook")
                        .param("author", "NonExistentAuthor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetBooksSortedByRating() throws Exception {
        List<BookDTO> books = Arrays.asList(
                new BookDTO("Book1", "Author1", 2021, 9.0),
                new BookDTO("Book2", "Author2", 2020, 8.5)
        );

        Mockito.when(bookService.getBookDtosPagedAndSortedByRates(anyInt())).thenReturn(books);

        mockMvc.perform(get("/books/sorted-by-rating")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"))
                .andExpect(jsonPath("$[0].author").value("Author1"))
                .andExpect(jsonPath("$[0].avgRating").value(9.0))
                .andExpect(jsonPath("$[1].title").value("Book2"))
                .andExpect(jsonPath("$[1].author").value("Author2"))
                .andExpect(jsonPath("$[1].avgRating").value(8.5));
    }
}
