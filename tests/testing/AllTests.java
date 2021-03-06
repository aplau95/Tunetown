package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import application.ApplicationTestSuite;
import application.AudioTestSuite;
import application.controller.ControllerTestSuite;
import application.guis.ApplicationGUISTestSuite;

//Created by: Alex Gata
@RunWith(Suite.class)
@SuiteClasses({
	ApplicationTestSuite.class,
	ControllerTestSuite.class,
	ApplicationGUISTestSuite.class,
	AudioTestSuite.class
})
public class AllTests {

}
