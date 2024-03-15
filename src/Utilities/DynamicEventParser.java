package Utilities;

import GraphicInterfaces.Constants.Interfaces.UserPathConstants;
import GraphicInterfaces.FileChooser;
import Utilities.DataStructures.InGameObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DynamicEventParser implements UserPathConstants {

    public DynamicEventParser(String filePath, FileChooser fileChooser) throws ParserConfigurationException, IOException, SAXException {

        String output = filePath;


        Document dynamicEventDocument = getDocument(filePath);

        NodeList itemList = dynamicEventDocument.getElementsByTagName("events");

        if (itemList.getLength() == 0) {
            fileChooser.setTitle("This is not an event file!");
            try {
                Thread.sleep(1250);
                fileChooser.setTitle(" Choose file from which you want to extract objects" + PropertiesReader.getVersion(true));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("Selected file doesn't follow a dynamic event structure that DayZ Editor exports.");
        }


        StringBuilder stringBuilder = parseFile(itemList);
        writeToFile(output, stringBuilder);
        openFile(output.substring(0, output.length() - 3) + "txt");

        System.exit(0);
    }

    private static void openFile(String output) {
        try {
            Desktop.getDesktop().open(new File(output));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFile(String output, StringBuilder stringBuilder) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.substring(0, output.length() - 3) + "txt"))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static StringBuilder parseFile(NodeList itemList) {

        StringBuilder stringBuilder = new StringBuilder();
        double x = 0, y = 0, z = 0;
        int entry = 0;

        List<InGameObject> inGameObjectsList = new ArrayList<>();

        for (int i = 0; i < itemList.getLength(); i++) {
            Element element = (Element) itemList.item(i);
            NodeList events = element.getElementsByTagName("event");

            for (int j = 0; j < events.getLength(); j++) {
                Element event = (Element) events.item(j);
                String inGameObjectName = event.getAttribute("name");
                NodeList positions = event.getElementsByTagName("pos");

                for (int k = 0; k < positions.getLength(); k++) {
                    Element pos = (Element) positions.item(k);
                    inGameObjectsList.add(new InGameObject(inGameObjectName, pos.getAttribute("x"), pos.getAttribute("y"), pos.getAttribute("a"), pos.getAttribute("z")));

                }

            }
        }

        for (InGameObject inGameObject : inGameObjectsList) {

            if (entry++ == 0) {
                x = inGameObject.x;
                z = inGameObject.z;
                y = inGameObject.y;
                inGameObject.x = 0;
                inGameObject.z = 0;
                inGameObject.y = 0;
            } else {
                inGameObject.x = -(x - inGameObject.x);
                inGameObject.x = Math.round(inGameObject.x * 100.0) / 100.0;
                inGameObject.y = -(y - inGameObject.y);
                inGameObject.y = Math.round(inGameObject.y * 100.0) / 100.0;
                inGameObject.z = -(z - inGameObject.z);
                inGameObject.z = Math.round(inGameObject.z * 100.0) / 100.0;
            }

            stringBuilder.append("<child type=\"")
                    .append(inGameObject.getClassName())
                    .append("\" x=\"")
                    .append(inGameObject.x)
                    .append("\" z=\"")
                    .append(inGameObject.z)
                    .append("\" a=\"")
                    .append(inGameObject.a)
                    .append("\" y=\"")
                    .append(inGameObject.y)
                    .append("\"/>\n");
        }

        return stringBuilder;
    }

    private static Document getDocument(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(path));
        return document;
    }
}
