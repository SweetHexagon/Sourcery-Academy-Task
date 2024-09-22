package sourceryacademy.library.services.realizations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sourceryacademy.library.data.Book;
import sourceryacademy.library.dtos.BookDTO;
import sourceryacademy.library.exceptions.BookNotFoundException;
import sourceryacademy.library.repositories.BookRepository;
import sourceryacademy.library.services.Interfaces.BookServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;

    private static final int PAGE_SIZE = 10;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }



    @Override
    public BookDTO  getBookByTitleAndAuthor(String title, String author) {
        Optional<Book> book = bookRepository.findBookByTitleAndAuthor(title, author);

        if (book.isPresent()) {
            return new BookDTO(book.get());
        }else {
            throw new BookNotFoundException("no book with such title and author");
        }
    }

    @Override
    public List<BookDTO> getBookDtosByAuthor(String author, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Book> books = bookRepository.findBooksByAuthor(author, pageable);

        List<BookDTO> ListOfBookDTOs = books.getContent().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());

        return ListOfBookDTOs;
    }

    @Override
    public List<BookDTO> getBookDtosByTitle(String title, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Book> books = bookRepository.findBooksByTitle(title, pageable);

        List<BookDTO> ListOfBookDTOs = books.getContent().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());

        return ListOfBookDTOs;
    }

    @Override
    public List<BookDTO> getBookDtosByYear(int year, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Book> books = bookRepository.findBooksByPublishYear(year, pageable);

        List<BookDTO> ListOfBookDTOs = books.getContent().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());

        return ListOfBookDTOs;
    }

    @Override
    public List<BookDTO> getBookDtosPagedAndSortedByRates(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<BookDTO> bookDTOs = bookRepository.findBookDtosPagedAndSortedByRates(pageable);

        List<BookDTO> ListOfBookDTOs = bookDTOs.getContent().stream().toList();

        return ListOfBookDTOs;
    }
}
