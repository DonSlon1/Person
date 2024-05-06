package com.jira.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * The {@code Person} class represents a person entity with basic personal information.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private int age;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Person() {
    }
    /**
     * Gets the ID of the person.
     * @return the ID of the person.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     * @param id the new ID of the person.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the person.
     * @return the name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     * @param name the new name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the surname of the person.
     * @return the surname of the person.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the person.
     * @param surname the new surname of the person.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the age of the person.
     * @return the age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     * @param age the new age of the person.
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}