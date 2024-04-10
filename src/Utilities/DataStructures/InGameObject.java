package Utilities.DataStructures;

public class InGameObject {
    private String className;
    public float x;
    public float y;
    public float a;
    public float z;

    public InGameObject(String className, String x, String y, String a, String z) {
        this.className = className;
        this.x = Float.parseFloat(x);
        this.y = Float.parseFloat(y);
        this.a = Float.parseFloat(a);
        this.z = Float.parseFloat(z);
    }

    public String getObjectClassName() {

        return className;
    }

    @Override
    public String toString() {
        return className;
    }
}
