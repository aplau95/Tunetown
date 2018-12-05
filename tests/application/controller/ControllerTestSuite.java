package application.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import application.controller.discover.DiscoverControllerTests;
import application.controller.favorites.FavoritesControllerTests;
import application.controller.home.HomeControllerTests;
import application.controller.settings.SettingsControllerTests;

@RunWith(Suite.class)
@SuiteClasses({
	ControllerFactoryTests.class,
	DiscoverControllerTests.class,
	FavoritesControllerTests.class,
	HomeControllerTests.class,
	SettingsControllerTests.class
})
public class ControllerTestSuite {

}
