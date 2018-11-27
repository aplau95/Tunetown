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

public class TileFragment extends Region {//what to extend?
    //color of each segment

    StackPane boxFrag;

    public TileFragment(String title, String description) {
    //     Circle circle = new Circle(300, 135, 100); 
    //   circle.setFill(Color.DARKSLATEBLUE); 
    //   circle.setStroke(Color.BLACK);
    Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setWidth(100);
        r.setHeight(100);
        r.setArcWidth(20);
        r.setArcHeight(20);  
      
       
    r.setId("tile");
      
    boxFrag = new StackPane(); 
    Insets i = new Insets(0, 0, 0, 0);
    
    
    VBox boxSubFrag = new VBox();
    
    Label titleL = new Label(title);
    Label descL = new Label(description);
    boxSubFrag.getChildren().addAll(titleL, descL);
    boxFrag.setMargin(r, i);
    boxFrag.getChildren().addAll(r, boxSubFrag); 
      
        
          

        // track info which includes song and artist
        

        // boxFrag.setPadding(new Insets(5, 5, 5, 5));
        boxFrag.getStylesheets().add(getClass().getResource("tilefrag.css").toExternalForm());

    }

    public StackPane getFragment(){
        return boxFrag;
    }
}
