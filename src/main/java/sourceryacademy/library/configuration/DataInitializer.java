package sourceryacademy.library.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sourceryacademy.library.data.Book;
import sourceryacademy.library.data.Rate;
import sourceryacademy.library.data.User;
import sourceryacademy.library.dtos.BookDTO;
import sourceryacademy.library.repositories.BookRepository;
import sourceryacademy.library.repositories.RateRepository;
import sourceryacademy.library.repositories.UserRepository;
import sourceryacademy.library.services.realizations.BookService;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RateRepository rateRepository;
    private final BookService bookService;

    public DataInitializer(BookRepository bookRepository, UserRepository userRepository, RateRepository rateRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
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

        System.out.println("Data initialization complete.");


    }
}
