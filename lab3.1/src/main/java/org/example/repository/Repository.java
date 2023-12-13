package org.example.repository;

import org.example.model.Account;
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

    public Student createStudent(String firstname, String lastname) {
        Student student = new Student();
        try (Statement statement = connection.createStatement()) {
            String query = "with inserted as (" +
                    "insert into student (firstname, lastname) values ('" + firstname + "', '" + lastname + "')" +
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Account createAccount(String login, String pass, Student student) {
        Account account = new Account();
        try (Statement statement = connection.createStatement()) {
            String query = "with inserted as (" +
                    "insert into account (login, pass, student) values ('" + login + "', '" + pass + "', '" + student.getId() + "')" +
                    "returning id)" +
                    "select id from inserted;";
            ResultSet resultSet = statement.executeQuery(query);
            String id = null;
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
            account.setId(id);
            account.setLogin(login);
            account.setPass(pass);
            account.setStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
