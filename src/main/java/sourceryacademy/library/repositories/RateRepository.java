package sourceryacademy.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sourceryacademy.library.data.Book;
import sourceryacademy.library.data.Rate;
import sourceryacademy.library.data.User;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByUser(User user);
    List<Rate> findByBook(Book book);
}
