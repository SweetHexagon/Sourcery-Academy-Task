package sourceryacademy.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sourceryacademy.library.data.Book;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sourceryacademy.library.dtos.BookDTO;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitleAndAuthor(String title, String author);

    Page<Book> findBooksByTitle(String title, Pageable pageable);

    Page<Book> findBooksByAuthor(String author, Pageable pageable);

    Page<Book> findBooksByPublishYear(int year, Pageable pageable);

    @Query("SELECT new sourceryacademy.library.dtos.BookDTO(b.title, b.author, b.publishYear, COALESCE(AVG(r.rating), 0)) " +
            "FROM Book b LEFT JOIN b.rates r GROUP BY b.id, b.title, b.author, b.publishYear ORDER BY COALESCE(AVG(r.rating), 0) DESC")
    Page<BookDTO> findBookDtosPagedAndSortedByRates(Pageable pageable);

}
