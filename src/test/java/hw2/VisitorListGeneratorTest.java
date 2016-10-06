package hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import citysim9002.Visitor;
import citysim9002.VisitorListGenerator;

/**
 * Created by Xynoci on 10/5/16.
 */
public class VisitorListGeneratorTest {
    private VisitorListGenerator visgen = new VisitorListGenerator((new Random()).nextInt());

    /**
     * FUN-VISITOR. Each Visitor shall be of one of four types: a Student, a Professor, a Business
     * Person, or a Blogger.
     */
    @Test
    public void isOneTypeFromTheFour() throws Exception {
        boolean flag = true;
        Validator validator = new Validator();
        LinkedList<Visitor> vislist = visgen.getVisitorList();
        for (Visitor aVisitor : vislist) {
            if (!validator.isOneTypeFromTheFourValidator(aVisitor)) {
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void isThereFiveVisitorsGenerated() throws Exception {
        int numberOfVisitorsExpected = 5;
        int numberOfVisitorsGenerated = visgen.getVisitorList().size();
        Assert.assertEquals(numberOfVisitorsExpected, numberOfVisitorsGenerated);
    }
}