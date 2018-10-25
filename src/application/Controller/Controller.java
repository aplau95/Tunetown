package application.Controller;

import javafx.scene.Node;

public interface Controller {

	/**
	 * Called once at app startup to build initial scene elements
	 */
	public Node buildScene();

	/**
	 * Called before the page is shown. Use this to prepare the page before its shown
	 * Don't do anything that will block the main UI thread
	 * or else user will see a delay switching to the next tab
	 */
	public void beforeShow();

	/**
	 * Called after the page is shown. It's your time to shine!
	 */
	public void afterShow();

	/**
	 * Called before the page is hidden. Use this to cleanup.
	 * Don't do anything that will block the main UI thread
	 * or else user will see a delay switching to the next tab
	 */
	public void beforeHide();

	/**
	 * Called after page is hidden. Not much to do here probably.
	 */
	public void afterHide();

}
