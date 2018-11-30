package application.controller.Home;

import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
import application.guis.SquareTile;
import application.guis.TileFragment;
import application.FavoritesData;
import application.TrackData;
import application.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeController implements Controller {

	FavoritesData fd;

	// TrackFragment track1;
	// TrackFragment track2;
	// TrackFragment track3;

	Label numTracks;
	HBox topBar;
	HBox tileList;
	VBox root;
	Button b2;
	Button b1;
	SquareTile st1;
	SquareTile st2;
	SquareTile st3;
	Button b3;

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
		st1 = new SquareTile("Pop", "Most Liked", "Genre");
		b1 = st1.getSquareTile();
		st2 = new SquareTile(fd.numberOfFavorites().toString(), "New Songs", "Discovered");
		b2 = st2.getSquareTile();
		st3 = new SquareTile(fd.numberOfMinutes().toString(), "Minutes", "New Music");
		b3 = st3.getSquareTile();

		b1.setId("tile1");
		b2.setId("tile2");
		b3.setId("tile3");

		tileList = new HBox();
		tileList.setId("tileList");
		tileList.getChildren().addAll(b1, b2, b3);
		tileList.setSpacing(10.0);
		
		// Recently favorited list
		VBox recentfaveBox = new VBox();
		recentfaveBox.setId("greetingBox");
		//Region recentfaveRegionFiller = new Region();		// what're these used for?
		//HBox.setHgrow(recentfaveRegionFiller, Priority.ALWAYS);
		Label recentfaveTitle = new Label("Recently Favorited");
		recentfaveTitle.setId("headerLabel");
		
		// song tiles
		VBox recentfaveList = new VBox();
		recentfaveList.getChildren().addAll(
				// TileFragment.CreateRecentFaveTile("I Feel It Coming", "Starboy", "The Weeknd"), 
				// TileFragment.CreateRecentFaveTile("Controlla", "Views", "Drake"), 
				// TileFragment.CreateRecentFaveTile("Look What You Made Me Do", "Reputation", "Taylor Swift")
				);
		recentfaveList.setSpacing(20.0);
		recentfaveBox.getChildren().addAll(recentfaveTitle, recentfaveList);
		recentfaveBox.setSpacing(20.0);
		

		
		root.getChildren().addAll(
		topBar,
		greetingBox,
		tileList,
		recentfaveBox
		);
		
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
			b2 = st2.updateData(fd.numberOfFavorites().toString());
			b3 = st3.updateData(Integer.toString(fd.numberOfMinutes() / 60000));
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
