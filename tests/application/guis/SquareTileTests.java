package application.guis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class SquareTileTests {
	
	@BeforeAll
	public static void init() {
		JFXPanel jp = new JFXPanel();
		jp.validate();
	}

	@Test
	void testSqureTile() {
		SquareTile st = new SquareTile("hello", "world", "hello world");
		assertTrue(st.b1Label.getText().equals("hello") && st.b1Sublabel.getText().equals("world") && st.b1Sublabel2.getText().equals("hello world"));
		
	}

}
