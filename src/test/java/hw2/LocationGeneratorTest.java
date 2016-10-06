package hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

import citysim9002.Location;
import citysim9002.LocationGenerator;

/**
 * Created by Xynoci on 10/1/16.
 */
public class LocationGeneratorTest {
    private static final double THRESHOLD = 0.01;
    private static final int ITERATIONS = Integer.MAX_VALUE;
    private LocationGenerator locgen = new LocationGenerator((new Random()).nextInt());

    /**
     * FUN-CITY-LOCS. The program shall simulate a City with four locations: The Cathedral of
     * Learning, Squirrel Hill, The Point, and Downtown.
     */
    @Test
    public void areAllLocationsCoveredByCity() {
        HashMap<Location, Boolean> locMap = new HashMap<Location, Boolean>(4) {{
            put(Location.CathedralOfLearning, false);
            put(Location.Downtown, false);
            put(Location.SquirrelHill, false);
            put(Location.ThePoint, false);
        }};
        int loopCount = 0;
        do {
            Location present = locgen.getLocation(locgen.FIRST_VISIT);
            if (!locMap.get(present)) {
                locMap.put(present, true);
            }
        }
        while (loopCount++ == ITERATIONS || locMap.get(Location.CathedralOfLearning) &&
                locMap.get(Location.Downtown) && locMap.get(Location.SquirrelHill) &&
                locMap.get(Location.ThePoint));
        Assert.assertTrue(--loopCount != ITERATIONS);
    }

    /**
     * FUN-ITERATIONS. For each iteration, the program shall randomly select a location for the
     * visitor to visit.  The choices specified in FUN-CITY-LOCS as well as the option to leave the
     * City shall all have equal weight - that is, there is an equal chance that a visitor will have
     * a 20% chance of visiting any location specified in FUN-CITY-LOCS and a 20% chance of leaving
     * the City (except in cases covered under FUN-FIRST-VISIT).
     */
    @Test
    public void isProbabilityTheSameForAll() {
        HashMap<Location, Integer> locMap = new HashMap<Location, Integer>(4) {{
            put(Location.CathedralOfLearning, 0);
            put(Location.Downtown, 0);
            put(Location.SquirrelHill, 0);
            put(Location.ThePoint, 0);
        }};
        int nullCount = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            Location loc = locgen.getLocation(locgen.NON_FIRST_VISIT);
            if (loc == null) {
                nullCount++;
            } else {
                locMap.put(loc, locMap.get(loc) + 1);
            }
        }
        Assert.assertEquals(0.2d, ((double) nullCount / ITERATIONS), THRESHOLD);
        for (Location location : locMap.keySet()) {
            Assert.assertEquals(0.2d, ((double) locMap.get(location) / ITERATIONS), THRESHOLD);
        }
    }

    @Test
    public void ifExistOnFirstVisit() {
        for (int i = 0; i < ITERATIONS; i++) {
            Assert.assertFalse(locgen.getLocation(locgen.FIRST_VISIT) == null);
        }
    }

}