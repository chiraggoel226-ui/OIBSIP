package DigitalLibraryManagementSystem.repository;

import DigitalLibraryManagementSystem.model.Booking;
import DigitalLibraryManagementSystem.model.Book;
import DigitalLibraryManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByBook(Book book);

    List<Booking> findByStatus(String status);

    List<Booking> findByBookAndStatus(Book book, String status);
}