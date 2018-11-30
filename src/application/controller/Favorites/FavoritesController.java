package application.controller.Favorites;

import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
import application.FavoritesData;
import application.TrackData;
import application.controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.CustomTextField;


public class FavoritesController implements Controller {

	FavoritesData fd;
	Button numLikedB;

	public FavoritesController(FavoritesData fd) {
		this.fd = fd;
		try {
			this.fd = fd;
		} catch (Exception e) {
			System.out.println("Failed to connect to spotify");
		}
	}

	@Override
	public Node buildScene() {

		VBox root = new VBox();
		root.setId("root");

		Region regionLeft = new Region();
		regionLeft.setPrefWidth(40);
		Region regionCenter = new Region();
		HBox.setHgrow(regionCenter, Priority.ALWAYS);
		Region regionRight = new Region();
		regionRight.setPrefWidth(40);

		HBox topBar = new HBox();
		topBar.setId("topBar");
		Label discoverL = new Label("Favorites");
		discoverL.setId("favoritesLabel");
		numLikedB = new Button("53 Songs");
		numLikedB.setId("numLikedButton");
		topBar.getChildren().addAll(regionLeft, discoverL, regionCenter, numLikedB, regionRight);

		CustomTextField searchBar = new CustomTextField();
		searchBar.setLeft(new ImageView() {{setId("searchImage");}});
		searchBar.setId("searchBar");

		root.getChildren().addAll(topBar, searchBar);
		root.setAlignment(Pos.TOP_CENTER);
		root.getStylesheets().add(getClass().getResource("favorites.css").toExternalForm());

		return root;
	}

	
	/**
	 * Called before the page is shown
	 */
	@Override
	public void beforeShow() {
		//Implement if necessary
	}

	/**
	 * Called after the page is shown. It's your time to shine!
	 */
	@Override
	public void afterShow() {

		// try {

		// 	player = new LoopingAudioPlayer(new URL(currentTrack.getPreviewUrl()),this::onTime);
		// 	executorService.submit(player);

		// 	albumI.setImage(new Image(currentTrack.getImageUrl()));
		// 	songNameL.setText(currentTrack.getName());
		// 	artistL.setText(currentTrack.getArtists());
		// 	genreB.setText(currentTrack.getGenre());

		// } catch(MalformedURLException e) {
		// 	System.out.println("Error " + e);
		// }
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
		//Implement if necessary
	}

}
