package org.example.repository;

import org.example.model.Group;
import org.example.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {
    private Connection connection;

    public Repository(Connection connection) {
        this.connection = connection;
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

    public Student selectStudentById(String id) {
        Student student = new Student();
        try (Statement statement = connection.createStatement()) {
            String query = "select * from student where id='" + id + "';";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                student.setId(resultSet.getString("id"));
            student.setFirstname(resultSet.getString("firstname"));
            student.setLastname(resultSet.getString("lastname"));
            Group group = selectGroupById(resultSet.getString("student_group_id"));
            student.setGroup(group);
            } else {
                System.out.println("No such student");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
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


    public void deleteGroupById(String id) {
        try (Statement statement = connection.createStatement()) {
            String query = "delete from student_group where id='" + id + "';";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
