package org.apandey.view.controls;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

import org.apandey.props.AppSettings;

public class TextBox extends TextField {

	public TextBox() {

		setMinWidth(AppSettings.getDoubleValue("body.input.width"));
		setPrefWidth(AppSettings.getDoubleValue("body.input.width"));
		setMaxWidth(AppSettings.getDoubleValue("body.input.width"));
	}

	public TextBox(String textValue) {

		setText(textValue);

		setMinWidth(AppSettings.getDoubleValue("body.input.width"));
		setPrefWidth(AppSettings.getDoubleValue("body.input.width"));
		setMaxWidth(AppSettings.getDoubleValue("body.input.width"));
	}

	public TextBox(SimpleStringProperty textProperty) {

		textProperty().bindBidirectional(textProperty);

		setMinWidth(AppSettings.getDoubleValue("body.input.width"));
		setPrefWidth(AppSettings.getDoubleValue("body.input.width"));
		setMaxWidth(AppSettings.getDoubleValue("body.input.width"));
	}
}
