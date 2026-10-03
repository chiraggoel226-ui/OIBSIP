package DigitalLibraryManagementSystem.repository;

import DigitalLibraryManagementSystem.model.Query;
import DigitalLibraryManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryRepository extends JpaRepository<Query, Long> {

    List<Query> findByUser(User user);

    List<Query> findByStatus(String status);
}