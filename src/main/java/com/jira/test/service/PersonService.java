package com.jira.test.service;

import com.jira.test.entity.Person;
import com.jira.test.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing persons.
 * Provides methods to create, read, update, and delete Person entities.
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Adds a new person to the repository.
     * @param person the person to add.
     * @return the saved person.
     */
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Retrieves all persons from the repository.
     * @return a list of all persons.
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Retrieves a person by their ID.
     * @param id the ID of the person to retrieve.
     * @return an Optional containing the found person or an empty Optional if no person is found.
     */
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    /**
     * Updates an existing person in the repository.
     * @param person the person with updated information.
     * @return the updated person.
     */
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Deletes a person from the repository by their ID.
     * @param id the ID of the person to delete.
     */
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    /**
     * Finds persons by their name and surname.
     * @param name the name of the person to find.
     * @param surname the surname of the person to find.
     * @return a list of persons matching the name and surname.
     */
    public List<Person> getPersonsByName(String name, String surname) {
        return personRepository.findAllByName(name); // This method needs modification to filter by surname as well.
    }
}