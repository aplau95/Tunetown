package application.guis;

import javafx.scene.layout.*;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.*;

public class TileFragment {

	private TileFragment() {
		throw new IllegalStateException("Utility class");
	}

	public static Button createRecentFaveTile(String imageUrl, String title, String album, String artist) {
		Button btn = new Button();
		HBox btnBox = new HBox();
		VBox details = new VBox();

		Label lbl = new Label(title);
		Label subLbl = new Label(album + " - " + artist);

		ImageView i = new ImageView();
		i.setImage(new Image(imageUrl));
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
