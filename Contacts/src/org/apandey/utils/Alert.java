package org.apandey.utils;

import java.net.MalformedURLException;

import javafx.stage.Stage;

public class Alert extends MessagePopUp {

	private Alert(Stage parentStage, String title, String type, String message)
			throws MalformedURLException {
		super(parentStage, title, type, message);
	}

	private Alert(Stage parentStage, String title, String type, String message,
			double width) throws MalformedURLException {
		super(parentStage, title, type, message, width);
	}

	public static void showAlert(Stage parentStage, String title, String type,
			String message) {
		try {
			new Alert(parentStage, title, type, message);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void showAlert(Stage parentStage, String title, String type,
			String message, double width) {
		try {
			new Alert(parentStage, title, type, message, width);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
