package application.controller.favorites;

import application.FavoritesData;
import application.controller.Controller;
import application.guis.TileFragment;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.textfield.CustomTextField;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class FavoritesController implements Controller {

	private FavoritesData fd;
	private Button numLikedB;
	private VBox searchResults;
	private CustomTextField searchBar;

	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	private PauseTransition pause = new PauseTransition(Duration.millis(250));

	public FavoritesController(FavoritesData fd) {
		this.fd = fd;
	}

	@Override
	public Node buildScene() {

		VBox root = new VBox();
		root.setId("root");

		Region regionLeft = new Region();
		regionLeft.setPrefWidth(40);
		Region regionLeft1 = new Region();
		regionLeft1.setPrefWidth(40);
		Region regionCenter = new Region();
		HBox.setHgrow(regionCenter, Priority.ALWAYS);
		Region regionRight = new Region();
		regionRight.setPrefWidth(40);
		Region regionRight1 = new Region();
		regionRight1.setPrefWidth(40);

		HBox topBar = new HBox();
		topBar.setId("topBar");
		Label discoverL = new Label("Favorites");
		discoverL.setId("favoritesLabel");
		numLikedB = new Button("53 Songs");
		numLikedB.setId("numLikedButton");
		topBar.getChildren().addAll(discoverL, regionCenter, numLikedB, regionRight);

		HBox searchBox = new HBox();
		searchBar = new CustomTextField();
		ImageView searchImage = new ImageView();
		searchImage.setId("searchImage");
		searchBar.setLeft(searchImage);
		searchBar.setId("searchBar");
		searchBar.setPromptText("Search by Song Name, Artist, or Genre...");
		HBox.setHgrow(searchBar, Priority.ALWAYS);
		searchBox.getChildren().addAll(regionLeft,searchBar,regionRight);
		searchBar.textProperty().addListener((obs, oldText, newText) -> 
			this.onSearch(newText)
		);

		searchResults = new VBox();
		searchResults.setAlignment(Pos.TOP_CENTER);
		searchResults.setSpacing(15);
		HBox scrollBox = new HBox();
		ScrollPane sp = new ScrollPane();
		sp.setFitToWidth(true);
		sp.setId("scrollPane");
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setContent(searchResults);
		HBox.setHgrow(sp, Priority.ALWAYS);
		scrollBox.getChildren().addAll(regionLeft1, sp, regionRight1);
		sp.setPrefViewportHeight(1000);

		root.getChildren().addAll(topBar, searchBox, scrollBox);
		root.setAlignment(Pos.TOP_CENTER);
		root.getStylesheets().add(getClass().getResource("favorites.css").toExternalForm());

		return root;
	}

	private void onSearch(String searchText) {

		pause.setOnFinished(event ->
			executorService.submit(() -> {

				List<Node> results = fd.search(searchText).stream()
						.map(td -> TileFragment.createRecentFaveTile(td.getImageUrl(), td.getName(), td.getAlbum(), td.getArtists()))
						.collect(Collectors.toList());

				pause.setOnFinished(pauseEvent ->
					Platform.runLater(() -> {
						searchResults.getChildren().clear();
						searchResults.getChildren().addAll(results);
					})
				);
				pause.playFromStart();

			}));
		pause.playFromStart();

	}

	
	/**
	 * Called before the page is shown
	 */
	@Override
	public void beforeShow() {
		// Add something here if you want
	}

	/**
	 * Called after the page is shown. It's your time to shine!
	 */
	@Override
	public void afterShow() {
		onSearch(searchBar.getText());

		int numLiked = fd.getFavoritesList().size();
		numLikedB.setText(numLiked + (numLiked == 1 ? " Song" : " Songs"));
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
