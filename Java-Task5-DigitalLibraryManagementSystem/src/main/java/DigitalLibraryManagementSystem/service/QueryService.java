package DigitalLibraryManagementSystem.service;

import DigitalLibraryManagementSystem.model.Query;
import DigitalLibraryManagementSystem.model.User;
import DigitalLibraryManagementSystem.repository.QueryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueryService {

    private final QueryRepository queryRepository;

    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public Query submitQuery(Query query) {

        query.setCreatedAt(LocalDateTime.now());
        query.setStatus("OPEN");

        return queryRepository.save(query);
    }

    public List<Query> getAllQueries() {
        return queryRepository.findAll();
    }

    public List<Query> getQueriesByUser(User user) {
        return queryRepository.findByUser(user);
    }

    public void markQueryResolved(Long queryId) {

        Query query = queryRepository.findById(queryId)
                .orElseThrow(() ->
                        new RuntimeException("Query not found"));

        query.setStatus("RESOLVED");

        queryRepository.save(query);
    }
}