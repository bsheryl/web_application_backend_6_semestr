package org.example.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "student")
public class Student {
    @JacksonXmlProperty(localName = "firstname", isAttribute = true)
    private String firstname;
    @JacksonXmlProperty(isAttribute = true)
    private String lastname;
    @JacksonXmlProperty(isAttribute = true)
    private String groupnumber;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "subject")
    private List<Subject> subjects;
    private double average;


public String getFirstname() {
        return firstname;
    }

    public Student setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Student setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getGroupnumber() {
        return groupnumber;
    }

    public Student setGroupnumber(String groupnumber) {
        this.groupnumber = groupnumber;
        return this;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Student setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }

    public double getAverage() {
        return average;
    }

    public Student setAverage(double average) {
        this.average = average;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", groupnumber='" + groupnumber + '\'' +
                ", subjects=" + subjects +
                ", average=" + average +
                '}';
    }
}
