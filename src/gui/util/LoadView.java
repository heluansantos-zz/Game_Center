package gui.util;

import java.io.IOException;
import java.util.function.Consumer;

import application.Main;
import gui.ClientViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.services.ClientServices;

public class LoadView {

	public synchronized <T> void loadView(String absoluteName, Consumer<T> initializengAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			mainVbox.setFillWidth(true);
			mainVbox.setFillWidth(true);
			Node mainMenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(newVBox.getChildren());

			T controller = loader.getController();
			initializengAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	public synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			mainVbox.setFillWidth(true);
			mainVbox.setFillWidth(true);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().addAll(newVBox.getChildren());

			ClientViewController controller = loader.getController();
			controller.setClientServices(new ClientServices());
			controller.updateTableView();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	public synchronized void loadView3(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			mainVbox.setFillWidth(true);
			mainVbox.setFillWidth(true);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().addAll(newVBox.getChildren());
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	public void createDialogForm(String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle(title);
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
