package org.apandey;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apandey.props.AppSettings;

public class Contacts extends Application {
	private static Contacts contacts;
	private double startDragX;
	private double startDragY;
	private Stage stage;
	private VBox root;

	public static Contacts getInstance() {
		return contacts;
	}

	public Stage getPrimaryStage() {
		return stage;
	}

	@Override
	public void start(final Stage primaryStage) {
		contacts = this;
		stage = primaryStage;

		primaryStage.setTitle(AppSettings.getStringValue("app.title.text"));
		primaryStage.initStyle(StageStyle.TRANSPARENT);

		root = new VBox();
		root.getStyleClass().add("root");

		Scene scene = new Scene(root,
				AppSettings.getDoubleValue("app.window.width"),
				AppSettings.getDoubleValue("app.window.height"),
				Color.TRANSPARENT);
		scene.getStylesheets().add(
				Contacts.class.getResource(
						AppSettings.getStringValue("app.stylesheet.path"))
						.toExternalForm());

		Name person = new Name();
		root.getChildren().addAll(person);

		primaryStage.setScene(scene);
		primaryStage.show();

		enableDragging();
	}

	public void enableDragging() {
		root.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				startDragX = me.getSceneX();
				startDragY = me.getSceneY();
				root.setStyle("-fx-cursor:move;");
			}
		});
		root.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				root.setStyle("-fx-cursor:default;");
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				stage.setX(me.getScreenX() - startDragX);
				stage.setY(me.getScreenY() - startDragY);
				root.setStyle("-fx-cursor:move;");
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
