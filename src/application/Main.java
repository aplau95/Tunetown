package application;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.wrapper.spotify.SpotifyApi;

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

class SpotifyAccessor {
	
	private String accessToken;
	private CloseableHttpClient client;
	
	public SpotifyAccessor(String clientId, String clientSecret) throws ClientProtocolException, IOException {
		
		client = HttpClientBuilder.create().build();
		
		HttpPost httppost = new HttpPost("https://accounts.spotify.com/api/token");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type", "client_credentials"));
		httppost.setEntity(new UrlEncodedFormEntity(params));
		
		String encoding = Base64.getEncoder().encodeToString((clientId+":"+clientSecret).getBytes());
		httppost.setHeader("Authorization", "Basic " + encoding);
		
		CloseableHttpResponse response = client.execute(httppost);
		String bodyAsString = EntityUtils.toString(response.getEntity());
		
		JSONObject obj = new JSONObject(bodyAsString);
		
		accessToken = obj.getString("access_token");
		
	}
	
	public JSONObject getSampleTrack() throws ClientProtocolException, IOException {
		
		HttpGet httpget = new HttpGet("https://api.spotify.com/v1/tracks/2TpxZ7JUBn3uw46aR7qd6V");
		httpget.setHeader("Authorization","Bearer "+accessToken);
		
		CloseableHttpResponse response = client.execute(httpget);
		String bodyAsString = EntityUtils.toString(response.getEntity());
		
		return new JSONObject(bodyAsString);
	}
	
}

public class Main extends Application {
	
	public static InputStream openStream(String myUrl) throws IOException {
	    final URL url = new URL(myUrl);
	    final URLConnection con = url.openConnection();
	    con.setRequestProperty("User-Agent", "My Client");
	    return new BufferedInputStream(con.getInputStream());
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			setupStage(primaryStage);
			
			SpotifyAccessor spotify = new SpotifyAccessor("160b683f23e946ed8000ec438e36890a",
					"efa3a5718c6a49acb3828305c3a01c7b");
			
			String previewUrl = spotify.getSampleTrack().getString("preview_url");
			
			try {
				URL url = new URL(previewUrl);	
				AdvancedPlayer myPlayer = new AdvancedPlayer(url.openStream());
				myPlayer.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
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
