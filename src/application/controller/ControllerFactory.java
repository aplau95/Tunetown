package application.controller;

import application.controller.discover.DiscoverController;
import application.controller.favorites.FavoritesController;
import application.controller.home.HomeController;
import application.controller.settings.SettingsController;
import application.FavoritesData;

public class ControllerFactory {
	
	public enum Type {
		DISCOVER,
		FAVORITES,
		SETTINGS,
		HOME;
	}
	
	public static Controller build(ControllerFactory.Type t, FavoritesData fd) {
		switch (t) {
		case DISCOVER:
			return new DiscoverController(fd);
		case FAVORITES:
			return new FavoritesController(fd);
		case SETTINGS:
			return new SettingsController(fd);
		case HOME:
			return new HomeController(fd);
		}
		return null;
	}
}
