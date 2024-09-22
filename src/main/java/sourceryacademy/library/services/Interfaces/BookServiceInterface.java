package sourceryacademy.library.services.Interfaces;

import org.springframework.data.domain.Page;
import sourceryacademy.library.data.Book;
import sourceryacademy.library.dtos.BookDTO;

import java.util.List;

public interface BookServiceInterface {

    BookDTO getBookByTitleAndAuthor(String title, String author);

    List<BookDTO> getBookDtosByAuthor(String author, int page);

    List<BookDTO> getBookDtosByTitle(String title, int page);

    List<BookDTO> getBookDtosByYear(int year, int page);

    List<BookDTO> getBookDtosPagedAndSortedByRates(int page);
}
