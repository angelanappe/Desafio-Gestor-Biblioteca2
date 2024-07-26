package cl.praxis.demo.service;

import cl.praxis.demo.model.dto.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private List<Book> bookList = new ArrayList<>();
    private Long nextId = 1L;

    public BookService() {

        bookList.add(new Book(nextId++, "123", "Canción de Hielo y Fuego", "George R. R. Martin", true));
        bookList.add(new Book(nextId++, "234", "El Resplandor", "Stephen King", true));
        bookList.add(new Book(nextId++, "345", "Inferno", "Dan Brown", true));
        bookList.add(new Book(nextId++, "456", "Sapo y Sepo", "Arnold Lobel", true));
    }

    public List<Book> findAllBooks() {
        return bookList;
    }

    public List<Book> searchBooks(String query) {
        return bookList.stream()
                .filter(book -> book.getTitle().contains(query) || book.getAuthor().contains(query))
                .collect(Collectors.toList());
    }

    public void addBook(Book book) {
        book.setId(nextId++);
        bookList.add(book);
    }

    public Optional<Book> findBookById(Long id) {
        return bookList.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }
}
