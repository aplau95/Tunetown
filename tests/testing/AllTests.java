package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import application.ApplicationTestSuite;
import application.controller.ControllerTestSuite;
import application.guis.ApplicationGUISTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
	ApplicationTestSuite.class,
	ControllerTestSuite.class,
	ApplicationGUISTestSuite.class
})
public class AllTests {

}
