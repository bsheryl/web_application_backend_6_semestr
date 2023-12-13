package org.example.utils;

import org.example.model.Student;

import java.util.OptionalDouble;

public class StudentUtils {
    public static Student checkAverage(Student student) {
        OptionalDouble average = student.getSubjects().stream().mapToInt(x -> Integer.parseInt(x.getMark())).average();
        student.setAverage(average.getAsDouble());
        return student;
    }
}
