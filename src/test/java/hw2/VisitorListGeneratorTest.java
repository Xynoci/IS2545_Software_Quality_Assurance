package hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import citysim9002.base.Visitor;
import citysim9002.service.VisitorListGenerator;
import javafx.scene.control.RadioMenuItem;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt(any(int.class))).thenReturn(9);
        VisitorListGenerator mockVisitorListGenerator = new VisitorListGenerator(mockRandom.nextInt());

        boolean flag = true;
        Validator validator = new Validator();
        LinkedList<Visitor> visitorList = mockVisitorListGenerator.getVisitorList();
        for (Visitor aVisitor : visitorList) {
            if (!validator.isOneTypeFromTheFourValidator(aVisitor)) {
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    /**
     * FUN-FIVE-VISITORS. Five visitors, numbered 1 through 5, shall traverse the City, one after
     * the other.
     */
    @Test
    public void isThereFiveVisitorsGenerated() throws Exception {
        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt(any(int.class))).thenReturn(6);
        VisitorListGenerator mockVisitorListGenerator = new VisitorListGenerator(mockRandom.nextInt());

        int numberOfVisitorsExpected = 5;
        int numberOfVisitorsGenerated = mockVisitorListGenerator.getVisitorList().size();
        Assert.assertEquals(numberOfVisitorsExpected, numberOfVisitorsGenerated);
    }
}