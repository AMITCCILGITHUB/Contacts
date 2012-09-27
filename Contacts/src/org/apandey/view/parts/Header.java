package org.apandey.view.parts;

import javafx.event.EventHandler;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import org.apandey.Contacts;
import org.apandey.props.AppSettings;
import org.apandey.utils.WindowButtons;

public class Header extends ToolBar {

	private WindowButtons windowButtons;
	private double mouseDragOffsetX = 0;
	private double mouseDragOffsetY = 0;

	public Header() {

		this.getStyleClass().add("main-tool-bar");

		initComponents();

		initMovement();
	}

	private void initMovement() {

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {
					windowButtons.toogleMaximized();
				}
			}
		});

		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				mouseDragOffsetX = event.getSceneX();
				mouseDragOffsetY = event.getSceneY();
			}
		});

		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (!windowButtons.isMaximized()) {
					Contacts.getInstance().getPrimaryStage()
							.setX(event.getScreenX() - mouseDragOffsetX);
					Contacts.getInstance().getPrimaryStage()
							.setY(event.getScreenY() - mouseDragOffsetY);
				}
			}
		});

	}

	private void initComponents() {

		this.setPrefHeight(AppSettings.getIntegerValue("header.min.height"));
		this.setMinHeight(AppSettings.getIntegerValue("header.min.height"));
		this.setMaxHeight(AppSettings.getIntegerValue("header.min.height"));

		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		this.getItems().add(spacer);

		windowButtons = new WindowButtons(Contacts.getInstance()
				.getPrimaryStage());

		this.getItems().addAll(windowButtons);
	}
}
