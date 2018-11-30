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

public class SquareTile {
    Button b1 = new Button();
    VBox b1TextBox = new VBox();
    Label b1Label = new Label();
    Label b1Sublabel = new Label();
    Label b1Sublabel2 = new Label();
    

    public SquareTile(String label, String sublabel, String sublabel2) {
        b1Label.setText(label);
        b1Sublabel.setText(sublabel);
        b1Sublabel2.setText(sublabel2);
        b1Label.setId("tileLabel");
        b1Sublabel.setId("tileSublabel");
        b1Sublabel2.setId("tileSublabel");
		
		b1TextBox.getChildren().addAll(b1Label, b1Sublabel, b1Sublabel2);
		b1.setGraphic(b1TextBox);
		b1.setPrefSize(100, 100);
    }
    
    public Button getSquareTile() {
        return b1;
    }

    public Button updateData(String newVal) {
        b1Label.setText(newVal);
        return b1;
    }
}