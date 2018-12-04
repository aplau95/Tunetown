package application.controller.home;

import application.guis.SquareTile;
import application.guis.TileFragment;
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
	String headerLabel = "headerLabel";

	HBox topBar;
	HBox tileList;
	VBox root;
	Button b2;
	Button b1;
	SquareTile st1;
	SquareTile st2;
	SquareTile st3;
	Button b3;
	VBox recentfaveList;

	ExecutorService executorService = Executors.newFixedThreadPool(2);

	public HomeController(FavoritesData fd) {
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
		tuneTownL.setId(headerLabel);
		topBar.getChildren().addAll(tuneTownL, regionCenter);

		String name = "Dad?";
		HBox greetingBox = new HBox();
		greetingBox.setId("greetingBox");
		Region greetingRegionFiller = new Region();
		HBox.setHgrow(greetingRegionFiller, Priority.ALWAYS);
		Label greetingLabel = new Label("Hello, " + name);
		greetingLabel.setId(headerLabel);
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
		Label recentfaveTitle = new Label("Recently Favorited");
		recentfaveTitle.setId(headerLabel);
		
		
		// song tiles
		recentfaveList = new VBox();
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
			if (fd.numberOfFavorites() >= 3){
				TrackData temp1 = fd.getAt(fd.numberOfFavorites() - 3);
				TrackData temp2 = fd.getAt(fd.numberOfFavorites() - 2);
				TrackData temp3 = fd.getAt(fd.numberOfFavorites() - 1);
				recentfaveList.getChildren().clear();
				recentfaveList.getChildren().add(TileFragment.createRecentFaveTile(temp3.getImageUrl(), temp3.getName(), temp3.getAlbum(), temp3.getArtists()));
				recentfaveList.getChildren().add(TileFragment.createRecentFaveTile(temp2.getImageUrl(), temp2.getName(), temp2.getAlbum(), temp2.getArtists()));
				recentfaveList.getChildren().add(TileFragment.createRecentFaveTile(temp1.getImageUrl(), temp1.getName(), temp1.getAlbum(), temp1.getArtists()));				
			} else if (fd.numberOfFavorites() == 2) {
				TrackData temp1 = fd.getAt(fd.numberOfFavorites() - 2);
				TrackData temp2 = fd.getAt(fd.numberOfFavorites() - 1);
				recentfaveList.getChildren().clear();
				recentfaveList.getChildren().add(TileFragment.createRecentFaveTile(temp2.getImageUrl(), temp2.getName(), temp2.getAlbum(), temp2.getArtists()));
				recentfaveList.getChildren().add(TileFragment.createRecentFaveTile(temp1.getImageUrl(), temp1.getName(), temp1.getAlbum(), temp1.getArtists()));
			} else if (fd.numberOfFavorites() == 1) {
				TrackData temp1 = fd.getAt(fd.numberOfFavorites() - 1);
				recentfaveList.getChildren().clear();
				recentfaveList.getChildren().add(TileFragment.createRecentFaveTile(temp1.getImageUrl(), temp1.getName(), temp1.getAlbum(), temp1.getArtists()));
			}
	}

	/**
	 * Called before the page is hidden. Use this to cleanup.
	 * Don't do anything that will block the main UI thread
	 * or else user will see a delay switching to the next tab
	 */
	@Override
	public void beforeHide() {
		//Implement if necessary
	}

	/**
	 * Called after page is hidden. Not much to do here probably.
	 */
	@Override
	public void afterHide() {
		//Implement if necessary
	}

}
