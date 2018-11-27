package application.guis;

import com.wrapper.spotify.model_objects.specification.Track;

import javafx.scene.layout.*;
import javafx.scene.shape.*;
import java.awt.Color;
import javafx.geometry.Insets;

public class TrackFragment extends Region {//what to extend?
    //color of each segment
    public String artist;
    public String trackName;
    public String imageUrl;


    public TrackFragment() {
        HBox trackFrag = new HBox();
        Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setWidth(100);
        r.setHeight(100);
        r.setArcWidth(20);
        r.setArcHeight(20);

        Circle albumImage = new Circle();
        albumImage.setCenterX(100.0f);
        albumImage.setCenterY(100.0f);
        albumImage.setRadius(50.0f);
        

        trackFrag.getChildren().addAll(r);
        trackFrag.setPrefWidth(200);
        trackFrag.setPadding(new Insets(15, 12, 15, 12));

        // r.setFill(Color.GRAY);
        // r.setStroke(Color.BLUEVIOLET);
    }
}
