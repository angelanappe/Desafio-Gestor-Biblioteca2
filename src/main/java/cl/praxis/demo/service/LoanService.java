package cl.praxis.demo.service;

import cl.praxis.demo.model.dto.Book;
import cl.praxis.demo.model.dto.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {
    private List<Loan> loans = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private BookService bookService;

    public void requestLoan(Long bookId, String userName) {
        Optional<Book> bookOpt = bookService.findBookById(bookId);

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (book.isAvailable()) {
                Loan loan = new Loan(nextId++, userName, book);
                loans.add(loan);
                book.setAvailable(false);
            }
        }
    }

    public void returnLoan(Long loanId) {
        Optional<Loan> loanOpt = loans.stream()
                .filter(l -> l.getId().equals(loanId))
                .findFirst();

        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            loan.getBook().setAvailable(true);
            loan.setReturnDate(LocalDate.now());
        }
    }

    public List<Loan> findAllLoans() {
        return loans;
    }

    public List<Loan> findLoansByUser(String userName) {
        return loans.stream()
                .filter(loan -> loan.getUserName().equals(userName))
                .collect(Collectors.toList());
    }
}
