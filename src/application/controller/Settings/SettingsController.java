package application.controller.Settings;

import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
import application.FavoritesData;
import application.TrackData;
import application.controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;



public class SettingsController implements Controller {

	FavoritesData fd;

	VBox root;
	int faveGenres = 0;

	ExecutorService executorService = Executors.newFixedThreadPool(2);


	public SettingsController(FavoritesData fd) {
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

		// Heading
		Region regionCenter = new Region();
		HBox.setHgrow(regionCenter, Priority.ALWAYS);

		HBox topBar = new HBox();
		topBar.setId("Node");
		Label settingsL = new Label("Settings");
		settingsL.setId("Header");
	
		topBar.getChildren().addAll(settingsL, regionCenter);

		// Name
		VBox nameBox = new VBox();
		nameBox.setSpacing(10);
		nameBox.setId("Node");
		//nameBox.setPadding(new Insets(0, 50, 0, 50));
		//nameBox.setSpacing(10);
		Label nameSH = new Label("Name");
		nameSH.setId("Subheader");
		Label nameD = new Label("You name will only be used in the app");
		nameD.setId("Description");

		HBox nameEdit = new HBox();
		nameEdit.setSpacing(10);
		TextField nameIn = new TextField("Dad?");
		nameIn.setId("Input");
		nameIn.setMinWidth(300);
		Label nameSave = new Label("");	// to add a save button - if needed
		nameEdit.getChildren().addAll(nameIn, nameSave);

		nameBox.getChildren().addAll(
			nameSH,
			nameD,
			nameEdit
			);

		// Favorite Genres
		VBox genreBox = new VBox();
		genreBox.setSpacing(10);
		genreBox.setId("Node");
		Label genreSH = new Label("Favorite Genres");
		genreSH.setId("Subheader");
		Label genreD = new Label("Used to tailor recommendations to your taste");
		genreD.setId("Description");

		VBox genreList = new VBox();
		genreList.setSpacing(10);

		HBox genreEdit = new HBox();
		TextField genreIn = new TextField("Add new genre");
		genreIn.setId("Input");
		genreIn.setMinWidth(300);
		Label genreSave = new Label(); 		// to add a save button - if needed
		genreEdit.getChildren().addAll(genreIn, genreSave);
		
		genreList.getChildren().addAll(
			AddNewGenre("Pop"),
			AddNewGenre("Hip Hop"),
			AddNewGenre("Alternatic Rock"),
			AddNewGenre("Electronic"),
			genreEdit
		);

		genreBox.getChildren().addAll(
			genreSH,
			genreD,
			genreList
		);

		// Popularity
		VBox popBox = new VBox();
		popBox.setSpacing(10);
		popBox.setId("Node");
		Label popSH = new Label ("Popularity");
		popSH.setId("Subheader");
		Label popD = new Label("A low popularity will recommend rarer songs while a high value will recommend recent popular songs");
		popD.setWrapText(true);
		popD.setId("Description");

		Slider popSlider = new Slider(0, 100, 50);
		popSlider.setShowTickMarks(true);
		popSlider.setShowTickLabels(true);
		popSlider.setMajorTickUnit(25f);
		popSlider.setBlockIncrement(1f);

		popBox.getChildren().addAll(
			popSH,
			popD,
			popSlider
		);

		root.getChildren().addAll(
			topBar,
			nameBox,
			genreBox,
			popBox
			);
		root.setAlignment(Pos.TOP_CENTER);
		root.getStylesheets().add(getClass().getResource("settings.css").toExternalForm());

		return root;
	}

	public HBox AddNewGenre(String gName) {
		HBox genre = new HBox();
		genre.setSpacing(10);
		ImageView deleteIcon = new ImageView();
		deleteIcon.setId("IconDelete");
		Label genreLbl = new Label(gName);
		genreLbl.setId("GenreLabel");
		genre.getChildren().addAll(
			deleteIcon,
			genreLbl
		);
		return genre;
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
