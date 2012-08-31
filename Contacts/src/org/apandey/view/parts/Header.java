package org.apandey.view.parts;

import javafx.scene.layout.HBox;

import org.apandey.Contacts;
import org.apandey.props.AppSettings;
import org.apandey.utils.WindowButtons;

public class Header extends HBox {
	public Header() {
		initComponents();
	}

	private void initComponents() {
		this.setMinHeight(AppSettings.getIntegerValue("header.min.height"));

		final WindowButtons windowButtons = new WindowButtons(Contacts
				.getInstance().getPrimaryStage());

		this.getChildren().addAll(windowButtons);
	}
}
