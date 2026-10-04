package DigitalLibraryManagementSystem.repository;

import DigitalLibraryManagementSystem.model.ContactMessage;
import DigitalLibraryManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactMessage, Long> {

    List<ContactMessage> findByUser(User user);

    List<ContactMessage> findByStatus(String status);
}