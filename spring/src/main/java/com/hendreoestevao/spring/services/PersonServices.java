package com.hendreoestevao.spring.services;

import com.hendreoestevao.spring.model.Person;
import com.hendreoestevao.spring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {
        logger.info("Finding All Persons");

        List<Person> persons = new ArrayList<Person>();
        for(int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("Finding person by id: " + id);

    Person person = new Person();
    person.setId(counter.incrementAndGet());
    person.setFirstName("John");
    person.setLastName("Smith");
    person.setAddress("Brasil");
    person.setGender("Male");

    return person;
    }

    public Person create(Person person) {
        logger.info("Creating one Person!");
        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one Person!");
        return person;
    }

    public void delete(String id) {
        logger.info("Delete one Person!");
    }


    private Person mockPerson(int i) {
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Person Name" + i);
        person.setLastName("Person LastName" + i);
        person.setAddress("Brasil");
        person.setGender("Male");

        return person;
    }

}
