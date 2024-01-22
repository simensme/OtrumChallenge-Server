package com.otrumchallenge.otrumcase;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/name-list")
@CrossOrigin(origins = "http://localhost:5173")
public class NameListApi {
    private final List<Person> people;

    public NameListApi() {
        people = new ArrayList<>();
        people.add(new Person("Ola Nordmann", 25));
        people.add(new Person("Kari Nordmann", 36));
        people.add(new Person("Astrid Hansen", 50));
        people.add(new Person("Einar Larsen", 95));
        people.add(new Person("Ole Pettersen", 39));
        people.add(new Person("Emil Johnsen", 39));
        people.add(new Person("Erling Karlsen", 39));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getNameList() {
        return people;
    }

    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getNameListEl(@PathVariable String personId) {
        try {
            int index = Integer.parseInt(personId);
            if (index >= 0 && index < people.size()) {
                Person person = people.get(index);
                return ResponseEntity.ok().body(person);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPerson(@RequestBody Person newPerson) {
        people.add(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
