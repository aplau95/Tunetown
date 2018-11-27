// package application.guis;

// import com.wrapper.spotify.model_objects.specification.Track;

// import javafx.scene.layout.*;
// import javafx.scene.shape.*;
// import java.awt.Color;
// import javafx.geometry.Insets;

// import application.TrackData;
// import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.Node;

// public class TrackFragment extends Node {//what to extend?
    
//     HBox trackFrag;

//     public TrackFragment(TrackData td) {
//         Rectangle r = new Rectangle();
//         r.setX(50);
//         r.setY(50);
//         r.setWidth(100);
//         r.setHeight(100);
//         r.setArcWidth(20);
//         r.setArcHeight(20);

//         //image on left side for trackFrag
//         ImageView albumI = new ImageView();
// 		albumI.setFitWidth(30);
// 		albumI.setPreserveRatio(true);
//         albumI.setImage(new Image(td.getImageUrl()));

//         Circle albumImage = new Circle();
//         albumImage.setCenterX(100.0f);
//         albumImage.setCenterY(100.0f);
//         albumImage.setRadius(50.0f);
        

//         // track info which includes song and artist
//         VBox trackInfoSubFrag = new VBox(); 
//         Label artistL = new Label(td.getArtists());
//         Label trackL = new Label(td.getName());
//         trackInfoSubFrag.getChildren().addAll(artistL, trackL);

//         trackFrag = new HBox();
// 		trackFrag.setId("trackFrag");
// 		trackFrag.getChildren().addAll(albumI, trackInfoSubFrag);
// 		trackFrag.setPrefWidth(300);
//         trackFrag.setPadding(new Insets(15, 12, 15, 12));
//         trackFrag.getStylesheets().add(getClass().getResource("trackfrag.css").toExternalForm());
//     }

//     public HBox getFragment(){
//         return trackFrag;
//     }
// }
