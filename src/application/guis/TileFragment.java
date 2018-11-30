package application.guis;

import com.wrapper.spotify.model_objects.specification.Track;

import javafx.scene.layout.*;
import javafx.scene.shape.*;
import java.awt.Color;
import javafx.geometry.Insets;

import application.TrackData;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import application.LoopingAudioPlayer;
import application.SpotifyAccessor;
import application.guis.TileFragment;
// import application.guis.TrackFragment;
import application.FavoritesData;
import application.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TileFragment {
    public static  Button CreateTile(String label, String sublabel, String sublabel2) {
		Button b1 = new Button();
		VBox b1TextBox= new VBox();
		Label b1Label = new Label(label);
		Label b1Sublabel = new Label(sublabel);
		Label b1Sublabel2 = new Label(sublabel2);
		b1Label.setId("tileLabel");
		b1Sublabel.setId("tileSublabel");
		b1Sublabel2.setId("tileSublabel");
		b1TextBox.getChildren().addAll(b1Label, b1Sublabel, b1Sublabel2);
		b1.setGraphic(b1TextBox);
		b1.setPrefSize(100, 100);
		
		return b1;
	}
	
	public static Button CreateRecentFaveTile(String title, String album, String artist) {
		Button btn = new Button();
		HBox btnBox = new HBox();
		VBox details = new VBox();
		Label lbl = new Label(title);
		Label subLbl = new Label(album + " - " + artist);
		
		
		ImageView i = new ImageView();
		i.setImage(new Image("https://images-na.ssl-images-amazon.com/images/I/51%2B0AeCT5mL._SS500.jpg"));
		i.setFitWidth(50);
		i.setPreserveRatio(true);

		lbl.setId("recentFaveLabel");
		subLbl.setId("recentFaveSubLabel");
		details.getChildren().addAll(lbl, subLbl);
		btnBox.getChildren().addAll(i,details);
		btn.setGraphic(btnBox);
		btn.setPrefSize(400, 70);		
		btn.setId("RecentFaveTile");
		
		return btn;
    }
}
