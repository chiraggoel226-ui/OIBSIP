package DigitalLibraryManagementSystem.controller;

import DigitalLibraryManagementSystem.model.Book;
import DigitalLibraryManagementSystem.model.Query;
import DigitalLibraryManagementSystem.model.User;
import DigitalLibraryManagementSystem.repository.UserRepository;
import DigitalLibraryManagementSystem.service.BookService;
import DigitalLibraryManagementSystem.service.BookingService;
import DigitalLibraryManagementSystem.service.FineService;
import DigitalLibraryManagementSystem.service.IssueService;
import DigitalLibraryManagementSystem.service.QueryService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final BookService bookService;
    private final IssueService issueService;
    private final FineService fineService;
    private final BookingService bookingService;
    private final QueryService queryService;
    private final UserRepository userRepository;

    public UserController(
            BookService bookService,
            IssueService issueService,
            FineService fineService,
            BookingService bookingService,
            QueryService queryService,
            UserRepository userRepository) {

        this.bookService = bookService;
        this.issueService = issueService;
        this.fineService = fineService;
        this.bookingService = bookingService;
        this.queryService = queryService;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {

        User user = getLoggedInUser(authentication);

        model.addAttribute("user", user);

        return "index";
    }

    @GetMapping("/books")
    public String books(Model model) {

        model.addAttribute("books", bookService.getAllBooks());

        return "books";
    }

    @GetMapping("/books/search")
    public String searchBooks(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "books",
                bookService.searchByTitle(keyword)
        );

        return "books";
    }

    @GetMapping("/books/category")
    public String booksByCategory(
            @RequestParam String category,
            Model model) {

        model.addAttribute(
                "books",
                bookService.searchByCategory(category)
        );

        return "books";
    }

    @GetMapping("/books/author")
    public String booksByAuthor(
            @RequestParam String author,
            Model model) {

        model.addAttribute(
                "books",
                bookService.searchByAuthor(author)
        );

        return "books";
    }

    @PostMapping("/issue/{bookId}")
    public String issueBook(
            @PathVariable Long bookId,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Book book = bookService.getBookById(bookId);

        issueService.issueBook(user, book);

        return "redirect:/user/books";
    }

    @GetMapping("/my-books")
    public String myBooks(
            Authentication authentication,
            Model model) {

        User user = getLoggedInUser(authentication);

        model.addAttribute(
                "issues",
                issueService.getIssuesByUser(user)
        );

        return "my-books";
    }

    @PostMapping("/return/{issueId}")
    public String returnBook(
            @PathVariable Long issueId) {

        issueService.returnBook(issueId);

        return "redirect:/user/my-books";
    }

    @GetMapping("/bookings")
    public String bookings(
            Authentication authentication,
            Model model) {

        User user = getLoggedInUser(authentication);

        model.addAttribute(
                "bookings",
                bookingService.getBookingsByUser(user)
        );

        return "booking";
    }

    @PostMapping("/booking/{bookId}")
    public String bookBook(
            @PathVariable Long bookId,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Book book = bookService.getBookById(bookId);

        bookingService.bookBook(user, book);

        return "redirect:/user/bookings";
    }

    @GetMapping("/fines")
    public String fines(
            Authentication authentication,
            Model model) {

        User user = getLoggedInUser(authentication);

        model.addAttribute(
                "fines",
                fineService.getFinesByUser(user)
        );

        return "fines";
    }

    @GetMapping("/query")
    public String queryPage(Model model) {

        model.addAttribute("query", new Query());

        return "contact";
    }

    @PostMapping("/query")
    public String submitQuery(
            @ModelAttribute Query query,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        query.setUser(user);

        queryService.submitQuery(query);

        return "redirect:/user/dashboard";
    }

    private User getLoggedInUser(Authentication authentication) {

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}