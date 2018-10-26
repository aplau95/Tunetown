package application.Controller;

import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
import application.TrackData;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiscoverController implements Controller {

	VBox root;

	ImageView albumI;
	ProgressBar pb;
	Label songNameL;
	Label artistL;
	Button nextB;

	SpotifyAccessor spotify;
	TrackData currentTrack;
	LoopingAudioPlayer player;

	ExecutorService executorService = Executors.newFixedThreadPool(2);

	public DiscoverController() {
		try {
			spotify = new SpotifyAccessor();
			currentTrack = spotify.getNextRecommendation();
		} catch (Exception e) {
			System.out.println("Failed to connect to spotify");
		}
	}


	@Override
	public Node buildScene() {

		root = new VBox();

		albumI = new ImageView();
		albumI.setFitHeight(300);
		albumI.setPreserveRatio(true);

		albumI.setImage(new Image(currentTrack.getImageUrl()));
		pb = new ProgressBar(0.0);
		songNameL = new Label(currentTrack.getName());
		artistL = new Label(currentTrack.getArtists());

		nextB = new Button("Next");
		nextB.setOnAction(this::onNext);

		root.getChildren().addAll(albumI, songNameL, pb, artistL, nextB);
		root.setAlignment(Pos.CENTER);

		return root;
	}

	public void onNext(ActionEvent e) {
		player.stop();

		try {

			currentTrack = spotify.getNextRecommendation();
			player = new LoopingAudioPlayer(new URL(currentTrack.getPreviewUrl()),this::onTime);
			executorService.submit(player);

			albumI.setImage(new Image(currentTrack.getImageUrl()));
			songNameL.setText(currentTrack.getName());
			artistL.setText(currentTrack.getArtists());

		} catch(MalformedURLException ex) {
			System.out.println("Error");
		}
	}

	public Void onTime(Double time) {
		pb.setProgress(time/30.0);

		return null;
	}

	@Override
	public void beforeShow() {

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

	}

}
