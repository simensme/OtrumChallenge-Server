package com.otrumchallenge.otrumcase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/name-list")
public class NameListApi {
    private final List<Person> people;

    public NameListApi() {
        // Initialize the list with your data
        people = new ArrayList<>();
        people.add(new Person("Ola Nordmann", 25));
        people.add(new Person("Kari Nordmann", 36));
        people.add(new Person("Astrid Hansen", 50));
        people.add(new Person("Einar Larsen", 95));
        people.add(new Person("Ole Pettersen", 39));
        people.add(new Person("Emil Johnsen", 39));
        people.add(new Person("Erling Karlsen", 39));
    }

    @GetMapping
    public List<Person> getNameList() {
        return people;
    }

    @GetMapping("/{personId}")
    public String getNameListEl(@PathVariable String personId) {
        try {
            int index = Integer.parseInt(personId);
            if (index >= 0 && index < people.size()) {
                Person person = people.get(index);
                return "Name: " + person.getName() + ", Age: " + person.getAge();
            } else {
                return "Person not found";
            }
        } catch (NumberFormatException e) {
            return "Invalid personId format";
        }
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
