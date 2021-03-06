package application.controller.settings;

import application.FavoritesData;
import application.Logger;
import application.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingsController implements Controller {

	FavoritesData fd;

	String subheaderCSS = "Subheader";
	String descriptionCSS = "Description";
	VBox root;
	int faveGenres = 0;

	ExecutorService executorService = Executors.newFixedThreadPool(2);


	public SettingsController(FavoritesData fd) {
		try {
			this.fd = fd;
		} catch (Exception e) {
			Logger.getInstance().log("Failed to connect to spotify");
		}
	}

	@Override
	public Node buildScene() {
		root = new VBox();
		HBox topBar = new HBox();
		Label settingsL = new Label("Settings");
		root.setId("root");
		


		// Heading
		Region regionCenter = new Region();
		HBox.setHgrow(regionCenter, Priority.ALWAYS);
		topBar.setId("Node");
		settingsL.setId("Header");
		topBar.getChildren().addAll(settingsL, regionCenter);

		// Name
		VBox nameBox = new VBox();
		nameBox.setSpacing(10);
		nameBox.setId("Node");
		Label nameSH = new Label("Name");
		nameSH.setId(subheaderCSS);
		Label nameD = new Label("Your name will only be used in the app");
		nameD.setId(descriptionCSS);

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
		genreSH.setId(subheaderCSS);
		Label genreD = new Label("Used to tailor recommendations to your taste");
		genreD.setId(descriptionCSS);

		VBox genreList = new VBox();
		genreList.setSpacing(10);

		HBox genreEdit = new HBox();
		TextField genreIn = new TextField("Add new genre");
		genreIn.setId("Input");
		genreIn.setMinWidth(300);
		Label genreSave = new Label(); 		// to add a save button - if needed
		genreEdit.getChildren().addAll(genreIn, genreSave);
		
		genreList.getChildren().addAll(
			addNewGenre("Pop"),
			addNewGenre("Hip Hop"),
			addNewGenre("Alternatic Rock"),
			addNewGenre("Electronic"),
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
		popSH.setId(subheaderCSS);
		Label popD = new Label("A low popularity will recommend rarer songs while a high value will recommend recent popular songs");
		popD.setWrapText(true);
		popD.setId(descriptionCSS);

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

	public HBox addNewGenre(String gName) {
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
		//Implement if necessary
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
