package org.apandey;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apandey.props.AppSettings;
import org.apandey.view.parts.Body;
import org.apandey.view.parts.Footer;
import org.apandey.view.parts.Header;

public class Contacts extends Application {
	private static Contacts contacts;
	private double startDragX;
	private double startDragY;
	private Stage stage;
	private StackPane root;
	private NamePopup fullName;

	public static Contacts getInstance() {
		return contacts;
	}

	public Stage getPrimaryStage() {
		return stage;
	}

	@Override
	public void start(final Stage primaryStage) throws MalformedURLException {
		contacts = this;
		stage = primaryStage;

		primaryStage.setTitle(AppSettings.getStringValue("app.title.text"));
		primaryStage.initStyle(StageStyle.TRANSPARENT);

		root = new StackPane();
		root.getStyleClass().add("root");

		Scene scene = new Scene(root,
				AppSettings.getDoubleValue("app.window.width"),
				AppSettings.getDoubleValue("app.window.height"),
				Color.TRANSPARENT);

		File mainStyle = new File("resources/style/style.css");
		File windowButtonStyle = new File("resources/style/window-buttons.css");
		scene.getStylesheets().add(mainStyle.toURI().toURL().toExternalForm());
		scene.getStylesheets().add(
				windowButtonStyle.toURI().toURL().toExternalForm());

		BorderPane pane = new BorderPane();
		Header header = new Header();
		Footer footer = new Footer();
		Body body = new Body();

		pane.setTop(header);
		pane.setCenter(body);
		pane.setBottom(footer);

		root.getChildren().addAll(pane);

		primaryStage.setScene(scene);
		primaryStage.show();

		fullName = new NamePopup(primaryStage, "Name");

		enableDragging();
	}

	public void showFullNamePopup() {
		fullName.show();
	}

	public void getFullName() {
		fullName.toString();
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
