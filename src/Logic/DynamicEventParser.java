package Logic;

import Utilities.XMLReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DynamicEventParser {
    public DynamicEventParser(String path, String name) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("You have successfully entered the `Event parser`.");

        new XMLReader(path, "events");













        //TODO copy ProtoExtractor solution for closing the application. :) ↓
        System.exit(0);
    }
}