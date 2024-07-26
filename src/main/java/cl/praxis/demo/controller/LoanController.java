package cl.praxis.demo.controller;

import cl.praxis.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/history")
    public String listLoanHistory(Model model) {
        model.addAttribute("loans", loanService.findAllLoans());
        return "loanHistory";
    }
}
