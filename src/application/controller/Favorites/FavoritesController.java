package application.controller.Favorites;

import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
import application.FavoritesData;
import application.TrackData;
import application.controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;



public class FavoritesController implements Controller {

	FavoritesData fd;

	VBox root;
	// HBox actionButtons;
	// HBox timeLabels;

	// ImageView albumI;
	// ProgressBar pb;
	// Label songNameL;
	// Label artistL;
	// Label timePassedL;
	// Label timeLeftL;
	// Button dislikeB;
	// Button likeB;

	// SpotifyAccessor spotify;
	// TrackData currentTrack;
	// LoopingAudioPlayer player;
	// FavoritesData favoritesData;

	ExecutorService executorService = Executors.newFixedThreadPool(2);


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

		root = new VBox();
		root.setId("root");

		Region regionCenter = new Region();
		HBox.setHgrow(regionCenter, Priority.ALWAYS);

		HBox topBar = new HBox();
		topBar.setId("topBar");
		Label favoritesL = new Label("TuneTown");
		favoritesL.setId("favoritesLabel");
		Button trackCountB = new Button("51 songs");
		trackCountB.setId("trackCountButton");
		topBar.getChildren().addAll(favoritesL, regionCenter, trackCountB);

		// albumI = new ImageView();
		// albumI.setFitWidth(300);
		// albumI.setPreserveRatio(true);

		// albumI.setImage(new Image(currentTrack.getImageUrl()));
		// pb = new ProgressBar(0.0);
		// pb.setPrefWidth(300);
		// pb.setId("progressBar");

		// timePassedL = new Label("0:00");
		// timeLeftL = new Label("-30:00");
		// Region region1 = new Region();
		// HBox.setHgrow(region1, Priority.ALWAYS);

		// timeLabels = new HBox();
		// timeLabels.setId("timeLabels");
		// timeLabels.getChildren().addAll(timePassedL, region1, timeLeftL);
		// timeLabels.setMaxWidth(300);

		// songNameL = new Label(currentTrack.getName());
		// songNameL.setId("songName");
		// artistL = new Label(currentTrack.getArtists());
		// artistL.setId("artistName");

		// actionButtons = new HBox();
		// actionButtons.setId("actionButtons");

		// dislikeB = new Button();
		// dislikeB.setId("dislike");
		// likeB = new Button();
		// likeB.setId("like");

		// dislikeB.setOnAction(e -> onDislike());
		// likeB.setOnAction(e -> onLike());

		// actionButtons.getChildren().addAll(dislikeB, likeB);
		// actionButtons.setAlignment(Pos.CENTER);

		root.getChildren().addAll(
			topBar
			);
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
