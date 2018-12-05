package application;

import application.controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.geometry.Pos;

import java.util.Arrays;
import java.util.List;

public class Main extends Application {

	BorderPane root = new BorderPane();
	Controller currentController;
	VBox currentTarget;
	List<VBox> targets;

	@Override
	public void start(Stage primaryStage) {
		
		Logger.getInstance().log("Setting up stage");

		primaryStage.setOnCloseRequest(event -> {Platform.exit(); System.exit(0);});
		
		setupStage(primaryStage);
	
	}

	public void setupStage (Stage primaryStage) {

		// Navigation pane

		Node navPanel = getNavigationPanel();

		root.setBottom(navPanel);

		// Set alignment
		BorderPane.setAlignment(navPanel, Pos.BOTTOM_CENTER);

		Scene scene = new Scene(root,444,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public VBox buildNavTarget(String name) {
		VBox box = new VBox();
		ImageView icon = new ImageView();
		icon.setId("icon"+name);
		Label nameL = new Label(name);
		nameL.setId("label"+name);
		box.getChildren().addAll(icon, nameL);
		box.setAlignment(Pos.CENTER);
		return box;
	}

    public Node getNavigationPanel() {
    	
    	// Buttons
		VBox homeTarget = buildNavTarget("Home");
		VBox discoverTarget = buildNavTarget("Discover");
		discoverTarget.setId("dim");
		VBox settingsTarget = buildNavTarget("Settings");
		settingsTarget.setId("dim");
		VBox favoritesTarget = buildNavTarget("Favorites");
		favoritesTarget.setId("dim");

		targets = Arrays.asList(homeTarget,discoverTarget,settingsTarget,favoritesTarget);

		GridPane navPanel = new GridPane();
		navPanel.setAlignment(Pos.CENTER);
		navPanel.setId("navPanel");
		navPanel.setPrefHeight(100);
		
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(100/4.0);
		cc.setHalignment(HPos.CENTER);
		
		navPanel.getColumnConstraints().setAll(cc,cc,cc,cc);
		navPanel.add(homeTarget,0,0);
		navPanel.add(discoverTarget,1,0);
		navPanel.add(favoritesTarget,2,0);
		navPanel.add(settingsTarget,3,0);

		// Set the padding of the TilePane

		navPanel.setStyle("-fx-padding: 10;");
		navPanel.setBackground(new Background(new BackgroundFill(Color.web("#252628"), CornerRadii.EMPTY, Insets.EMPTY)));
		navPanel.setStyle("-fx-border-style: solid;");
		navPanel.setStyle("-fx-border-color: rgb(67,67,67);");
		navPanel.setStyle("-fx-border-width: 1;");

		FavoritesData fd = new FavoritesData();

		Controller home = ControllerFactory.build(ControllerFactory.Type.HOME, fd);
		Controller discover = ControllerFactory.build(ControllerFactory.Type.DISCOVER, fd);
		Controller settings = ControllerFactory.build(ControllerFactory.Type.SETTINGS, fd);
		Controller favorite = ControllerFactory.build(ControllerFactory.Type.FAVORITES, fd);

		Node homeScene = home.buildScene();
		Node discoverScene = discover.buildScene();
		Node settingsScene = settings.buildScene();
		Node favoriteScene = favorite.buildScene();

		// Setup default subscene at the start of application
		root.setCenter(homeScene);
		currentController = home;

		// Change subscene with navigation panel
		homeTarget.setOnMouseClicked(e -> switchToView(homeScene, home, homeTarget));
		discoverTarget.setOnMouseClicked(e -> switchToView(discoverScene, discover, discoverTarget));
		settingsTarget.setOnMouseClicked(e -> switchToView(settingsScene, settings, settingsTarget));
		favoritesTarget.setOnMouseClicked(e -> switchToView(favoriteScene, favorite, favoritesTarget));

		return navPanel;
    }

    public void switchToView(Node nextNode, Controller nextController, VBox target) {
    	Logger.getInstance().log("Changing Panel");
		targets.forEach(t -> t.setId("dim"));
		target.setId("");
		currentController.beforeHide();
		nextController.beforeShow();
		root.setCenter(nextNode);
		nextController.afterShow();
		currentController.afterHide();
		currentController = nextController;
	}

	
	public static void main(String[] args) {
		launch();
	}
}
