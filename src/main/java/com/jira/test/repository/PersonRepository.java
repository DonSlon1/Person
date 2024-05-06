package com.jira.test.repository;

import com.jira.test.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Person} instances.
 * Provides basic CRUD operations due to the extension of {@link JpaRepository}.
 * Includes custom finder methods for person entities.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Finds all persons with the specified name.
     * @param name the name to search for.
     * @return a list of persons with the specified name.
     */
    List<Person> findAllByName(String name);
}