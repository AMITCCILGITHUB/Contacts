package org.apandey.view.parts;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.apandey.Contacts;
import org.apandey.props.AppSettings;

public class Body extends VBox {
	private static Body body;
	private TextField fullNameText;
	private TextField companyNameText;
	private TextField jobTitleText;
	private ComboBox<String> prefNameCombo;

	private TextField emailText;
	private TextField displayEmailText;
	private TextField webpageAddressText;
	private TextField imAddressText;

	private Body() {
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

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Button nameButton = new Button("Full Name");
		nameButton.setPrefWidth(AppSettings.getDoubleValue("body.label.width"));
		grid.add(nameButton, 0, 0);

		nameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Contacts.getInstance().showFullNamePopup();
			}

		});

		fullNameText = new TextField();
		fullNameText.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid.add(fullNameText, 1, 0);

		Label companyLabel = new Label("Company");
		companyLabel.setPrefWidth(AppSettings
				.getDoubleValue("body.label.width"));
		grid.add(companyLabel, 0, 1);

		companyNameText = new TextField();
		companyNameText.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid.add(companyNameText, 1, 1);

		Label jobTitleLabel = new Label("Job Title");
		jobTitleLabel.setPrefWidth(AppSettings
				.getDoubleValue("body.label.width"));
		grid.add(jobTitleLabel, 0, 2);

		jobTitleText = new TextField();
		jobTitleText.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid.add(jobTitleText, 1, 2);

		Label prefNameLabel = new Label("File As");
		prefNameLabel.setPrefWidth(AppSettings
				.getDoubleValue("body.label.width"));
		grid.add(prefNameLabel, 0, 3);

		prefNameCombo = new ComboBox<>();
		prefNameCombo.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid.add(prefNameCombo, 1, 3);

		Label emailLabel = new Label("E-mail");
		emailLabel.setPrefWidth(AppSettings.getDoubleValue("body.label.width"));
		grid.add(emailLabel, 0, 4);

		emailText = new TextField();
		emailText.setPrefWidth(AppSettings.getDoubleValue("body.input.width"));
		grid.add(emailText, 1, 4);

		Label displayEmailLabel = new Label("Display As");
		displayEmailLabel.setPrefWidth(AppSettings
				.getDoubleValue("body.label.width"));
		grid.add(displayEmailLabel, 0, 5);

		displayEmailText = new TextField();
		displayEmailText.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid.add(displayEmailText, 1, 5);

		Label webpageAddressLabel = new Label("Webpage Address");
		webpageAddressLabel.setPrefWidth(AppSettings
				.getDoubleValue("body.label.width"));
		grid.add(webpageAddressLabel, 0, 6);

		webpageAddressText = new TextField();
		webpageAddressText.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid.add(webpageAddressText, 1, 6);

		Label imAddressLabel = new Label("E-mail");
		imAddressLabel.setPrefWidth(AppSettings
				.getDoubleValue("body.label.width"));
		grid.add(imAddressLabel, 0, 7);

		imAddressText = new TextField();
		imAddressText.setPrefWidth(AppSettings
				.getDoubleValue("body.input.width"));
		grid.add(imAddressText, 1, 7);

		getChildren().addAll(grid);
	}

	public String getFullName() {
		return fullNameText.getText();
	}

	public void setFullName(String fullNameText) {
		this.fullNameText.setText(fullNameText);
	}

	public List<String> getPrefNames() {
		return prefNameCombo.getItems();
	}

	public void setPrefNames(List<String> fullNameText) {
		prefNameCombo.getItems().setAll(fullNameText);
	}
}
