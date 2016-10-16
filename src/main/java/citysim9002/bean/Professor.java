package citysim9002.bean;

import citysim9002.base.Location;
import citysim9002.base.Visitor;

public class Professor extends Visitor {
	public Professor() { super(); }
	
	public boolean prefersLocation(Location location){
		return true;
	}
	
	public String toString(){
		return new String("Professor");
	}
}