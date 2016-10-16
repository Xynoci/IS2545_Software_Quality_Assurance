package citysim9002.bean;

import citysim9002.base.Location;
import citysim9002.base.Visitor;

public class BusinessPerson extends Visitor {
    public BusinessPerson() {super(); }

    public boolean prefersLocation(Location location) {
        return location == Location.SquirrelHill || location == Location.Downtown;
    }

    public String toString() {
        return new String("Business Person");
    }
}