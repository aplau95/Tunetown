package application.controller;

import org.junit.jupiter.api.Test;

import application.FavoritesData;
import application.controller.Discover.DiscoverController;
import application.controller.Favorites.FavoritesController;
import application.controller.Home.HomeController;
import application.controller.Settings.SettingsController;
import application.FavoritesData;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

class ControllerFactoryTests {

	@Test
	void testBuildHomeController() {
		Controller c = ControllerFactory.build(ControllerFactory.Type.HOME, new FavoritesData());
		assertThat(c, instanceOf(HomeController.class));
	}
	
	@Test
	void testBuildDiscoverController() {
		Controller c = ControllerFactory.build(ControllerFactory.Type.DISCOVER, new FavoritesData());
		assertThat(c, instanceOf(DiscoverController.class));
	}
	
	@Test
	void testBuildFavoritesController() {
		Controller c = ControllerFactory.build(ControllerFactory.Type.FAVORITES, new FavoritesData());
		assertThat(c, instanceOf(FavoritesController.class));
	}

	@Test
	void testBuildSettingsController() {
		Controller c = ControllerFactory.build(ControllerFactory.Type.SETTINGS, new FavoritesData());
		assertThat(c, instanceOf(SettingsController.class));
	}

}
