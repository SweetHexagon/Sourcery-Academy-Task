package sourceryacademy.library.data;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sourceryacademy.library.data.customKeys.RateKey;


@Entity
@Table(name = "rate")
@NoArgsConstructor

public class Rate {

        @EmbeddedId
        private RateKey id = new RateKey();

        @Getter @Setter
        @ManyToOne
        @MapsId("userId")
        @JoinColumn(name = "user_id")
        private User user;

        @Getter @Setter
        @ManyToOne
        @MapsId("bookId")
        @JoinColumn(name = "book_id")
        private Book book;

        @Getter @Setter
        @Min(1)
        @Max(5)
        private int rating;

}

