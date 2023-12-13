package org.example.utils;

import org.example.model.Person;

import java.util.Comparator;
import java.util.List;

public class ListUtils {
    private List<Person> persons;

    public ListUtils(List<Person> persons) {
        this.persons = persons;
    }

    public void printInfo() {
        System.out.println("print - вывод данных");
        System.out.println("insert - ввод данных");
        System.out.println("update - изменение данных");
        System.out.println("delete - удаление данных");
        System.out.println("sort - сортирока данных");
    }

    public void printList() {
        persons.forEach(System.out::println);
    }

    public void insert(Person person) {
        persons.add(person);
    }

    public int getIndexOfPerson(Person person) {
        return persons.indexOf(person);
    }

    public void update(int index, Person person) {
        persons.set(index, person);
    }

    public void delete(Person person) {
        int index = getIndexOfPerson(person);
        if (index >= 0) {
            persons.remove(index);
        }
    }

    public void sort(String field) {
        if (field.equals("name")) {
            persons.sort(Comparator.comparing(Person::getName));
        } else if (field.equals("age")) {
            persons.sort(Comparator.comparing(Person::getAge));
        } else if (field.equals("city")) {
            persons.sort(Comparator.comparing(Person::getCity));
        }
    }
}
