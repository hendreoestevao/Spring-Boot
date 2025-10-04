package com.hendreoestevao.spring.services;

import com.hendreoestevao.spring.data.dto.PersonDTO;
import com.hendreoestevao.spring.exception.ResourceNotFoundException;
import com.hendreoestevao.spring.model.Person;
import com.hendreoestevao.spring.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.hendreoestevao.spring.mapper.ObjectMapper.parseListObject;
import static com.hendreoestevao.spring.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository personRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public List<PersonDTO> findAll() {
        logger.info("Finding All Persons");
        return parseListObject(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding person by id: " + id);

        var entity =  personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one Person!");

        var entity = parseObject(person, Person.class);

        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one Person!");
        Person entity =  personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        Person entity =  personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.deleteById(entity.getId());
    }
}
