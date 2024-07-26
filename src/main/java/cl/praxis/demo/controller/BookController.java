package cl.praxis.demo.controller;

import cl.praxis.demo.model.dto.Book;
import cl.praxis.demo.service.BookService;
import cl.praxis.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private LoanService loanService;

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String query, Model model) {
        List<Book> books = bookService.searchBooks(query);
        model.addAttribute("books", books);
        return "bookList";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        book.setAvailable(true);
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/reserve/{id}")
    public String reserve(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookId", id);
        return "reserveForm";
    }

    @PostMapping("/reserve")
    public String handleReserve(@RequestParam Long bookId, @RequestParam String userName) {
        loanService.requestLoan(bookId, userName);
        return "redirect:/loans/history";
    }

    @GetMapping("/reserved")
    public String listReservedBooks(Model model) {
        List<Book> reservedBooks = bookService.findAllBooks();
        reservedBooks.removeIf(Book::isAvailable);
        model.addAttribute("books", reservedBooks);
        return "reservedBooks";
    }

    @PostMapping("/return/{loanId}")
    public String returnBook(@PathVariable("loanId") Long loanId) {
        loanService.returnLoan(loanId);
        return "redirect:/loans/history";
    }
}
