package application.guis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;

class TileFragmentTests {
	
	@BeforeAll
	public static void init() {
		JFXPanel jp = new JFXPanel();
		jp.validate();
	}

	@Test
	void testCreateRecentFaveTile() {
		Button b = TileFragment.createRecentFaveTile("https://via.placeholder.com/150", "this_is_a_title", "this_is_an_album", "this_is_an_artist");
		assertTrue(b.getId().equals("RecentFaveTile"));
	}

}
