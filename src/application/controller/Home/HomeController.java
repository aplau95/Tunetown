package application.controller.Home;

import application.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeController implements Controller {

	/**
	 * Called once at app startup to build initial scene elements
	 */
	@Override
	public Node buildScene() {
		Label lbl = new Label("HOME");

		VBox root = new VBox();

		root.getChildren().add(lbl);
		root.setAlignment(Pos.CENTER);

		return root;
	}

	/**
	 * Called before the page is shown. Use this to prepare the page before its shown
	 * Don't do anything that will block the main UI thread
	 * or else user will see a delay switching to the next tab
	 */
	@Override
	public void beforeShow() {

	}

	/**
	 * Called after the page is shown. It's your time to shine!
	 */
	@Override
	public void afterShow() {

	}

	/**
	 * Called before the page is hidden. Use this to cleanup.
	 * Don't do anything that will block the main UI thread
	 * or else user will see a delay switching to the next tab
	 */
	@Override
	public void beforeHide() {

	}

	/**
	 * Called after page is hidden. Not much to do here probably.
	 */
	@Override
	public void afterHide() {

	}
}
