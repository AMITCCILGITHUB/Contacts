package org.apandey.view.parts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.apandey.Contacts;
import org.apandey.props.AppSettings;

public class Body extends VBox {
	public Body() {

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Button nameButton = new Button("Full Name...");
		nameButton.setPrefWidth(AppSettings.getDoubleValue("body.label.width"));
		grid.add(nameButton, 0, 0);

		nameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Contacts.getInstance().showFullNamePopup();
			}

		});

		TextField fullNameText = new TextField();
		fullNameText.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid.add(fullNameText, 1, 0);

		getChildren().addAll(grid);
	}
}
