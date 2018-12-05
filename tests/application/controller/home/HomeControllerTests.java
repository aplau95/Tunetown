package application.controller.home;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.FavoritesData;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;

public class HomeControllerTests {
	
	@BeforeAll
	public static void init() {
		JFXPanel jp = new JFXPanel();
		jp.validate();
	}

	@Test
	void testBuildScene() {
		HomeController hc = new HomeController(new FavoritesData());
		
		assertThat(hc.buildScene(), instanceOf(Node.class));
	}

}
