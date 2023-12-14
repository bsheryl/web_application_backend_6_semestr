package org.example.repository;

import org.example.model.Group;
import org.example.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Repository {
    private Connection connection;
    private Map<String, Student> storage;

    public Repository(Connection connection) {
        this.connection = connection;
        storage = new HashMap<>();
    }

    public Student createStudent(String firstname, String lastname, Group group) {
        Student student = new Student();
        try (Statement statement = connection.createStatement()) {
            String query = "with inserted as (" +
                    "insert into student (firstname, lastname, student_group_id) values ('" +
                    firstname + "', '" + lastname + "', '" + group.getId() + "')" +
                    "returning id)" +
                    "select id from inserted;";
            ResultSet resultSet = statement.executeQuery(query);
            String id = null;
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
            student.setId(id);
            student.setFirstname(firstname);
            student.setLastname(lastname);
            student.setGroup(group);
            group.addStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Group createGroup(String name) {
        Group group = new Group();
        try (Statement statement = connection.createStatement()) {
            String query = "with inserted as (" +
                    "insert into student_group (groupname) values ('" + name + "')" +
                    "returning id)" +
                    "select id from inserted;";
            ResultSet resultSet = statement.executeQuery(query);
            String id = null;
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
            group.setId(id);
            group.setName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    private Group selectGroupById(String id) {
        Group group = new Group();
        try (Statement statement = connection.createStatement()) {
            String query = "select * from student_group where id='" + id + "';";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                group.setId(resultSet.getString("id"));
                group.setName(resultSet.getString("groupname"));
            } else {
                System.out.println("No such group");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public List<Student> getStudentsByGroupAndSortedByField(Group group, String field) {
        List<Student> list = new ArrayList<>();
        Student student;
        try (Statement statement = connection.createStatement()) {
            String query = "select * from student " +
                    "where student_group_id = '" + group.getId() + "' " +
                    "order by " + field + ";";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getString("id"));
                student.setFirstname(resultSet.getString("firstname"));
                student.setLastname(resultSet.getString("lastname"));
                student.setGroup(group);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
