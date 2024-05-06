package com.jira.test;

import com.jira.test.entity.Person;
import com.jira.test.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    void testAddAndGetPerson() {
        Person person = new Person("Eve", "Williams", 22);
        person = personService.addPerson(person);
        assertThat(personService.getPersonById(person.getId())).isPresent()
                .contains(person);
    }

    @Test
    void testUpdatePerson() {
        Person person = new Person("Frank", "Taylor", 29);
        person = personService.addPerson(person);
        person.setAge(30);
        person = personService.updatePerson(person);

        assertThat(personService.getPersonById(person.getId())).isPresent()
                .map(Person::getAge).contains(30);
    }

    @Test
    void testDeletePerson() {
        Person person = new Person("Grace", "Hall", 31);
        person = personService.addPerson(person);
        personService.deletePerson(person.getId());

        assertThat(personService.getPersonById(person.getId())).isEmpty();
    }

    @Test
    void testGetAllPersons() {
        personService.addPerson(new Person("Henry", "Adams", 40));
        personService.addPerson(new Person("Ida", "Watson", 50));

        List<Person> persons = personService.getAllPersons();
        assertThat(persons).hasSize(2);
    }
}