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
        User user1 = new User();
        user1.setUsername("User1");
        User user2 = new User();
        user2.setUsername("User2");
        User user3 = new User();
        user3.setUsername("User3");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        Book book1 = new Book();
        book1.setTitle("Book One");
        book1.setAuthor("Author A");
        book1.setPublishYear(2021);

        Book book2 = new Book();
        book2.setTitle("Book Two");
        book2.setAuthor("Author B");
        book2.setPublishYear(2022);

        Book book3 = new Book();
        book3.setTitle("Book Three");
        book3.setAuthor("Author A");
        book3.setPublishYear(2020);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3));

        Rate rate1 = new Rate();
        rate1.setUser(user1);
        rate1.setBook(book1);
        rate1.setRating(5);

        Rate rate2 = new Rate();
        rate2.setUser(user2);
        rate2.setBook(book1);
        rate2.setRating(4);

        Rate rate3 = new Rate();
        rate3.setUser(user1);
        rate3.setBook(book2);
        rate3.setRating(3);

        Rate rate4 = new Rate();
        rate4.setUser(user1);
        rate4.setBook(book3);
        rate4.setRating(4);

        Rate rate5 = new Rate();
        rate5.setUser(user2);
        rate5.setBook(book3);
        rate5.setRating(3);

        Rate rate6 = new Rate();
        rate6.setUser(user3);
        rate6.setBook(book3);
        rate6.setRating(5);

        rateRepository.saveAll(Arrays.asList(rate1, rate2, rate3, rate4, rate5, rate6));

        System.out.println("Data initialization complete.");

        // Manual testing:

        Iterable<Rate> rates = rateRepository.findAll();
        System.out.println("Rates in the database:");
        for (Rate rate : rates) {
            System.out.println(rate.getUser().getUsername() + " " + rate.getBook().getTitle() + " " + rate.getRating());
        }

        Iterable<Book> books = bookRepository.findAll();
        System.out.println("Books in the database:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }

        books.forEach(book -> {
            double avgRating = book.getRates().stream().mapToInt(Rate::getRating).average().orElse(0);
            System.out.println("Average rating for " + book.getTitle() + " is " + avgRating);
        });

        List<BookDTO> sortedbooks = bookService.getBookDtosPagedAndSortedByRates(0);
        sortedbooks.forEach(System.out::println);

    }
}
