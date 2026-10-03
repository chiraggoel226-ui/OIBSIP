package DigitalLibraryManagementSystem.repository;

import DigitalLibraryManagementSystem.model.Issue;
import DigitalLibraryManagementSystem.model.User;
import DigitalLibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByUser(User user);

    List<Issue> findByBook(Book book);

    List<Issue> findByStatus(String status);

    List<Issue> findByUserAndStatus(User user, String status);
}