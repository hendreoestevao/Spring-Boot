package com.hendreoestevao.spring.services;

import com.hendreoestevao.spring.exception.ResourceNotFoundException;
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
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding person by id: " + id);

        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public Person create(Person person) {
        logger.info("Creating one Person!");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one Person!");
        Person entity =  personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(person);
    }

    public void delete(Long id) {
        Person entity =  personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.deleteById(entity.getId());
    }
}
