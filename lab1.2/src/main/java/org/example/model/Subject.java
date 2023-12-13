package org.example.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "subject")
public class Subject {
    @JacksonXmlProperty(isAttribute = true)
    private String title;
    @JacksonXmlProperty(isAttribute = true)
    private String mark;

    public String getTitle() {
        return title;
    }

    public Subject setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMark() {
        return mark;
    }

    public Subject setMark(String mark) {
        this.mark = mark;
        return this;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "title='" + title + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
