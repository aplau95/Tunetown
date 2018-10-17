package application;

import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
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
	
	public void setupStage (Stage primaryStage) {
		// Create the Label for the Header
        Label headerLbl = new Label("TuneTown");
        // Create the Label for the Input
        Label leftLbl = new Label("Songs");
        Button navHomeBtn = new Button("HOME");
        Button navPlaylistBtn = new Button("PLAYLIST");
        
    	// Navigation pane
        // Create the horizontal TilePane with a 10px spacing  
        float hgap = 10f;
        float vgap = 10f;
        TilePane navPane = new TilePane(Orientation.HORIZONTAL);//,hgap, vgap);
        navPane.setAlignment(Pos.CENTER);
        navPane.setPrefColumns(4);
        navPane.getChildren().add(new Button("Home"));
        navPane.getChildren().add(new Button("Discover"));
        navPane.getChildren().add(new Button("Playlist"));
        navPane.getChildren().add(new Button("Favorites"));

        // Set the padding of the TilePane     
        navPane.setStyle("-fx-padding: 10;");
        // Set the border-style of the TilePane
        navPane.setStyle("-fx-border-style: solid inside;");
        // Set the border-width of the TilePane
        navPane.setStyle("-fx-border-width: 2;");
        // Set the border-insets of the TilePane
        navPane.setStyle("-fx-border-insets: 5;");
        // Set the border-radius of the TilePane
        navPane.setStyle("-fx-border-radius: 5;");
        // Set the border-color of the TilePane
        navPane.setStyle("-fx-border-color: blue;");
		
		BorderPane root = new BorderPane();
		root.setTop(headerLbl);
		root.setLeft(leftLbl);
		root.setBottom(navPane);
		
		// Set alignment
		BorderPane.setAlignment(headerLbl, Pos.TOP_CENTER);
		BorderPane.setAlignment(leftLbl, Pos.TOP_CENTER);
		BorderPane.setAlignment(navHomeBtn, Pos.BOTTOM_RIGHT);
		BorderPane.setAlignment(navPane, Pos.BOTTOM_CENTER);
		
 

		
		
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
