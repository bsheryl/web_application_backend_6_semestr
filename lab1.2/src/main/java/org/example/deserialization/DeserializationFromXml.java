package org.example.deserialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.Group;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeserializationFromXml {
    public Group readXml(String path) {
        XmlMapper xmlMapper = new XmlMapper();
        Group group;
        try {
            String xmlData = new String(Files.readAllBytes(Paths.get(path)));
            group = xmlMapper.readValue(xmlData, Group.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return group;
    }
}
