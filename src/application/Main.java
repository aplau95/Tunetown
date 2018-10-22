package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		setupStage(primaryStage);
		
		new Thread(() -> {
			
			try {

//Uncomment to play audio for 5 seconds
//				SpotifyAccessor spotify = new SpotifyAccessor("160b683f23e946ed8000ec438e36890a",
//						"efa3a5718c6a49acb3828305c3a01c7b");
//				
//				String previewUrl = spotify.getSampleTrack().getString("preview_url");
//				
//				LoopingAudioPlayer player = new LoopingAudioPlayer(new URL(previewUrl));
//				
//				//Start Playing
//				Thread audioThread = new Thread(player);
//				audioThread.start();
//				
//				//Wait 5 seconds
//				Thread.sleep(5000);
//				
//				//Stop and after it stops print exited
//				player.stop(() -> {
//					System.out.println("Exited");
//				});
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}).start();
	
	}
	
	BorderPane root = new BorderPane();
    Node subScene = GetHomeScene();
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

         // Setup default subscene at the start of application
         root.setCenter(subScene);
         
         // Change subscene with navigation panel
         btnHome.setOnAction(new EventHandler<ActionEvent>() {
         	public void handle(ActionEvent event) {
         		subScene = GetHomeScene();
         		root.setCenter(subScene);
         	}
         });
         btnDiscover.setOnAction(new EventHandler<ActionEvent>() {
         	public void handle(ActionEvent event) {
         		subScene = GetDiscoverScene();
         		root.setCenter(subScene);
         	}
         });
         btnPlaylist.setOnAction(new EventHandler<ActionEvent>() {
         	public void handle(ActionEvent event) {
         		subScene = GetPlaylistScene();
         		root.setCenter(subScene);
         	}
         });
     	btnFavorites.setOnAction(new EventHandler<ActionEvent>() {
         	public void handle(ActionEvent event) {
         		subScene = GetFavoritesScene();
         		root.setCenter(subScene);
         	}
         });
     	
    	return navPanel;
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
	
	public Node GetHomeScene() {
		Label lbl = new Label("HOME");

		VBox root = new VBox();

		root.getChildren().add(lbl);
		root.setAlignment(Pos.CENTER);
		
		return root;
	}
	public Node GetPlaylistScene() {
		Label lbl = new Label("PLAYLIST");

		VBox root = new VBox();

		root.getChildren().add(lbl);
		root.setAlignment(Pos.CENTER);
		
		return root;
	}
	public Node GetDiscoverScene() {
		Label lbl = new Label("DISCOVER");

		VBox root = new VBox();

		root.getChildren().add(lbl);
		root.setAlignment(Pos.CENTER);
		
		return root;
	}
	public Node GetFavoritesScene() {
		Label lbl = new Label("FAVORITES");

		VBox root = new VBox();

		root.getChildren().add(lbl);
		root.setAlignment(Pos.CENTER);
		
		return root;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
