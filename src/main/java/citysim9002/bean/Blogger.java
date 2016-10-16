package citysim9002.bean;

import citysim9002.base.Location;
import citysim9002.base.Visitor;

public class Blogger extends Visitor {
    public Blogger() {super(); }

    public boolean prefersLocation(Location location) {
        return false;
    }

    public String toString() {
        return new String("Blogger");
    }
}