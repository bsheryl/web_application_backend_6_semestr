package org.example;

import org.example.model.Account;
import org.example.model.Student;
import org.example.repository.Repository;
import org.example.utils.ConnectionUtils;

import java.sql.Connection;

public class App {
    public static void main( String[] args ) {
        Connection connection = ConnectionUtils.getConnection();
        Repository repository = new Repository(connection);
        Student student = repository.createStudent("Tom", "Lee");
        Account account = repository.createAccount("tomlee", "qwerty", student);
        System.out.println(account);
    }
}
