package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Register;
import model.services.RegisterServices;

public class RecoverPasswordController implements Initializable {

	private RegisterServices service;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private Button buttonSubmit;

	@FXML
	private Button buttonCancel;

	public void setRegisterService(RegisterServices service) {
		this.service = service;
	}

	public void onButtonSubmitAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Register obj = new Register();
		obj = service.search(textFieldEmail.getText());
		createDialogFormAlert(obj, "/gui/AlertPasswordView.fxml", parentStage, "Informação");
		Utils.currentStage(event).close();
	}

	public void onButtonCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldMaxLength(textFieldEmail, 60);
		
	}

	public void createDialogFormAlert(Register obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			AlertPasswordController controller = loader.getController();
			controller.setEntity(obj);
			controller.showAlert();
		

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon1.jpg")));
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
