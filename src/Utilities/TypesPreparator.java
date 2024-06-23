package Utilities;

import java.nio.file.Path;

public class TypesPreparator {

    String typeTemplate = """
            <type name="">
                    <nominal>2</nominal>
                    <lifetime>14400</lifetime>
                    <restock>0</restock>
                    <min>1</min>
                    <quantmin>-1</quantmin>
                    <quantmax>-1</quantmax>
                    <cost>100</cost>
                    <flags count_in_cargo="0" count_in_hoarder="0" count_in_map="1" count_in_player="0" crafted="0" deloot="0"/>
                    <category name="weapons"/>
            		<usage name="ContaminatedArea"/>
                </type>
                """;

    public void prepareTypesForFile(Path path) {

    }
}
