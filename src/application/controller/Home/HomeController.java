package application.controller.Home;

import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
import application.guis.TileFragment;
import application.guis.TrackFragment;
import application.FavoritesData;
import application.TrackData;
import application.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeController implements Controller {

	FavoritesData fd;

	TrackFragment track1;
	TrackFragment track2;
	TrackFragment track3;

	Label numTracks;
	HBox topBar;
	HBox tileList;
	VBox root;

	Button settingsB;

	ExecutorService executorService = Executors.newFixedThreadPool(2);

	public HomeController(FavoritesData fd) {
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

		topBar = new HBox();
		topBar.setId("topBar");
		Label tuneTownL = new Label("TuneTown");
		tuneTownL.setId("headerLabel");
		settingsB = new Button();
		settingsB.setId("settingsButton");
		topBar.getChildren().addAll(tuneTownL, regionCenter, settingsB);

		String name = "Dad?";
		HBox greetingBox = new HBox();
		greetingBox.setId("greetingBox");
		Region greetingRegionFiller = new Region();
		HBox.setHgrow(greetingRegionFiller, Priority.ALWAYS);
		Label greetingLabel = new Label("Hello, " + name);
		greetingLabel.setId("headerLabel");
		greetingBox.getChildren().addAll(greetingLabel, greetingRegionFiller);

		// tile instances
		TileFragment b1 = new TileFragment("hello", "world");
		TileFragment b2 = new TileFragment("nihao", "world");
		TileFragment b3 = new TileFragment("hola", "world");
		b1.setId("tile1");
		b2.setId("tile1");
		b3.setId("tile1");

		tileList = new HBox();
		tileList.setId("tileList");
		tileList.getChildren().addAll(b1.getFragment(), b2.getFragment(), b3.getFragment());
		
		//used for debugging
		numTracks = new Label(fd.numberOfFavorites().toString());
		numTracks.setId("tuneTownLabel");
		

		if (fd.numberOfFavorites() >= 3){
			track1 = new TrackFragment(fd.getAt(0));
			track2 = new TrackFragment(fd.getAt(1));
			track3 = new TrackFragment(fd.getAt(2));

			root.getChildren().addAll(
			topBar,
			track1
			);
		} else {
			root.getChildren().addAll(
			topBar,
			greetingBox,
			tileList,
			numTracks
			);
		}
		
		root.setAlignment(Pos.TOP_CENTER);
		root.getStylesheets().add(getClass().getResource("home.css").toExternalForm());

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
			numTracks.setText(fd.numberOfFavorites().toString());
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
