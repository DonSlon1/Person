package com.jira.test;

import com.jira.test.entity.Person;
import com.jira.test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link Person} entities.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Creates a new person.
     * @param person the person to create.
     * @return the ResponseEntity with status 201 (Created) and with body the new person.
     */
    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person savedPerson = personService.addPerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    /**
     * Retrieves all persons.
     * @return the ResponseEntity with status 200 (OK) and with body all persons.
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    /**
     * Retrieves a single person by ID.
     * @param id the ID of the person to retrieve.
     * @return the ResponseEntity with status 200 (OK) and with body the person, or with status 404 (Not Found) if the person does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id)
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an existing person.
     * @param person the person to update.
     * @return the ResponseEntity with status 200 (OK) and with body the updated person.
     */
    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    /**
     * Deletes a person by ID.
     * @param id the ID of the person to delete.
     * @return the ResponseEntity with status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}