package Nikolai023.stationList.server.parser;

import Nikolai023.stationList.server.entities.Dictionary;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;


public class XMLParser {
    public Dictionary parseXML(InputStream inputStream) {
        Dictionary dictionary = new Dictionary();
        try {
            JAXBContext context = JAXBContext.newInstance(Dictionary.class);
            Unmarshaller um = context.createUnmarshaller();
            dictionary = (Dictionary) um.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
