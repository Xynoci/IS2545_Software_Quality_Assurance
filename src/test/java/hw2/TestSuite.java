package hw2;

import java.io.IOException;

import citysim9002.LocationGenerator;

/**
 * Created by Xynoci on 10/1/16.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        VisitorListGeneratorTest.class,
        LocationGeneratorTest.class,
        VisitorTest.class
})

public class TestSuite {
}
