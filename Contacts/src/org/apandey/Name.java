package org.apandey;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apandey.props.AppSettings;

public class Name {
	private Stage stage;
	private Scene scene;
	private StackPane root;
	private HBox actionBox;
	private double startDragX;
	private double startDragY;
	private SimpleDoubleProperty wrapWidth = new SimpleDoubleProperty(
			AppSettings.getDoubleValue("name.popup.width") - 58d);
	private StackPane mask = StackPaneBuilder
			.create()
			.style(AppSettings
					.getStringValue("-fx-background-color:black; -fx-opacity:.2; -fx-background-radius: 10px;"))
			.build();

	public Name(final Stage parentStage, String titleText)
			throws MalformedURLException {
		this.root = new StackPane();
		this.root.autosize();

		this.scene = new Scene(root,
				AppSettings.getDoubleValue("name.popup.width"),
				AppSettings.getDoubleValue("name.popup.height"),
				Color.web(AppSettings.getStringValue("name.popup.bgcolor")));
		File nameStyle = new File("resources/style/name.css");
		this.scene.getStylesheets().add(
				nameStyle.toURI().toURL().toExternalForm());
		this.scene.setFill(Color.TRANSPARENT);

		this.stage = new Stage();
		this.stage.setScene(scene);
		this.stage.initOwner(parentStage);
		this.stage.initModality(Modality.APPLICATION_MODAL);
		this.stage.setTitle(titleText);
		this.stage.initStyle(StageStyle.TRANSPARENT);

		Group mainRoot = new Group();
		StackPane rootStack = new StackPane() {

			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				stage.setWidth(getWidth());
				stage.setHeight(getHeight() + 20);
			}
		};

		rootStack.autosize();
		rootStack.getStyleClass().add("popUpStage");
		mainRoot.getChildren().add(rootStack);

		this.root.setPadding(new Insets(0, 0, 0, 0));
		this.root.getChildren().add(mainRoot);

		VBox vb = new VBox();

		HBox header = new HBox();
		header.setAlignment(Pos.CENTER_LEFT);
		header.getStyleClass().add("popUpHeader");
		header.setPrefHeight(28);
		Button closeBtn = new Button();
		closeBtn.getStyleClass().add("close-button");
		closeBtn.setPrefSize(16, 16);
		closeBtn.setMinSize(16, 16);
		closeBtn.setMaxSize(16, 16);
		closeBtn.setCursor(Cursor.HAND);
		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				closePopUp();
			}
		});

		StackPane sp = new StackPane();
		sp.getChildren().add(closeBtn);
		sp.setAlignment(Pos.CENTER_RIGHT);
		Label titleLbl = new Label(titleText);
		titleLbl.getStyleClass().add("popUpHeaderLbl");

		header.getChildren().addAll(titleLbl, sp);
		HBox.setHgrow(sp, Priority.ALWAYS);
		addDragListeners(header);

		StackPane content = new StackPane();
		content.setAlignment(Pos.TOP_LEFT);
		content.setPadding(new Insets(8, 5, 5, 5));
		content.getStyleClass().add("popUpBody");
		content.setMinHeight(50);

		VBox components = new VBox(12);

		HBox title = new HBox();
		Label titleLabel = new Label(
				AppSettings.getStringValue("title.label.text"));
		titleLabel.setPrefWidth(AppSettings.getDoubleValue("name.label.width"));
		List<String> titleList = Arrays.asList(AppSettings.getStringValue(
				"title.combo.list").split(","));
		ComboBox<String> titleCombo = new ComboBox<>(
				FXCollections.observableList(titleList));
		titleCombo.setPrefWidth(AppSettings.getDoubleValue("name.input.width"));
		title.getChildren().addAll(titleLabel, titleCombo);

		HBox firstName = new HBox();
		Label firstNameLabel = new Label(
				AppSettings.getStringValue("firstname.label.text"));
		firstNameLabel.setPrefWidth(AppSettings
				.getDoubleValue("name.label.width"));
		TextField firstNameText = new TextField();
		firstNameText.setPrefWidth(AppSettings
				.getDoubleValue("name.input.width"));
		firstName.getChildren().addAll(firstNameLabel, firstNameText);

		HBox middleName = new HBox();
		Label middleNameLabel = new Label(
				AppSettings.getStringValue("middlename.label.text"));
		middleNameLabel.setPrefWidth(AppSettings
				.getDoubleValue("name.label.width"));
		TextField middleNameText = new TextField();
		middleNameText.setPrefWidth(AppSettings
				.getDoubleValue("name.input.width"));
		middleName.getChildren().addAll(middleNameLabel, middleNameText);

		HBox lastName = new HBox();
		Label lastNameLabel = new Label(
				AppSettings.getStringValue("lastname.label.text"));
		lastNameLabel.setPrefWidth(AppSettings
				.getDoubleValue("name.label.width"));
		TextField lastNameText = new TextField();
		lastNameText.setPrefWidth(AppSettings
				.getDoubleValue("name.input.width"));
		lastName.getChildren().addAll(lastNameLabel, lastNameText);

		HBox suffix = new HBox();
		Label suffixLabel = new Label(
				AppSettings.getStringValue("suffix.label.text"));
		suffixLabel
				.setPrefWidth(AppSettings.getDoubleValue("name.label.width"));
		List<String> suffixList = Arrays.asList(AppSettings.getStringValue(
				"suffix.combo.list").split(","));
		ComboBox<String> suffixCombo = new ComboBox<>(
				FXCollections.observableList(suffixList));
		suffixCombo
				.setPrefWidth(AppSettings.getDoubleValue("name.input.width"));
		suffix.getChildren().addAll(suffixLabel, suffixCombo);

		components.getChildren().addAll(title, firstName, middleName, lastName,
				suffix);
		content.getChildren().add(components);

		actionBox = new HBox();
		actionBox.setAlignment(Pos.CENTER);
		actionBox.getStyleClass().add("popUpActionBox");
		actionBox.setPadding(new Insets(5, 0, 8, 0));
		Button okBtn = new Button("Ok");
		okBtn.getStyleClass().add("submit-button");
		okBtn.setPrefWidth(75);
		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				closePopUp();
			}
		});
		actionBox.getChildren().add(okBtn);

		vb.getChildren().addAll(header, content, actionBox);
		rootStack.getChildren().add(vb);

		parentStage.xProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				stage.setX(Double.valueOf(arg2.toString()));
			}
		});

		parentStage.yProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				stage.setY(Double.valueOf(arg2.toString())
						+ parentStage.getScene().getHeight() + 20);
			}
		});
	}

	protected Stage getStage() {
		return this.stage;
	}

	public double getWrapWidth() {
		return wrapWidth.get();
	}

	public final void setWrapWidth(double wrapWidth) {
		this.wrapWidth.set(wrapWidth);
	}

	public SimpleDoubleProperty wrapWidthProperty() {
		return wrapWidth;
	}

	public HBox getActionBox() {
		return actionBox;
	}

	public final void addDragListeners(final Node n) {
		n.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				startDragX = me.getSceneX();
				startDragY = me.getSceneY();
				root.setStyle("-fx-opacity:.7;");
			}
		});
		n.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				root.setStyle("-fx-opacity:1;");
			}
		});
		n.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {
				stage.setX(me.getScreenX() - startDragX);
				stage.setY(me.getScreenY() - startDragY);
			}
		});
	}

	public void closePopUp() {
		Parent parentRoot = ((Stage) stage.getOwner()).getScene().getRoot();
		if (parentRoot instanceof StackPane) {
			((StackPane) parentRoot).getChildren().remove(mask);
		}
		stage.close();
	}

	public final void show() {
		StackPane parentRoot = (StackPane) ((Stage) stage.getOwner())
				.getScene().getRoot();
		parentRoot.getChildren().add(mask);
		stage.show();
	}
}
