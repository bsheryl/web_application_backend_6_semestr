package org.example;

import org.example.deserialization.DeserializationFromXml;
import org.example.model.Group;
import org.example.serialization.SerializationToXml;
import org.example.utils.StudentUtils;

public class App {
    public static void main( String[] args ) {
        DeserializationFromXml deserialize = new DeserializationFromXml();
        Group group = deserialize.readXml(args[0]);
        group.getStudents().stream().forEach(StudentUtils::checkAverage);
        SerializationToXml serialize = new SerializationToXml();
        serialize.apply(group, args[1]);
    }
}
