package application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	FavoritesDataTests.class,
	TrackDataTests.class
	
})
public class ApplicationTestSuite {

}