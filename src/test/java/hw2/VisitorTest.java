package hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import citysim9002.Blogger;
import citysim9002.BusinessPerson;
import citysim9002.Location;
import citysim9002.LocationGenerator;
import citysim9002.Professor;
import citysim9002.Student;
import citysim9002.Visitor;
import citysim9002.VisitorListGenerator;

/**
 * Created by Xynoci on 10/1/16.
 */
public class VisitorTest {
    /**
     * FUN-PREFERENCES. A Student shall like Squirrel Hill, Downtown, and The Point, and dislike The
     * Cathedral of Learning.  A Business Person shall like Squirrel Hill and Downtown, and dislike
     * all other locations. A Professor shall like all locations.  A Blogger shall dislike all
     * locations.
     */
    @Test
    public void isPreferencesCorrect() throws Exception {
        Student student = new Student();
        BusinessPerson businessPerson = new BusinessPerson();
        Professor professor = new Professor();
        Blogger blogger = new Blogger();

        boolean studentFlag = !student.prefersLocation(Location.CathedralOfLearning) &&
                student.prefersLocation(Location.Downtown) &&
                student.prefersLocation(Location.SquirrelHill) &&
                student.prefersLocation(Location.ThePoint);
        boolean businessPersonFlag = !businessPerson.prefersLocation(Location.CathedralOfLearning) &&
                businessPerson.prefersLocation(Location.Downtown) &&
                businessPerson.prefersLocation(Location.SquirrelHill) &&
                !businessPerson.prefersLocation(Location.ThePoint);
        boolean professorFlag = professor.prefersLocation(Location.CathedralOfLearning) &&
                professor.prefersLocation(Location.Downtown) &&
                professor.prefersLocation(Location.SquirrelHill) &&
                professor.prefersLocation(Location.ThePoint);
        boolean bloggerFlag = !blogger.prefersLocation(Location.CathedralOfLearning) &&
                !blogger.prefersLocation(Location.Downtown) &&
                !blogger.prefersLocation(Location.SquirrelHill) &&
                !blogger.prefersLocation(Location.ThePoint);

        Assert.assertTrue(studentFlag && businessPersonFlag && professorFlag && bloggerFlag);
    }

}