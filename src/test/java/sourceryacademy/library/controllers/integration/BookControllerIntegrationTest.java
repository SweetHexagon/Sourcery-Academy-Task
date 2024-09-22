package sourceryacademy.library.controllers.integration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import sourceryacademy.library.data.Book;
import sourceryacademy.library.data.Rate;
import sourceryacademy.library.data.User;
import sourceryacademy.library.repositories.BookRepository;
import sourceryacademy.library.repositories.RateRepository;
import sourceryacademy.library.repositories.UserRepository;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        rateRepository.deleteAll();
        bookRepository.deleteAll();
        userRepository.deleteAll();

        Book book1 = new Book();
        book1.setTitle("Book1");
        book1.setAuthor("Author1");
        book1.setPublishYear(2021);

        Book book2 = new Book();
        book2.setTitle("Book2");
        book2.setAuthor("Author2");
        book2.setPublishYear(2020);

        bookRepository.saveAll(Arrays.asList(book1, book2));

        User user1 = new User();
        user1.setUsername("User1");

        User user2 = new User();
        user2.setUsername("User2");

        User user3 = new User();
        user3.setUsername("User3");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        Rate rate1 = new Rate();
        rate1.setUser(user1);
        rate1.setBook(book1);
        rate1.setRating(5);

        Rate rate2 = new Rate();
        rate2.setUser(user2);
        rate2.setBook(book1);
        rate2.setRating(3);

        Rate rate3 = new Rate();
        rate3.setUser(user3);
        rate3.setBook(book1);
        rate3.setRating(4);

        Rate rate4 = new Rate();
        rate4.setUser(user1);
        rate4.setBook(book2);
        rate4.setRating(4);

        Rate rate5 = new Rate();
        rate5.setUser(user2);
        rate5.setBook(book2);
        rate5.setRating(5);

        rateRepository.saveAll(Arrays.asList(rate1, rate2, rate3, rate4, rate5));
    }

    @Test
    public void testGetBooksSortedByRating() throws Exception {
        mockMvc.perform(get("/books/sorted-by-rating")
                        .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book2"))
                .andExpect(jsonPath("$[0].avgRating").value(4.5))
                .andExpect(jsonPath("$[1].title").value("Book1"))
                .andExpect(jsonPath("$[1].avgRating").value(4.0));
    }

    //I calculate avg only when it is necessary, better approach would be to create separate table to contain data about avg rate for each book
    @Test
    public void testGetBooksByAuthor() throws Exception {
        mockMvc.perform(get("/books/by-author")
                        .param("author", "Author1")
                        .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book1"))
                .andExpect(jsonPath("$[0].author").value("Author1"))
                .andExpect(jsonPath("$[0].avgRating").value(-1));
    }

    //I calculate avg only when it is necessary, better approach would be to create separate table to contain data about avg rate for each book
    @Test
    public void testGetBookByTitleAndAuthor() throws Exception {
        mockMvc.perform(get("/books/by-title-author")
                        .param("title", "Book1")
                        .param("author", "Author1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book1"))
                .andExpect(jsonPath("$.author").value("Author1"))
                .andExpect(jsonPath("$.avgRating").value(-1));
    }
}
