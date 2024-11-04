package bookstore.app.books.services;

import bookstore.app.books.models.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    // Obtener todos los libros
    List<BookEntity> findAll();

    // Guardar un nuevo libro
    BookEntity saveBook(BookEntity book);

    // Encontrar un libro por ISBN
    Optional<BookEntity> findByIsbn(String isbn);

    // Actualizar la edición de un libro
    Optional<BookEntity> updateEdition(String isbn, String edition);

    // Eliminar un libro por ISBN
    boolean deleteByIsbn(String isbn);


}
