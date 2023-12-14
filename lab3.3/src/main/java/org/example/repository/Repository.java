package org.example.repository;

import org.example.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private Connection connection;
    private Map<String, Student> storage;

    public Repository(Connection connection) {
        this.connection = connection;
        storage = new HashMap<>();
    }

    public Student createStudent(String firstname, String lastname) {
        Student student = new Student();
        try (Statement statement = connection.createStatement()) {
            String query = "with inserted as (" +
                    "insert into student (firstname, lastname) values ('" +
                    firstname + "', '" + lastname + "') " +
                    "returning id) " +
                    "select id from inserted;";
            ResultSet resultSet = statement.executeQuery(query);
            String id = null;
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
            student.setId(id);
            student.setFirstname(firstname);
            student.setLastname(lastname);
            storage.put(id, student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Map<String, Student> getAllStudent() {
        Student student;
        try (Statement statement = connection.createStatement()) {
            String query = "select * from student;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getString("id"));
                student.setFirstname(resultSet.getString("firstname"));
                student.setLastname(resultSet.getString("lastname"));
                storage.put(student.getId(), student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storage;
    }
}
