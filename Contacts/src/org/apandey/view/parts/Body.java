package org.apandey.view.parts;

import java.io.File;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPaneBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.apandey.Contacts;
import org.apandey.data.Address;
import org.apandey.data.Name;
import org.apandey.data.Phone;
import org.apandey.props.AppSettings;
import org.apandey.view.controls.LabelBox;
import org.apandey.view.controls.TextBox;

public class Body extends VBox {

	private static Body body;
	private TextBox fullNameText;
	private TextBox companyNameText;
	private TextBox jobTitleText;
	private ComboBox<String> prefNameCombo;

	private TextBox emailText;
	private TextBox displayEmailText;
	private TextBox webpageAddressText;
	private TextBox imAddressText;

	private TextBox phoneNumberText;

	private TextBox addressText;

	private Body() {

		super(10);

		initComponents();
	}

	public static Body getInstance() {

		if (body == null) {
			body = new Body();
		}

		return body;

	}

	private void initComponents() {

		getStyleClass().add("body");

		HBox hbox1 = new HBox(10);
		GridPane grid1 = new GridPane();
		grid1.setHgap(10);
		grid1.setVgap(10);
		grid1.getStyleClass().add("grid-region");
		grid1.setPadding(new Insets(10, 10, 10, 10));

		Button nameButton = new Button("Full Name");
		nameButton.getStyleClass().add("submit-button");
		nameButton.setPrefWidth(AppSettings.getDoubleValue("body.label.width"));
		grid1.add(nameButton, 0, 0);

		fullNameText = new TextBox();
		grid1.add(fullNameText, 1, 0);

		LabelBox companyLabel = new LabelBox("Company");
		grid1.add(companyLabel, 0, 1);

		companyNameText = new TextBox();
		grid1.add(companyNameText, 1, 1);

		LabelBox jobTitleLabel = new LabelBox("Job Title");
		grid1.add(jobTitleLabel, 0, 2);

		jobTitleText = new TextBox();
		grid1.add(jobTitleText, 1, 2);

		LabelBox prefNameLabel = new LabelBox("File As");
		grid1.add(prefNameLabel, 0, 3);

		prefNameCombo = new ComboBox<>();
		prefNameCombo.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid1.add(prefNameCombo, 1, 3);

		StackPane hBoxPhoto = new StackPane();
		hBoxPhoto.getStyleClass().add("photo");
		File photoFile = new File("resources/images/photo.png");
		final ImageView image = new ImageView();
		try {
			image.setImage(new Image(photoFile.toURI().toURL().toString()));
		} catch (Exception ex) {
		}
		image.setPreserveRatio(true);
		image.setFitWidth(AppSettings.getDoubleValue("photo.input.width"));
		image.setFitHeight(AppSettings.getDoubleValue("photo.input.height"));
		GridPane.setRowSpan(hBoxPhoto, 4);
		hBoxPhoto.getChildren().add(image);
		grid1.add(hBoxPhoto, 2, 0);

		GridPane grid11 = new GridPane();
		grid11.setHgap(10);
		grid11.setVgap(10);
		grid11.getStyleClass().add("grid-region");
		grid11.setPadding(new Insets(10, 10, 10, 10));

		StackPane vCardStack = new StackPane();
		final ImageView vCardImage = new ImageView();
		vCardImage.setPreserveRatio(true);
		vCardImage.setFitWidth(AppSettings.getDoubleValue("photo.input.width"));
		vCardImage.setFitHeight(AppSettings
				.getDoubleValue("photo.input.height"));
		vCardImage.imageProperty().bind(image.imageProperty());
		final Label vCard = new Label();
		vCard.setPrefSize(AppSettings.getDoubleValue("body.vcard.width"),
				AppSettings.getDoubleValue("body.vcard.height"));
		StackPane.setAlignment(vCardImage, Pos.TOP_RIGHT);
		vCardStack.getChildren().addAll(vCardImage, vCard);
		grid11.add(vCardStack, 0, 0);

		HBox hbox2 = new HBox();
		GridPane grid2 = new GridPane();
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.getStyleClass().add("grid-region");
		grid2.setPadding(new Insets(0, 10, 10, 10));

		LabelBox emailLabel = new LabelBox("E-mail");
		grid2.add(emailLabel, 0, 1);

		emailText = new TextBox();
		grid2.add(emailText, 1, 1);

		LabelBox displayEmailLabel = new LabelBox("Display As");
		grid2.add(displayEmailLabel, 0, 2);

		displayEmailText = new TextBox();
		grid2.add(displayEmailText, 1, 2);

		LabelBox webpageAddressLabel = new LabelBox("Web page Address");
		grid2.add(webpageAddressLabel, 0, 3);

		webpageAddressText = new TextBox();
		grid2.add(webpageAddressText, 1, 3);

		LabelBox imAddressLabel = new LabelBox("IM Address");
		grid2.add(imAddressLabel, 0, 4);

		imAddressText = new TextBox();
		grid2.add(imAddressText, 1, 4);

		HBox hbox3 = new HBox();
		ScrollPane phoneScroll = ScrollPaneBuilder
				.create()
				.minWidth(
						AppSettings.getDoubleValue("body.label.width")
								+ AppSettings
										.getDoubleValue("body.input.width")
								+ 40).minHeight(60).styleClass("grid-region")
				.vbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED).build();
		final GridPane grid3 = new GridPane();
		grid3.setHgap(10);
		grid3.setVgap(10);
		grid3.setPadding(new Insets(10, 10, 10, 10));

		Button phoneButton = new Button("Phone Number");
		phoneButton.getStyleClass().add("submit-button");
		phoneButton
				.setPrefWidth(AppSettings.getDoubleValue("body.label.width"));
		grid3.add(phoneButton, 0, 0);

		phoneNumberText = new TextBox();
		grid3.add(phoneNumberText, 1, 0);

		final Button addPhoneButton = new Button("Add Phone Number");
		addPhoneButton.getStyleClass().add("submit-button");
		addPhoneButton.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid3.add(addPhoneButton, 1, 1);

		phoneNumberText = new TextBox();
		grid3.add(phoneNumberText, 1, 0);
		phoneScroll.setContent(grid3);

		HBox hbox4 = new HBox();

		ScrollPane addressScroll = ScrollPaneBuilder
				.create()
				.minWidth(
						AppSettings.getDoubleValue("body.label.width")
								+ AppSettings
										.getDoubleValue("body.input.width")
								+ 40).minHeight(60).styleClass("grid-region")
				.vbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED).build();
		final GridPane grid4 = new GridPane();
		grid4.setHgap(10);
		grid4.setVgap(10);
		grid4.setPadding(new Insets(10, 10, 10, 10));

		Button addressButton = new Button("Address");
		addressButton.getStyleClass().add("submit-button");
		addressButton.setPrefWidth(AppSettings
				.getDoubleValue("body.label.width"));
		grid4.add(addressButton, 0, 0);

		addressText = new TextBox();
		grid4.add(addressText, 1, 0);

		final Button addAddressButton = new Button("Add Address");
		addAddressButton.getStyleClass().add("submit-button");
		addAddressButton.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid4.add(addAddressButton, 1, 1);
		addressScroll.setContent(grid4);

		hbox1.getChildren().addAll(grid1, grid11);
		hbox2.getChildren().addAll(grid2);
		hbox3.getChildren().addAll(phoneScroll);
		hbox4.getChildren().addAll(addressScroll);

		getChildren().addAll(hbox1, hbox2, hbox3, hbox4);

		fullNameText.focusedProperty().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {

						if (oldValue == true && newValue == false) {
							setFullNameFromTextField(fullNameText.getText());

							setPrefNames(Name.getInstance()
									.getNameCombinations());

							vCard.setText(Name.getInstance()
									.getNameCombinations().get(0)
									+ "\n"
									+ Phone.getInstance("Business").getNumber()
									+ "\n"
									+ Address.getInstance("Business")
											.getAddressDetail());

						}

					}
				});

		image.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				FileChooser fileChooser = new FileChooser();

				ExtensionFilter extFilter1 = new ExtensionFilter(
						"Bitmap Image (*.bmp)", "*.bmp");
				ExtensionFilter extFilter2 = new ExtensionFilter(
						"PNG Image (*.png)", "*.png");
				ExtensionFilter extFilter3 = new ExtensionFilter(
						"JPG Image (*.jpg)", "*.jpg");
				ExtensionFilter extFilter4 = new ExtensionFilter(
						"GIF Image (*.gif)", "*.gif");
				ExtensionFilter extFilter5 = new ExtensionFilter(
						"All files (*.*)", "*.*");
				fileChooser.getExtensionFilters().addAll(extFilter1,
						extFilter2, extFilter3, extFilter4, extFilter5);

				try {
					image.setImage(new Image(fileChooser.showOpenDialog(null)
							.toURI().toURL().toString()));
				} catch (Exception ex) {
				}

			}
		});

		addPhoneButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				Button phoneButton = new Button("Phone Number");
				phoneButton.getStyleClass().add("submit-button");
				phoneButton.setPrefWidth(AppSettings
						.getDoubleValue("body.label.width"));
				grid3.add(phoneButton, 0, Phone.getCount());

				phoneNumberText = new TextBox();

				grid3.add(phoneNumberText, 1, Phone.getCount());

				GridPane.setRowIndex(addPhoneButton, Phone.getCount() + 1);

				phoneButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						Contacts.getInstance().showPhoneNumberPopup();
					}

				});
			}

		});

		addAddressButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				Button addressButton = new Button("Address");
				addressButton.getStyleClass().add("submit-button");
				addressButton.setPrefWidth(AppSettings
						.getDoubleValue("body.label.width"));
				grid4.add(addressButton, 0, Address.getCount());

				addressText = new TextBox();
				grid4.add(addressText, 1, Address.getCount());

				GridPane.setRowIndex(addAddressButton, Phone.getCount() + 1);

				addressButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						Contacts.getInstance().showAddressPopup();
					}

				});
			}

		});

		nameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				Contacts.getInstance().showFullNamePopup();
			}

		});

		phoneButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				Contacts.getInstance().showPhoneNumberPopup();
			}

		});

		addressButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				Contacts.getInstance().showAddressPopup();
			}

		});
	}

	public String getFullName() {

		return fullNameText.getText();
	}

	public void setFullName(String fullNameText) {

		this.fullNameText.setText(fullNameText);
	}

	public String getPhoneNumbers() {

		return phoneNumberText.getText();
	}

	public void setPhoneNumbers(String phoneNumberText) {

		this.phoneNumberText.setText(phoneNumberText);
	}

	public String getAddress() {

		return addressText.getText();
	}

	public void setAddress(String addressText) {

		this.addressText.setText(addressText);
	}

	public List<String> getPrefNames() {

		return prefNameCombo.getItems();
	}

	public void setPrefNames(List<String> fullNameText) {

		prefNameCombo.getItems().setAll(fullNameText);
		if (prefNameCombo.getSelectionModel().getSelectedIndex() == -1) {
			prefNameCombo.getSelectionModel().selectFirst();
		}
	}

	public void setFullNameFromTextField(String fullName) {

		String[] nameParts = fullName.split(" ");

		if (nameParts.length > 0) {
			Name.getInstance().setFirstName(nameParts[0]);
			if (nameParts.length > 1) {
				Name.getInstance().setMiddleName(nameParts[1]);

				for (int index = 2; index < nameParts.length; index++) {
					Name.getInstance()
							.setLastName(
									Name.getInstance().getLastName()
											+ nameParts[index]);
				}
			}
		}
	}
}
