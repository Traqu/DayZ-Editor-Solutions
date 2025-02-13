package Utilities;

import java.nio.file.Path;

public class TypesPreparator {

    String typeTemplate =
            "<type name=\"\">\n" +
                    "    <nominal>2</nominal>\n" +
                    "    <lifetime>14400</lifetime>\n" +
                    "    <restock>0</restock>\n" +
                    "    <min>1</min>\n" +
                    "    <quantmin>-1</quantmin>\n" +
                    "    <quantmax>-1</quantmax>\n" +
                    "    <cost>100</cost>\n" +
                    "    <flags count_in_cargo=\"0\" count_in_hoarder=\"0\" count_in_map=\"1\" count_in_player=\"0\" crafted=\"0\" deloot=\"0\"/>\n" +
                    "    <category name=\"weapons\"/>\n" +
                    "    <usage name=\"ContaminatedArea\"/>\n" +
                    "</type>\n";

    public void prepareTypesForFile(Path path) {

    }
}
