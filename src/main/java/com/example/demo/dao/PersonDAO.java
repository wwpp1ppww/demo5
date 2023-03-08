package com.example.demo.dao;

import com.example.demo.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(1, "Tom"));
        people.add(new Person(2, "Jack"));
    }

    public List<Person> index(){
        return people;
    }
}
