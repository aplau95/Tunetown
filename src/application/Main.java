package application;

import application.Controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

public class Main extends Application {

	BorderPane root = new BorderPane();
	Controller currentController;

	@Override
	public void start(Stage primaryStage) {
		
		setupStage(primaryStage);
	
	}

	public void setupStage (Stage primaryStage) {
		// Create the Label for the Header
		Label headerLbl = new Label("TuneTown");

		// Navigation pane

		Node navPanel = GetNavigationPanel();

		root.setTop(headerLbl);
		root.setBottom(navPanel);

		// Set alignment
		BorderPane.setAlignment(headerLbl, Pos.TOP_CENTER);
		BorderPane.setAlignment(navPanel, Pos.BOTTOM_CENTER);

		Scene scene = new Scene(root,400,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		//Image imgPlay = new Image(getClass().getResourceAsStream("play.png"));
		//Button btnPlay = new Button();
		//btnPlay.setGraphic(new ImageView(imgPlay));

		//root.getChildren().add(btnPlay);
		primaryStage.setScene(scene);
		primaryStage.show();

		//return;
	}

    public Node GetNavigationPanel() {
    	
    	// Buttons
		Button btnHome = new Button("Home");
		Button btnDiscover = new Button("Discover");
		Button btnPlaylist = new Button("Playlists");
		Button btnFavorites = new Button("Favorites");

		TilePane navPanel = new TilePane(Orientation.HORIZONTAL);//,hgap, vgap);
		navPanel.setAlignment(Pos.CENTER);
		navPanel.setPrefColumns(4);
		navPanel.getChildren().add(btnHome);
		navPanel.getChildren().add(btnDiscover);
		navPanel.getChildren().add(btnPlaylist);
		navPanel.getChildren().add(btnFavorites);

		// Set the padding of the TilePane
		navPanel.setStyle("-fx-padding: 10;");
		navPanel.setStyle("-fx-border-style: solid inside;");
		navPanel.setStyle("-fx-border-width: 2;");
		navPanel.setStyle("-fx-border-insets: 5;");
		navPanel.setStyle("-fx-border-radius: 5;");
		navPanel.setStyle("-fx-border-color: blue;");


		Controller home = new HomeController();
		Controller discover = new DiscoverController();
		Controller playlist = new PlaylistsController();
		Controller favorite = new FavoritesController();

		Node homeScene = home.buildScene();
		Node discoverScene = discover.buildScene();
		Node playlistScene = playlist.buildScene();
		Node favoriteScene = favorite.buildScene();

		// Setup default subscene at the start of application
		root.setCenter(homeScene);
		currentController = home;

		// Change subscene with navigation panel
		btnHome.setOnAction((e) -> switchToView(homeScene, home));
		btnDiscover.setOnAction((e) -> switchToView(discoverScene, discover));
		btnPlaylist.setOnAction((e) -> switchToView(playlistScene, playlist));
		btnFavorites.setOnAction((e) -> switchToView(favoriteScene, favorite));

		return navPanel;
    }

    public void switchToView(Node nextNode, Controller nextController) {
		currentController.beforeHide();
		nextController.beforeShow();
		root.setCenter(nextNode);
		nextController.afterShow();
		currentController.afterHide();
		currentController = nextController;
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
