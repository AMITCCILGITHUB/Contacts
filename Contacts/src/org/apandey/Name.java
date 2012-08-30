package org.apandey;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import org.apandey.props.AppSettings;

public class Name extends VBox {
	public Name() {
		initComponents();
	}

	private void initComponents() {
		setVgrow(this, Priority.ALWAYS);

		Label titleLabel = new Label(
				AppSettings.getStringValue("title.label.text"));
		List<String> titleList = Arrays.asList(AppSettings.getStringValue(
				"title.combo.list").split(","));
		ComboBox<String> titleCombo = new ComboBox<>(
				FXCollections.observableList(titleList));

		Label firstNameLabel = new Label(
				AppSettings.getStringValue("firstname.label.text"));
		TextField firstNametext = new TextField();

		Label middleNameLabel = new Label(
				AppSettings.getStringValue("middlename.label.text"));
		TextField middleNametext = new TextField();

		Label lastNameLabel = new Label(
				AppSettings.getStringValue("lastname.label.text"));
		TextField lastNametext = new TextField();

		Label suffixLabel = new Label(
				AppSettings.getStringValue("suffix.label.text"));
		List<String> suffixList = Arrays.asList(AppSettings.getStringValue(
				"suffix.combo.list").split(","));
		ComboBox<String> suffixCombo = new ComboBox<>(
				FXCollections.observableList(suffixList));

		getChildren().addAll(titleLabel, titleCombo, firstNameLabel,
				firstNametext, middleNameLabel, middleNametext, lastNameLabel,
				lastNametext, suffixLabel, suffixCombo);
	}
}
