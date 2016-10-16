package hw2;

import citysim9002.base.Visitor;

/**
 * Created by Xynoci on 10/5/16.
 */
class Validator {

    boolean isOneTypeFromTheFourValidator(Visitor visitor) throws Exception {
        boolean isOneTypeFormTheFour = false;
        switch (visitor.getClass().getSimpleName()) {
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
                if (visitor.toString().equals("Business Person")) {
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
        return (isOneTypeFormTheFour);
    }

}
