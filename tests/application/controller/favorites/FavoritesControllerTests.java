package application.controller.favorites;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.FavoritesData;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;

class FavoritesControllerTests {
	
	@BeforeAll
	public static void init() {
		JFXPanel jp = new JFXPanel();
		jp.validate();
	}

	@Test
	void testBuildScene() {
		FavoritesController fc = new FavoritesController(new FavoritesData());
		
		assertThat(fc.buildScene(), instanceOf(Node.class));
	}

}
