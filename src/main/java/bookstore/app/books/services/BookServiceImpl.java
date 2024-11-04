package bookstore.app.books.services;

import bookstore.app.books.models.entity.BookEntity;
import bookstore.app.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<BookEntity> findAll() {
        return (List<BookEntity>) repository.findAll();
    }

    @Override
    @Transactional
    public BookEntity saveBook(BookEntity book) {
        return repository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookEntity> findByIsbn(String isbn) {
        return repository.findById(String.valueOf(Long.valueOf(isbn)));
    }

    @Override
    @Transactional
    public Optional<BookEntity> updateEdition(String isbn, String edition) {
        Optional<BookEntity> optionalBook = repository.findById(String.valueOf(Long.valueOf(isbn)));
        if (optionalBook.isPresent()) {
            BookEntity book = optionalBook.get();
            book.setEdition(edition);
            repository.save(book);
            return Optional.of(book);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteByIsbn(String isbn) {
        if (repository.existsById(String.valueOf(isbn))) {
            repository.deleteById(String.valueOf(isbn));
            return true;
        }
        return false;
    }
}
