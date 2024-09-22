package sourceryacademy.library.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sourceryacademy.library.data.Book;
@Getter @Setter
@NoArgsConstructor
public class BookDTO {
    private double avgRating;

    private String title;

    private int publishYear;

    private String author;

    public BookDTO(Book book) {
        this.title = book.getTitle();
        this.publishYear = book.getPublishYear();
        this.author = book.getAuthor();
        this.avgRating = -1;
    }

    public BookDTO(String title, String author, int publishYear, double avgRating) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.avgRating = avgRating;
    }
    @Override
    public String toString() {
        return "BookDTO{title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                ", avgRating=" + avgRating + '}';
    }

}
