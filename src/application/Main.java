package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.http.NameValuePair;
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
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javazoom.jl.player.Player;;


public class Main extends Application {
	
	private static MediaPlayer mediaPlayer;
	private static Media hit;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			CloseableHttpClient client = HttpClientBuilder.create().build();
			
			HttpPost httppost = new HttpPost("https://accounts.spotify.com/api/token");
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("grant_type", "client_credentials"));
			httppost.setEntity(new UrlEncodedFormEntity(params));
			
			String encoding = Base64.getEncoder().encodeToString(("160b683f23e946ed8000ec438e36890a:efa3a5718c6a49acb3828305c3a01c7b").getBytes());
			httppost.setHeader("Authorization", "Basic " + encoding);
			
			CloseableHttpResponse response = client.execute(httppost);
			String bodyAsString = EntityUtils.toString(response.getEntity());
			
			System.out.println(bodyAsString);
			
			JSONObject obj = new JSONObject(bodyAsString);
			String token = obj.getString("access_token");
			
			System.out.println(token);
			
			HttpGet httpget = new HttpGet("https://api.spotify.com/v1/tracks/2TpxZ7JUBn3uw46aR7qd6V");
			httpget.setHeader("Authorization","Bearer "+token);
			
			response = client.execute(httpget);
			bodyAsString = EntityUtils.toString(response.getEntity());
			obj = new JSONObject(bodyAsString);
			String preview_url = obj.getString("preview_url");
			System.out.println(preview_url);
			
			try {
				URL url = new URL(preview_url);	
				Player myPlayer = new Player(url.openStream());
				myPlayer.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
					

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
