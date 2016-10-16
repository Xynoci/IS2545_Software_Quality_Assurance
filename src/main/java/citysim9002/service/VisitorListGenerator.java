package citysim9002.service;

import java.util.LinkedList;

import citysim9002.base.Generator;
import citysim9002.base.Visitor;
import citysim9002.bean.Blogger;
import citysim9002.bean.BusinessPerson;
import citysim9002.bean.Professor;
import citysim9002.bean.Student;

/*
 *The simple but elegant **towntest** is forked from
 * [Bret Gourdie](https://github.com/bretgourdie/towntest). It is modified here for testing course.
 */
public class VisitorListGenerator extends Generator {

    private final int STUDENT = 0;
    private final int PROFESSOR = 1;
    private final int BUSINESS_PERSON = 2;
    private final int BLOGGER = 3;
    private final int NUMBER_OF_VISITOR_TYPES = 4;

    public VisitorListGenerator(int seed) {
        super(seed);
    }

    public LinkedList<Visitor> getVisitorList() {
        int numberOfVisitors = 5;
        LinkedList<Visitor> visitorList = new LinkedList<Visitor>();

        for (int i = 0; i < numberOfVisitors; i++) {
            int nextVisitor = this.nextInt(NUMBER_OF_VISITOR_TYPES);

            if (nextVisitor == STUDENT) {
                visitorList.add(new Student());
            } else if (nextVisitor == PROFESSOR) {
                visitorList.add(new Professor());
            } else if (nextVisitor == BUSINESS_PERSON) {
                visitorList.add(new BusinessPerson());
            } else if (nextVisitor == BLOGGER) {
                visitorList.add(new Blogger());
            } else {
                System.err.println("Internal error.");
            }
        }
        return visitorList;
    }
}