package DigitalLibraryManagementSystem.service;

import DigitalLibraryManagementSystem.model.Fine;
import DigitalLibraryManagementSystem.model.Issue;
import DigitalLibraryManagementSystem.model.User;
import DigitalLibraryManagementSystem.repository.FineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class FineService {

    private static final double FINE_PER_DAY = 5.0;

    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public Fine generateFine(Issue issue) {

        LocalDate returnDate = issue.getReturnDate();

        if (returnDate == null) {
            returnDate = LocalDate.now();
        }

        if (!returnDate.isAfter(issue.getDueDate())) {
            return null;
        }

        long overdueDays =
                ChronoUnit.DAYS.between(
                        issue.getDueDate(),
                        returnDate
                );

        double amount = overdueDays * FINE_PER_DAY;

        Fine fine = new Fine();

        fine.setIssue(issue);
        fine.setUser(issue.getUser());
        fine.setAmount(amount);
        fine.setPaid(false);
        fine.setCreatedDate(LocalDate.now());

        return fineRepository.save(fine);
    }

    public void markFineAsPaid(Long fineId) {

        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() ->
                        new RuntimeException("Fine not found"));

        fine.setPaid(true);

        fineRepository.save(fine);
    }

    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    public List<Fine> getFinesByUser(User user) {
        return fineRepository.findByUser(user);
    }
}