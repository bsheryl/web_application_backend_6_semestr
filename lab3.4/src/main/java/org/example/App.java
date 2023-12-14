package org.example;

import org.example.model.Group;
import org.example.model.Student;
import org.example.repository.Repository;
import org.example.utils.ConnectionUtils;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class App {
    public static void main( String[] args ) {
        Connection connection = ConnectionUtils.getConnection();
        Repository repository = new Repository(connection);
        Group ivt = repository.createGroup("IVT");
        Group ibts = repository.createGroup("IBTS");
        Student tom = repository.createStudent("Tom", "Lee", ivt);
        Student harry = repository.createStudent("Harry", "Potter", ibts);
        Student alfred = repository.createStudent("Alfred", "Hill", ivt);
        Student calvin = repository.createStudent("Calvin", "Pierce", ivt);
        Student daniel = repository.createStudent("Daniel", "Walker", ivt);
        List<Student> students = repository.getStudentsByGroupAndSortedByField(ivt, "firstname");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
