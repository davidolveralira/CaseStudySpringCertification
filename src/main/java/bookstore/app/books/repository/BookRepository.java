package bookstore.app.books.repository;

import bookstore.app.books.models.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository <BookEntity, String>{
    Optional<BookEntity> findByIsbn(String isbn);
}
