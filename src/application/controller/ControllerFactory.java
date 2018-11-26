package application.controller;

import application.controller.Discover.DiscoverController;
import application.controller.Favorites.FavoritesController;
import application.controller.Home.HomeController;
import application.controller.Playlists.PlaylistsController;

public class ControllerFactory {
	
	public enum Type {
		DISCOVER,
		FAVORITES,
		PLAYLISTS,
		HOME;
	}
	
	private ControllerFactory() {
	}
	
	public static Controller build(ControllerFactory.Type t) {
		switch (t) {
		case DISCOVER:
			return new DiscoverController();
		case FAVORITES:
			return new FavoritesController();
		case PLAYLISTS:
			return new PlaylistsController();
		case HOME:
			return new HomeController();
		}
		return null;
	}
}
