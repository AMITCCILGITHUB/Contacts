package org.apandey.utils;

import javafx.stage.Stage;

public class Alert extends MessagePopUp {

    private Alert(Stage parentStage, String title, String type, String message) {
        super(parentStage, title, type, message);
    }

    private Alert(Stage parentStage, String title, String type, String message, double width) {
        super(parentStage, title, type, message, width);
    }

    public static void showAlert(Stage parentStage, String title, String type, String message) {
        new Alert(parentStage, title, type, message);
    }

    public static void showAlert(Stage parentStage, String title, String type, String message, double width) {
        new Alert(parentStage, title, type, message, width);
    }
}
