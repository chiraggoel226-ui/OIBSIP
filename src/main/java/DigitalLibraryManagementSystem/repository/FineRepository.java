package DigitalLibraryManagementSystem.repository;

import DigitalLibraryManagementSystem.model.Fine;
import DigitalLibraryManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {

    List<Fine> findByUser(User user);

    List<Fine> findByPaid(boolean paid);
}