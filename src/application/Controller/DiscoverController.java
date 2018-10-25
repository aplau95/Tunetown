package application.Controller;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DiscoverController implements Controller {

	/**
	 * Called once at app startup to build initial scene elements
	 */
	@Override
	public Node buildScene() {
		Label lbl = new Label("DISCOVER");

		VBox root = new VBox();

		root.getChildren().add(lbl);
		root.setAlignment(Pos.CENTER);

		new Thread(() -> {

			try {

//Uncomment to play audio for 5 seconds
//				SpotifyAccessor spotify = new SpotifyAccessor("160b683f23e946ed8000ec438e36890a",
//						"efa3a5718c6a49acb3828305c3a01c7b");
//
//				String previewUrl = spotify.getSampleTrack().getString("preview_url");
//
//				LoopingAudioPlayer player = new LoopingAudioPlayer(new URL(previewUrl));
//
//				//Start Playing
//				Thread audioThread = new Thread(player);
//				audioThread.start();
//
//				//Wait 5 seconds
//				Thread.sleep(5000);
//
//				//Stop and after it stops print exited
//				player.stop(() -> {
//					System.out.println("Exited");
//				});

			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();

		return root;
	}

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
