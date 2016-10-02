package test.java.hw2;

import main.java.citysim9002.Visitor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Xynoci on 10/1/16.
 */
public class VisitorTest {
    private static final String[] TYPE_LIST = new String[]{"Student", "Professor", "BusinessPerson", "Blogger"};

    @org.junit.Test
    /**
     * FUN-VISITOR.
     * Each Visitor shall be of one of four types: a Student, a Professor, a Business Person, or a Blogger.
     *
     * @param visitor a visitor object.
     */
    public void isOneTypeFromTheFour(Visitor visitor) throws Exception {
        boolean isOneTypeFormTheFour = false;
        switch (visitor.getClass().getEnclosingClass().getName()) {
            case "Student":
                if (visitor.toString().equals("Student")) {
                    isOneTypeFormTheFour = true;
                }
                break;
            case "Professor":
                if (visitor.toString().equals("Professor")) {
                    isOneTypeFormTheFour = true;
                }
                break;
            case "BusinessPerson":
                if (visitor.toString().equals("BusinessPerson")) {
                    isOneTypeFormTheFour = true;
                }
                break;
            case "Blogger":
                if (visitor.toString().equals("Blogger")) {
                    isOneTypeFormTheFour = true;
                }
                break;
            default:
                break;
        }
        Assert.assertTrue(isOneTypeFormTheFour);
    }

}