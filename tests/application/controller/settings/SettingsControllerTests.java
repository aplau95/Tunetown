package application.controller.settings;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import application.FavoritesData;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;

class SettingsControllerTests {
	
	@BeforeAll
	public static void init() {
		JFXPanel jp = new JFXPanel();
		jp.validate();
	}
	
	@Test
	void testBuildScene() {
		SettingsController sc = new SettingsController(new FavoritesData());

		assertThat(sc.buildScene(), instanceOf(Node.class));
	}

}
