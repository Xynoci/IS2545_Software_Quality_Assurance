package test.java.hw2;

import main.java.citysim9002.Location;
import main.java.citysim9002.LocationGenerator;
import org.junit.Assert;

import java.util.HashMap;

/**
 * Created by Xynoci on 10/1/16.
 */
public class LocationGeneratorTest {
    private static final double THRESHOLD = 0.01;
    private static final int ITERATIONS = Integer.MAX_VALUE;

    /**
     * FUN-CITY-LOCS.
     * The program shall simulate a City with four locations: The Cathedral of Learning, Squirrel Hill, The Point, and
     * Downtown.
     *
     * @param locgen simulated city
     */
    @org.junit.Test
    public void areAllLocationsCoveredByCity(LocationGenerator locgen) {
        HashMap<Location, Boolean> locMap = new HashMap<Location, Boolean>(4) {{
            put(Location.CathedralOfLearning, false);
            put(Location.Downtown, false);
            put(Location.SquirrelHill, false);
            put(Location.ThePoint, false);
        }};
        int loopCount = 0;
        do {
            locMap.put(locgen.getLocation(), true);
        }
        while (locMap.get(Location.CathedralOfLearning) && locMap.get(Location.Downtown) &&
                locMap.get(Location.SquirrelHill) && locMap.get(Location.ThePoint) || loopCount++ == ITERATIONS);
        Assert.assertTrue(--loopCount != ITERATIONS);
    }

    /**
     * FUN-ITERATIONS. For each iteration, the program shall randomly select a location for the visitor to visit.  The
     * choices specified in FUN-CITY-LOCS as well as the option to leave the City shall all have equal weight - that is,
     * there is an equal chance that a visitor will have a 20% chance of visiting any location specified in
     * FUN-CITY-LOCS and a 20% chance of leaving the City (except in cases covered under FUN-FIRST-VISIT).
     *
     * @param locgen
     */
    public void isProbabilityTheSameForAll(LocationGenerator locgen) {
        HashMap<Location, Integer> locMap = new HashMap<Location, Integer>(4) {{
            put(Location.CathedralOfLearning, 0);
            put(Location.Downtown, 0);
            put(Location.SquirrelHill, 0);
            put(Location.ThePoint, 0);
        }};
        int nullCount = 0, locCount = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Location loc = locgen.getLocationOrLeave();
            if (loc == null) {
                nullCount++;
            } else {
                locMap.put(loc, locMap.get(loc) + 1);
            }
        }
        boolean locationFlag = false,
                nullFlag = Math.abs(((double) nullCount / ITERATIONS) - 0.2d) <= THRESHOLD;
        for (Location location : locMap.keySet()) {
            locCount++;
            if (Math.abs(((double) locMap.get(location) / ITERATIONS) - 0.2d) > THRESHOLD) {
                break;
            }
        }
        locationFlag = (locCount == 4);
        Assert.assertTrue(locationFlag && nullFlag);
    }
}