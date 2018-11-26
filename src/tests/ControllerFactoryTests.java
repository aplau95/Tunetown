package tests;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import application.controller.ControllerFactory;
import application.controller.Discover.DiscoverController;
import application.controller.Favorites.FavoritesController;
import application.controller.Home.HomeController;
import application.controller.Playlists.PlaylistsController;
import application.controller.Controller;

class ControllerFactoryTests {

	@Test
	void testBuildHomeController() {
		Controller c = ControllerFactory.build(ControllerFactory.Type.HOME);
		assertThat(c, instanceOf(HomeController.class));
	}
	
	@Test
	void testBuildDiscoverController() {
		Controller c = ControllerFactory.build(ControllerFactory.Type.DISCOVER);
		assertThat(c, instanceOf(DiscoverController.class));
	}
	
	@Test
	void testBuildFavoritesController() {
		Controller c = ControllerFactory.build(ControllerFactory.Type.FAVORITES);
		assertThat(c, instanceOf(FavoritesController.class));
	}
	
	@Test
	void testBuildPlaylistsController() {
		Controller c = ControllerFactory.build(ControllerFactory.Type.PLAYLISTS);
		assertThat(c, instanceOf(PlaylistsController.class));
	}

}
