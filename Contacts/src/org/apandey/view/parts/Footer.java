package org.apandey.view.parts;

import javafx.scene.layout.HBox;

import org.apandey.props.AppSettings;

public class Footer extends HBox {
	public Footer() {
		initComponents();
	}

	private void initComponents() {
		this.setMinHeight(AppSettings.getIntegerValue("footre.min.height"));
	}
}
