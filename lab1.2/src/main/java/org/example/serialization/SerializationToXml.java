package org.example.serialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.Group;

import java.io.File;
import java.io.IOException;

public class SerializationToXml {
    public void apply(Group group, String path) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(path), group);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
