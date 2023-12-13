package org.example.deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeserializationFromJson {
    ObjectMapper objectMapper = new ObjectMapper();
    public List<Person> readJson(String path) {
        List<Person> persons = new ArrayList<>();
        try {
            persons = objectMapper.readValue(new File(path), new TypeReference<List<Person>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public Person readString(String json) {
        Person person = null;
        try {
            person = objectMapper.readValue(json, Person.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }
}
