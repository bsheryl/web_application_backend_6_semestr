package org.example;

import org.example.deserialization.DeserializationFromJson;
import org.example.model.Person;
import org.example.utils.ListUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        DeserializationFromJson deserialization = new DeserializationFromJson();
        List<Person> persons = deserialization.readJson("lab2.2/src/main/resources/sample.json");
        ListUtils listUtils = new ListUtils(persons);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            listUtils.printInfo();
            System.out.println("Enter command");
            input = scanner.nextLine();
            if (input.equals("print")) {
                listUtils.printList();
            } else if (input.equals("insert")) {
                input = scanner.nextLine();
                listUtils.insert(deserialization.readString(input));
            } else if (input.equals("update")) {
                System.out.println("Enter person");
                input = scanner.nextLine();
                int index = listUtils.getIndexOfPerson(deserialization.readString(input));
                if (index >= 0) {
                    System.out.println("Enter new data");
                    input = scanner.nextLine();
                    listUtils.update(index, deserialization.readString(input));
                }
            } else if (input.equals("delete")) {
                System.out.println("Enter person");
                input = scanner.nextLine();
                listUtils.delete(deserialization.readString(input));
            } else if (input.equals("sort")) {
                System.out.println("Enter field");
                input = scanner.nextLine();
                listUtils.sort(input);
            }
        }
    }
}
