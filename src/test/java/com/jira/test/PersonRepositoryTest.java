package com.jira.test;

import com.jira.test.entity.Person;
import com.jira.test.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSaveAndFindById() {
        Person person = new Person();
        person.setName("John");
        person.setSurname("Doe");
        person.setAge(30);
        person = personRepository.save(person);

        assertThat(personRepository.findById(person.getId())).isPresent()
                .contains(person);
    }

    @Test
    void testFindAll() {
        Person person1 = new Person("Alice", "Smith", 25);
        Person person2 = new Person("Bob", "Johnson", 35);
        personRepository.save(person1);
        personRepository.save(person2);

        List<Person> persons = personRepository.findAll();
        assertThat(persons).hasSize(2);
    }

    @Test
    void testDelete() {
        Person person = new Person("Charlie", "Brown", 20);
        person = personRepository.save(person);
        personRepository.deleteById(person.getId());

        assertThat(personRepository.findById(person.getId())).isEmpty();
    }

    @Test
    void testFindAllByName() {
        Person person1 = new Person("David", "Smith", 45);
        Person person2 = new Person("David", "Jones", 55);
        personRepository.save(person1);
        personRepository.save(person2);

        List<Person> persons = personRepository.findAllByName("David");
        assertThat(persons).hasSize(2).containsExactlyInAnyOrder(person1, person2);
    }

}