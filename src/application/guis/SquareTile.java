package application.guis;

import javafx.scene.layout.*;

import javafx.scene.control.Label;

import javafx.geometry.Pos;
import javafx.scene.control.*;

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

        b1TextBox.setAlignment(Pos.CENTER);
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