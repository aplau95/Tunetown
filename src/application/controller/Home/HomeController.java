package application.controller.Home;

import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
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
		Button b1 = TileFragment.CreateTile("Pop", "Most Liked", "Genre");			
		Button b2 = TileFragment.CreateTile("51", "New Songs", "Discovered"); 		
		Button b3 = TileFragment.CreateTile("2", "Hours of", "New Music"); 

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
				TileFragment.CreateRecentFaveTile("I Feel It Coming", "Starboy", "The Weeknd"), 
				TileFragment.CreateRecentFaveTile("Controlla", "Views", "Drake"), 
				TileFragment.CreateRecentFaveTile("Look What You Made Me Do", "Reputation", "Taylor Swift")
				);
		recentfaveList.setSpacing(20.0);
		recentfaveBox.getChildren().addAll(recentfaveTitle, recentfaveList);
		recentfaveBox.setSpacing(20.0);
		
		// not doing anything with this rn
		//numTracks = new Label(fd.numberOfFavorites().toString());
		//numTracks.setId("tuneTownLabel");
		

		if (fd.numberOfFavorites() >= 3){
			// track1 = new TrackFragment(fd.getAt(0));
			// track2 = new TrackFragment(fd.getAt(1));
			// track3 = new TrackFragment(fd.getAt(2));

			root.getChildren().addAll(
			topBar
			);
		} else {
			root.getChildren().addAll(
			topBar,
			greetingBox,
			tileList,
			recentfaveBox
			);
		}
		
		root.setAlignment(Pos.TOP_CENTER);
		root.getStylesheets().add(getClass().getResource("home.css").toExternalForm());

		return root;
	}

	// private Button CreateTile(String label, String sublabel, String sublabel2) {
	// 	Button b1 = new Button();
	// 	VBox b1TextBox= new VBox();
	// 	Label b1Label = new Label(label);
	// 	Label b1Sublabel = new Label(sublabel);
	// 	Label b1Sublabel2 = new Label(sublabel2);
	// 	b1Label.setId("tileLabel");
	// 	b1Sublabel.setId("tileSublabel");
	// 	b1Sublabel2.setId("tileSublabel");
	// 	b1TextBox.getChildren().addAll(b1Label, b1Sublabel, b1Sublabel2);
	// 	b1.setGraphic(b1TextBox);
	// 	b1.setPrefSize(100, 100);
		
	// 	return b1;
	// }
	
	// private Button CreateRecentFaveTile(String title, String album, String artist) {
	// 	Button btn = new Button();
	// 	HBox btnBox = new HBox();
	// 	VBox details = new VBox();
	// 	Label lbl = new Label(title);
	// 	Label subLbl = new Label(album + " - " + artist);
		
		
	// 	ImageView i = new ImageView();
	// 	i.setImage(new Image("https://images-na.ssl-images-amazon.com/images/I/51%2B0AeCT5mL._SS500.jpg"));
	// 	i.setFitWidth(50);
	// 	i.setPreserveRatio(true);

	// 	lbl.setId("recentFaveLabel");
	// 	subLbl.setId("recentFaveSubLabel");
	// 	details.getChildren().addAll(lbl, subLbl);
	// 	btnBox.getChildren().addAll(i,details);
	// 	btn.setGraphic(btnBox);
	// 	btn.setPrefSize(400, 70);		
	// 	btn.setId("RecentFaveTile");
		
	// 	return btn;
	// }
	
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
