package DigitalLibraryManagementSystem.service;

import DigitalLibraryManagementSystem.model.Book;
import DigitalLibraryManagementSystem.model.Issue;
import DigitalLibraryManagementSystem.model.User;
import DigitalLibraryManagementSystem.repository.BookRepository;
import DigitalLibraryManagementSystem.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;
    private final FineService fineService;

    public IssueService(IssueRepository issueRepository,
                        BookRepository bookRepository,
                        FineService fineService) {
        this.issueRepository = issueRepository;
        this.bookRepository = bookRepository;
        this.fineService = fineService;
    }

    public Issue issueBook(User user, Book book) {

        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        Issue issue = new Issue();

        issue.setUser(user);
        issue.setBook(book);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(14));
        issue.setStatus("ISSUED");

        return issueRepository.save(issue);
    }

    public Issue returnBook(Long issueId) {

        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() ->
                        new RuntimeException("Issue record not found"));

        if (!issue.getStatus().equals("ISSUED")) {
            throw new RuntimeException("Book is already returned");
        }

        issue.setReturnDate(LocalDate.now());
        issue.setStatus("RETURNED");

        Book book = issue.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        Issue savedIssue = issueRepository.save(issue);

        // Automatically generate fine if overdue
        fineService.generateFine(savedIssue);

        return savedIssue;
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public List<Issue> getIssuesByUser(User user) {
        return issueRepository.findByUser(user);
    }
}