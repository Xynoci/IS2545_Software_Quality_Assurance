package citysim9002;

/*
 *The simple but elegant **towntest** is forked from
 * [Bret Gourdie](https://github.com/bretgourdie/towntest). It is modified here for testing course.
 */
public class LocationGenerator extends Generator {
    public final int FIRST_VISIT = 0;
    public final int NON_FIRST_VISIT = 1;

    private Location locationList[];

    public LocationGenerator(int seed) {
        super(seed);

        locationList = new Location[5];
        locationList[0] = Location.CathedralOfLearning;
        locationList[1] = Location.SquirrelHill;
        locationList[2] = Location.ThePoint;
        locationList[3] = Location.Downtown;
        locationList[4] = null; // used to indicate "leave"
    }

    public Location getLocation(int visitTurns) {
        int upperBound = 0;
        if (visitTurns == FIRST_VISIT) {
            upperBound = this.locationList.length - 1;
        } else {
            upperBound = this.locationList.length;
        }
        return locationList[this.nextInt(upperBound)];
    }

    String getLocationNameString(Location location) {
        switch (location) {
            case CathedralOfLearning:
                return "The Cathedral of Learning";
            case SquirrelHill:
                return "Squirrel Hill";
            case ThePoint:
                return "The Point";
            case Downtown:
                return "Downtown";
        }
        return "";
    }

}