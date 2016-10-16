package citysim9002.bean;

import citysim9002.base.Location;
import citysim9002.base.Visitor;

public class Student extends Visitor {
    public Student() { super(); }

    public boolean prefersLocation(Location location) {
        return location != Location.CathedralOfLearning;
    }

    public String toString() {
        return new String("Student");
    }
}