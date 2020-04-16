package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.LoadView;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Register;
import model.services.RegisterServices;

public class MainViewController implements Initializable {

	@SuppressWarnings("unused")
	private RegisterServices service = null;
	
	@FXML
	private Label label;
	
	@FXML
	private Button buttonLogin;

	@FXML
	private Button buttonRegister;

	@FXML
	private Button buttonRecoverPassword;
	
	LoadView load = new LoadView();

	@FXML
	public void onButtonLoginAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Register obj = new Register();
		createDialogFormLogin(obj,"/gui/LoginViewForm.fxml", parentStage, "Login");
	}

	@FXML
	public void onButtonRegisterAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Register obj = new Register();
		createDialogFormRegister(obj,"/gui/RegisterFormView.fxml", parentStage, "Entre com os dados");
	}
	
	@FXML
	public void onButtonRecoverPasswodAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Register obj = new Register();
		createDialogFormRecoverPassword(obj,"/gui/RecoverPasswordView.fxml" , parentStage, "Recuperar senha");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	

	}
	public void createDialogFormRegister(Register obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
		
			RegisterViewFormController controller = loader.getController();
			controller.setRegister(obj);
			controller.setRegisterService(new RegisterServices());
			
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/register.png")));
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
	
	public void createDialogFormLogin(Register obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
		
			LoginViewFormController controller = loader.getController();
			controller.setRegisterService(new RegisterServices());
			
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/administrador.png")));
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
	public void createDialogFormRecoverPassword(Register obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
		
			RecoverPasswordController controller = loader.getController();
			controller.setRegisterService(new RegisterServices());
			
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/recover.png")));
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

	public void setRegisterService(RegisterServices service) {
		this.service = service;
	}

}
