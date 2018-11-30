package application.controller.Discover;

import application.Logger;
import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
import application.TrackData;
import application.controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiscoverController implements Controller {

	VBox root;
	HBox actionButtons;
	HBox timeLabels;

	ImageView albumI;
	ProgressBar pb;
	Label songNameL;
	Label artistL;
	Label timePassedL;
	Label timeLeftL;
	Button dislikeB;
	Button likeB;

	SpotifyAccessor spotify;
	TrackData currentTrack;
	LoopingAudioPlayer player;

	ExecutorService executorService = Executors.newFixedThreadPool(2);

	public DiscoverController() {
		try {
			spotify = new SpotifyAccessor();
			currentTrack = spotify.getNextRecommendation();
		} catch (Exception e) {
			Logger.getInstance().Log("Failed to connect to spotify");
		}
	}


	@Override
	public Node buildScene() {

		root = new VBox();
		root.setId("root");

		Region regionLeft = new Region();
		regionLeft.setPrefWidth(40);
		Region regionCenter = new Region();
		HBox.setHgrow(regionCenter, Priority.ALWAYS);
		Region regionRight = new Region();
		regionRight.setPrefWidth(40);

		HBox topBar = new HBox();
		topBar.setId("topBar");
		Label discoverL = new Label("Discover");
		discoverL.setId("discoverLabel");
		Button genreB = new Button("Pop");
		genreB.setId("genreButton");
		topBar.getChildren().addAll(regionLeft, discoverL, regionCenter, genreB, regionRight);

		albumI = new ImageView();
		albumI.setFitWidth(300);
		albumI.setPreserveRatio(true);

		albumI.setImage(new Image(currentTrack.getImageUrl()));
		pb = new ProgressBar(0.0);
		pb.setPrefWidth(300);
		pb.setId("progressBar");

		timePassedL = new Label("0:00");
		timeLeftL = new Label("-30:00");
		Region region1 = new Region();
		HBox.setHgrow(region1, Priority.ALWAYS);

		timeLabels = new HBox();
		timeLabels.setId("timeLabels");
		timeLabels.getChildren().addAll(timePassedL, region1, timeLeftL);
		timeLabels.setMaxWidth(300);

		songNameL = new Label(currentTrack.getName());
		songNameL.setId("songName");
		artistL = new Label(currentTrack.getArtists());
		artistL.setId("artistName");

		actionButtons = new HBox();
		actionButtons.setId("actionButtons");

		dislikeB = new Button();
		dislikeB.setId("dislike");
		likeB = new Button();
		likeB.setId("like");

		dislikeB.setOnAction(e -> onNext());
		likeB.setOnAction(e -> onNext());

		actionButtons.getChildren().addAll(dislikeB, likeB);
		actionButtons.setAlignment(Pos.CENTER);

		root.getChildren().addAll(topBar, albumI, pb, timeLabels, songNameL, artistL, actionButtons);
		root.setAlignment(Pos.TOP_CENTER);
		root.getStylesheets().add(getClass().getResource("discover.css").toExternalForm());

		return root;
	}

	public void onNext() {
		player.stop();

		try {

			currentTrack = spotify.getNextRecommendation();
			player = new LoopingAudioPlayer(new URL(currentTrack.getPreviewUrl()),this::onTime);
			executorService.submit(player);

			albumI.setImage(new Image(currentTrack.getImageUrl()));
			songNameL.setText(currentTrack.getName());
			artistL.setText(currentTrack.getArtists());

		} catch(MalformedURLException ex) {
	    	Logger.getInstance().Log("Error");
		}
	}

	public Void onTime(Double time) {
		Platform.runLater(() -> {
			pb.setProgress(time/30.0);
			timePassedL.setText("0:" + String.format("%02d", time.intValue()));
			timeLeftL.setText("-0:" + String.format("%02d", 30-time.intValue()));
		});
		return null;
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

		try {

			player = new LoopingAudioPlayer(new URL(currentTrack.getPreviewUrl()),this::onTime);
			executorService.submit(player);

			albumI.setImage(new Image(currentTrack.getImageUrl()));
			songNameL.setText(currentTrack.getName());
			artistL.setText(currentTrack.getArtists());

		} catch(MalformedURLException e) {
	    	Logger.getInstance().Log("Error: " + e.getMessage());
		}
	}

	/**
	 * Called before the page is hidden. Use this to cleanup.
	 * Don't do anything that will block the main UI thread
	 * or else user will see a delay switching to the next tab
	 */
	@Override
	public void beforeHide() {
		if(player != null) {
			player.stop();
		}
	}

	/**
	 * Called after page is hidden. Not much to do here probably.
	 */
	@Override
	public void afterHide() {
		//Implement if necessary
	}

}
