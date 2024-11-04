package bookstore.app.books.consumer.controllers;

import bookstore.app.books.models.entity.BookEntity;
import bookstore.app.books.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/bookstore")
@Tag(name = "Bookstore", description = "API for managing books in the bookstore")
public class BookStoreController {

    @Autowired
    private BookService service;

    /**
     * Retrieve all books in the bookstore.
     *
     * @return List of all books.
     */
    @GetMapping("/all")
    @Operation(summary = "Get all books", description = "Retrieve a list of all books in the bookstore.")
    public ResponseEntity<List<BookEntity>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Add a new book to the bookstore.
     *
     * @param book The book entity to add.
     * @return The saved book entity.
     */
    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Add a new book to the bookstore with its details.")
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity book) {
        BookEntity savedBook = service.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    /**
     * Retrieve a book by its ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return The book entity if found, otherwise a 404 status.
     */
    @GetMapping("/{isbn}")
    @Operation(summary = "Get a book by ISBN", description = "Retrieve a book's details by its ISBN.")
    public ResponseEntity<BookEntity> getBookByIsbn(@PathVariable String isbn) {
        Optional<BookEntity> book = service.findByIsbn(isbn);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update the edition of a book by its ISBN.
     *
     * @param isbn The ISBN of the book.
     * @param edition The new edition of the book.
     * @return The updated book entity if found, otherwise a 404 status.
     */
    @PutMapping("/update/{isbn}")
    @Operation(summary = "Update book edition", description = "Update the edition of a book by providing its ISBN.")
    public ResponseEntity<BookEntity> updateBookEdition(@PathVariable String isbn, @RequestParam String edition) {
        Optional<BookEntity> updatedBook = service.updateEdition(isbn, edition);
        return updatedBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete a book by its ISBN.
     *
     * @param isbn The ISBN of the book to delete.
     * @return A 204 status if the book was deleted, otherwise a 404 status.
     */
    @DeleteMapping("/delete/{isbn}")
    @Operation(summary = "Delete a book by ISBN", description = "Delete a book from the bookstore by its ISBN.")
    public ResponseEntity<Void> deleteBookByIsbn(@PathVariable String isbn) {
        if (service.deleteByIsbn(isbn)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}