package org.apandey.view.popups;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apandey.data.Phone;
import org.apandey.props.AppSettings;
import org.apandey.view.controls.TextBox;
import org.apandey.view.parts.Body;

public class PhonePopup {

	private ComboBox<String> countryCombo;
	private TextBox cityCodeText;
	private TextBox localNumberText;
	private TextBox extensionText;
	private String phoneType;

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

	public PhonePopup(final Stage parentStage, String titleText,
			String phoneType) throws MalformedURLException {

		this.root = new StackPane();
		this.root.autosize();

		this.scene = new Scene(root,
				AppSettings.getDoubleValue("phone.popup.width"),
				AppSettings.getDoubleValue("phone.popup.height"),
				Color.web(AppSettings.getStringValue("phone.popup.bgcolor")));
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

		this.phoneType = phoneType;

		VBox popupContent = new VBox();
		HBox header = initHeader(titleText);
		StackPane content = initContent();

		initActionBox();

		popupContent.getChildren().addAll(header, content, actionBox);
		rootStack.getChildren().add(popupContent);
	}

	public void initActionBox() {

		actionBox = new HBox(24);
		actionBox.setAlignment(Pos.CENTER);
		actionBox.getStyleClass().add("popUpActionBox");
		actionBox.setPadding(new Insets(5, 0, 8, 0));
		Button okBtn = new Button("Submit");
		okBtn.getStyleClass().add("submit-button");
		okBtn.setPrefWidth(75);
		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				setContactNumber();
				closePopUp();
			}
		});
		Button cancelBtn = new Button("Cancel");
		cancelBtn.getStyleClass().add("submit-button");
		cancelBtn.setPrefWidth(75);
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				closePopUp();
			}
		});
		actionBox.getChildren().addAll(okBtn, cancelBtn);
	}

	public StackPane initContent() {

		StackPane content = new StackPane();
		content.setAlignment(Pos.TOP_LEFT);
		content.setPadding(new Insets(8, 5, 5, 5));
		content.getStyleClass().add("popUpBody");
		content.setMinHeight(50);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label phoneTypeLabel = new Label(phoneType);
		phoneTypeLabel.setPrefWidth(AppSettings
				.getDoubleValue("phone.label.width"));
		grid.add(phoneTypeLabel, 0, 0);

		Label countryLabel = new Label(
				AppSettings.getStringValue("country.label.text"));
		countryLabel.setPrefWidth(AppSettings
				.getDoubleValue("phone.label.width"));
		grid.add(countryLabel, 0, 1);

		List<String> countryList = Arrays.asList();
		countryCombo = new ComboBox<>(FXCollections.observableList(countryList));
		countryCombo.setPrefWidth(AppSettings
				.getDoubleValue("phone.input.width"));
		grid.add(countryCombo, 1, 1);

		Label cityCodeLabel = new Label(
				AppSettings.getStringValue("citycode.label.text"));
		cityCodeLabel.setPrefWidth(AppSettings
				.getDoubleValue("phone.label.width"));
		grid.add(cityCodeLabel, 0, 2);

		cityCodeText = new TextBox(Phone.getInstance(phoneType)
				.cityCodeProperty());
		cityCodeText.setPrefWidth(AppSettings
				.getDoubleValue("phone.input.width"));
		grid.add(cityCodeText, 1, 2);

		Label localNumberLabel = new Label(
				AppSettings.getStringValue("localnumber.label.text"));
		localNumberLabel.setPrefWidth(AppSettings
				.getDoubleValue("phone.label.width"));
		grid.add(localNumberLabel, 0, 3);

		localNumberText = new TextBox(Phone.getInstance(phoneType)
				.localNumberProperty());
		localNumberText.setPrefWidth(AppSettings
				.getDoubleValue("phone.input.width"));
		grid.add(localNumberText, 1, 3);

		Label extensionLabel = new Label(
				AppSettings.getStringValue("extension.label.text"));
		extensionLabel.setPrefWidth(AppSettings
				.getDoubleValue("phone.label.width"));
		grid.add(extensionLabel, 0, 4);

		extensionText = new TextBox(Phone.getInstance(phoneType)
				.extensionProperty());
		extensionText.setPrefWidth(AppSettings
				.getDoubleValue("phone.input.width"));
		grid.add(extensionText, 1, 4);

		content.getChildren().add(grid);
		return content;
	}

	public HBox initHeader(String titleText) {

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

		return header;
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

	private void setContactNumber() {

		Phone.getInstance(phoneType).setCountryCode("+91");
		Phone.getInstance(phoneType).setCityCode(cityCodeText.getText());
		Phone.getInstance(phoneType).setLocalNumber(localNumberText.getText());
		Phone.getInstance(phoneType).setExtension(extensionText.getText());

		Body.getInstance().setPhoneNumbers(
				Phone.getInstance(phoneType).getNumber());
	}
}
