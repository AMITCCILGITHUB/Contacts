package org.apandey;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apandey.props.AppSettings;
import org.apandey.view.parts.Body;
import org.apandey.view.parts.Footer;
import org.apandey.view.parts.Header;
import org.apandey.view.popups.AddressPopup;
import org.apandey.view.popups.NamePopup;
import org.apandey.view.popups.PhonePopup;

public class Contacts extends Application {

	private static Contacts contacts;
	private Stage stage;
	private StackPane root;
	private NamePopup fullName;
	private PhonePopup phoneNumber;
	private AddressPopup addressDetail;

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
		root.getStyleClass().add("root-stack");

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
		pane.getStyleClass().add("root-pane");
		VBox topBox = new VBox(10);
		Header header = new Header();
		Region spacer = new Region();
		VBox.setVgrow(spacer, Priority.ALWAYS);
		topBox.getChildren().addAll(header, spacer);

		Footer footer = new Footer();

		pane.setTop(topBox);
		pane.setCenter(Body.getInstance());
		pane.setBottom(footer);

		root.getChildren().addAll(pane);

		primaryStage.setScene(scene);
		primaryStage.show();

		fullName = new NamePopup(primaryStage, "Name");
		phoneNumber = new PhonePopup(primaryStage, "Contact Details",
				"Business");
		addressDetail = new AddressPopup(primaryStage, "Contact Details",
				"Business");
	}

	public void showFullNamePopup() {

		fullName.show();
	}

	public void showPhoneNumberPopup() {

		phoneNumber.show();
	}

	public void showAddressPopup() {

		addressDetail.show();
	}

	public void getFullName() {

		fullName.toString();
	}

	public void getPhoneNumber() {

		phoneNumber.toString();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
