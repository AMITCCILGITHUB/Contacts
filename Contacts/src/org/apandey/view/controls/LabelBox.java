package org.apandey.view.controls;

import javafx.scene.control.Label;

import org.apandey.props.AppSettings;

public class LabelBox extends Label {
	public LabelBox(String text) {
		setText(text);

		setMinWidth(AppSettings.getDoubleValue("body.label.width"));
		setPrefWidth(AppSettings.getDoubleValue("body.label.width"));
		setMaxWidth(AppSettings.getDoubleValue("body.label.width"));
	}
}
