package org.example;

import org.example.model.Group;
import org.example.model.Student;
import org.example.repository.Repository;
import org.example.utils.ConnectionUtils;

import java.sql.Connection;

public class App {
    public static void main( String[] args ) {
        Connection connection = ConnectionUtils.getConnection();
        Repository repository = new Repository(connection);
        Group group = repository.createGroup("IVT");
        Student tom = repository.createStudent("Tom", "Lee", group);
        Student harry = repository.createStudent("Harry", "Potter", group);
        System.out.println(repository.selectStudentById(tom.getId()));
        repository.deleteGroupById(group.getId());
        repository.selectStudentById(tom.getId());
    }
}
