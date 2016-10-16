package citysim9002.entry;

import java.util.LinkedList;

import citysim9002.base.Location;
import citysim9002.base.Visitor;
import citysim9002.service.LocationGenerator;
import citysim9002.service.VisitorListGenerator;

/*
 *The simple but elegant **towntest** is forked from
 * [Bret Gourdie](https://github.com/bretgourdie/towntest). It is modified here for testing course.
 */
public class CitySim9002 {
    public static void main(String[] args) {
        int seed = 0;
        if (argumentValidate(args)) {
            seed = Integer.parseInt(args[0]);
        } else return;

        System.out.println("Welcome to CitySim! Your seed is " + Integer.toString(seed) + ".");
        VisitorListGenerator visgen = new VisitorListGenerator(seed);
        LocationGenerator locgen = new LocationGenerator(seed);
        travelTheCity(visgen, locgen);
    }

    private static boolean argumentValidate(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter one integer argument, seed");
            return false;
        }
        try {
            int seed = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            System.out.println("Please enter one integer argument, seed");
            return false;
        }
        return true;
    }

    private static void travelTheCity(VisitorListGenerator visgen, LocationGenerator locgen) {
        int visitorNumber = 0;
        LinkedList<Visitor> vislist = visgen.getVisitorList();
        for (Visitor visitor : vislist) {
            String numberedPrefix = "Visitor " + Integer.toString(++visitorNumber);
            System.out.println(numberedPrefix + " is a " + visitor.toString() + ".");

            int visitTurns = locgen.FIRST_VISIT;
            Location currentLocation = null;
            do {
                currentLocation = locgen.getLocation(visitTurns++);
                if (currentLocation == null) {
                    System.out.println(numberedPrefix + " has left the city.\n***");
                } else {
                    String notLiked = !visitor.prefersLocation(currentLocation) ? " not" : "";
                    String locationNameString = locgen.getLocationNameString(currentLocation);
                    System.out.println(numberedPrefix + " is going to " + locationNameString + "!");
                    System.out.println(numberedPrefix + " did" + notLiked + " like " + locationNameString + ".");
                }
            } while (currentLocation != null);
        }
    }

}