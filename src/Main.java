import GraphicInterfaces.ToolPicker;
import Utilities.PropertiesReader;

public class Main {
    public static void main(String[] args) {

        new ToolPicker("Choose your tool" + PropertiesReader.getVersion(true));

    }
}