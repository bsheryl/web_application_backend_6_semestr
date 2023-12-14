package org.example;

import org.example.model.Student;
import org.example.repository.Repository;
import org.example.utils.ConnectionUtils;

import java.sql.Connection;
import java.util.Map;

public class App {
    public static void main( String[] args ) {
        Connection connection = ConnectionUtils.getConnection();
        Repository repository = new Repository(connection);
        Student tom = repository.createStudent("Tom", "Lee");
        Student harry = repository.createStudent("Harry", "Potter");
        Student alfred = repository.createStudent("Alfred", "Hill");
        Student calvin = repository.createStudent("Calvin", "Pierce");
        Student daniel = repository.createStudent("Daniel", "Walker");
        Map<String, Student> students = repository.getAllStudent();
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
