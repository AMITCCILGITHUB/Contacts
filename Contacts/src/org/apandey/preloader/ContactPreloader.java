package org.apandey.preloader;

import java.io.IOException;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContactPreloader extends Preloader {

	Stage stage;
	Loading loading;

	private Scene createPreloaderScene() {

		loading = new Loading();
		try {
			loading.buildComponents();
		} catch (IOException ex) {
		}
		return loading.getScene();
	}

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		stage.setScene(createPreloaderScene());
		stage.show();
		loading.animate();
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification scn) {

		System.out.println(scn.getType());
		if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
			stage.hide();
		}

	}

	@Override
	public void handleProgressNotification(ProgressNotification pn) {

		loading.update(pn.getProgress());

	}

}
