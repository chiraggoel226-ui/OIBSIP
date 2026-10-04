package DigitalLibraryManagementSystem.service;

import DigitalLibraryManagementSystem.model.Book;
import DigitalLibraryManagementSystem.model.Booking;
import DigitalLibraryManagementSystem.model.User;
import DigitalLibraryManagementSystem.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking bookBook(User user, Book book) {

        if (book.getQuantity() > 0) {
            throw new RuntimeException(
                    "Book is currently available. You can issue it directly."
            );
        }

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setBook(book);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus("WAITING");

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }
}