package application.controller.discover;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.FavoritesData;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;

class DiscoverControllerTests {
	
	@BeforeAll
	public static void init() {
		JFXPanel jp = new JFXPanel();
		jp.validate();
	}

	@Test
	void testBuildScene() {
		DiscoverController dc = new DiscoverController(new FavoritesData());
		
		assertThat(dc.buildScene(), instanceOf(Node.class));
	}

}
