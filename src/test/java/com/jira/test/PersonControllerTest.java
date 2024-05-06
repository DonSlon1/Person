package com.jira.test;

import com.jira.test.entity.Person;
import com.jira.test.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void testAddPerson() throws Exception {
        Person person = new Person("Jake", "Gibson", 33);
        given(personService.addPerson(person)).willReturn(person);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jake\",\"surname\":\"Gibson\",\"age\":33}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jake"));
    }

    @Test
    void testGetAllPersons() throws Exception {
        Person person = new Person("Laura", "Moore", 27);
        given(personService.getAllPersons()).willReturn(Collections.singletonList(person));

        mockMvc.perform(get("/person")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laura"));
    }

    @Test
    void testGetPersonById() throws Exception {
        Person person = new Person("Mark", "White", 36);
        given(personService.getPersonById(1L)).willReturn(Optional.of(person));

        mockMvc.perform(get("/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mark"));
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person person = new Person("Nina", "Brown", 29);
        given(personService.updatePerson(person)).willReturn(person);

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Nina\",\"surname\":\"Brown\",\"age\":29}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(29));
    }

    @Test
    void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}