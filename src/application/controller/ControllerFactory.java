package application.controller;

import application.controller.Discover.DiscoverController;
import application.controller.Favorites.FavoritesController;
import application.controller.Home.HomeController;
import application.controller.Playlists.PlaylistsController;
import application.FavoritesData;

public class ControllerFactory {
	
	public enum Type {
		DISCOVER,
		FAVORITES,
		PLAYLISTS,
		HOME;
	}
	
	public static Controller build(ControllerFactory.Type t, FavoritesData fd) {
		switch (t) {
		case DISCOVER:
			return new DiscoverController(fd);
		case FAVORITES:
			return new FavoritesController(fd);
		case PLAYLISTS:
			return new PlaylistsController(fd);
		case HOME:
			return new HomeController(fd);
		}
		return null;
	}
}
