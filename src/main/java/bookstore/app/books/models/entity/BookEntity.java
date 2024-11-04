package bookstore.app.books.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="books")
public class BookEntity {

    @Id
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "edition")
    private String edition;

    // Constructor vacío requerido por JPA
    public BookEntity() {
    }

    // Constructor opcional con parámetros para facilidad
    public BookEntity(String isbn, String title, String author, String edition) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.edition = edition;
    }

}
