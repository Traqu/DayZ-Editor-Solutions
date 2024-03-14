package Utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLReader {
    public XMLReader(String path, String elementTagName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(path));

        NodeList itemList = document.getElementsByTagName(elementTagName);

        for (int i = 0; i < itemList.getLength(); i++) {
            Element element = (Element)itemList.item(i);
            Element event = (Element) element.getElementsByTagName("event").item(0);

            System.out.println(event.getAttribute("pos"));
        }

    }
}