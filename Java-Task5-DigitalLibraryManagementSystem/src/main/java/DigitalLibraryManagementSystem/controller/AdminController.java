package DigitalLibraryManagementSystem.controller;

import DigitalLibraryManagementSystem.model.Book;
import DigitalLibraryManagementSystem.repository.UserRepository;
import DigitalLibraryManagementSystem.service.BookService;
import DigitalLibraryManagementSystem.service.FineService;
import DigitalLibraryManagementSystem.service.IssueService;
import DigitalLibraryManagementSystem.service.QueryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookService bookService;
    private final IssueService issueService;
    private final FineService fineService;
    private final QueryService queryService;
    private final UserRepository userRepository;

    public AdminController(
            BookService bookService,
            IssueService issueService,
            FineService fineService,
            QueryService queryService,
            UserRepository userRepository) {

        this.bookService = bookService;
        this.issueService = issueService;
        this.fineService = fineService;
        this.queryService = queryService;
        this.userRepository = userRepository;
    }


    // =========================================================
    // ADMIN HOME
    // =========================================================

    @GetMapping
    public String adminHome(Model model) {

        model.addAttribute(
                "booksCount",
                bookService.getAllBooks().size()
        );

        model.addAttribute(
                "membersCount",
                userRepository.findAll().size()
        );

        model.addAttribute(
                "issuedCount",
                issueService.getAllIssues().size()
        );

        model.addAttribute(
                "finesCount",
                fineService.getAllFines().size()
        );

        model.addAttribute(
                "queriesCount",
                queryService.getAllQueries().size()
        );

        return "admin/home";
    }


    // =========================================================
    // ADMIN DASHBOARD
    // =========================================================

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute(
                "booksCount",
                bookService.getAllBooks().size()
        );

        model.addAttribute(
                "membersCount",
                userRepository.findAll().size()
        );

        model.addAttribute(
                "issuedCount",
                issueService.getAllIssues().size()
        );

        model.addAttribute(
                "finesCount",
                fineService.getAllFines().size()
        );

        model.addAttribute(
                "queriesCount",
                queryService.getAllQueries().size()
        );

        return "admin/dashboard";
    }


    // =========================================================
    // BOOK MANAGEMENT
    // =========================================================

    @GetMapping("/books")
    public String books(Model model) {

        model.addAttribute(
                "books",
                bookService.getAllBooks()
        );

        return "admin/books";
    }


    // ADD BOOK PAGE

    @GetMapping("/books/add")
    public String addBookPage(Model model) {

        model.addAttribute(
                "book",
                new Book()
        );

        return "admin/add-book";
    }


    // ADD BOOK

    @PostMapping("/books/add")
    public String addBook(
            @ModelAttribute Book book) {

        bookService.addBook(book);

        return "redirect:/admin/books";
    }


    // EDIT BOOK PAGE

    @GetMapping("/books/edit/{id}")
    public String editBookPage(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "book",
                bookService.getBookById(id)
        );

        return "admin/edit-book";
    }


    // EDIT BOOK

    @PostMapping("/books/edit/{id}")
    public String editBook(
            @PathVariable Long id,
            @ModelAttribute Book book) {

        bookService.updateBook(id, book);

        return "redirect:/admin/books";
    }


    // DELETE BOOK

    @GetMapping("/books/delete/{id}")
    public String deleteBook(
            @PathVariable Long id) {

        bookService.deleteBook(id);

        return "redirect:/admin/books";
    }


    // =========================================================
    // ISSUED BOOKS
    // =========================================================

    @GetMapping("/issues")
    public String issues(Model model) {

        model.addAttribute(
                "issues",
                issueService.getAllIssues()
        );

        return "admin/issued-books";
    }


    // =========================================================
    // MEMBERS
    // =========================================================

    @GetMapping("/users")
    public String users(Model model) {

        model.addAttribute(
                "users",
                userRepository.findAll()
        );

        return "admin/members";
    }


    // =========================================================
    // FINES
    // =========================================================

    @GetMapping("/fines")
    public String fines(Model model) {

        model.addAttribute(
                "fines",
                fineService.getAllFines()
        );

        return "admin/fines";
    }


    // MARK FINE AS PAID

    @PostMapping("/fines/pay/{id}")
    public String markFinePaid(
            @PathVariable Long id) {

        fineService.markFineAsPaid(id);

        return "redirect:/admin/fines";
    }


    // =========================================================
    // USER QUERIES
    // =========================================================

    @GetMapping("/queries")
    public String queries(Model model) {

        model.addAttribute(
                "queries",
                queryService.getAllQueries()
        );

        return "admin/queries";
    }


    // MARK QUERY AS RESOLVED

    @PostMapping("/queries/resolve/{id}")
    public String resolveQuery(
            @PathVariable Long id) {

        queryService.markQueryResolved(id);

        return "redirect:/admin/queries";
    }
}