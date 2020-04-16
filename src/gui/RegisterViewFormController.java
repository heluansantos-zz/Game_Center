package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.Register;
import model.services.RegisterServices;

public class RegisterViewFormController implements Initializable {

	private Register entity;

	private RegisterServices service;

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldName;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private PasswordField passwordfieldPassword;

	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonCancel;

	public void setRegister(Register entity) {
		this.entity = entity;
	}

	public void setRegisterService(RegisterServices service) {
		this.service = service;
	}

	public void onButtonSaveAction(ActionEvent event) {

		if (entity == null) {
			throw new IllegalStateException("Enitade está nula");
		}
		if (service == null) {
			throw new IllegalStateException("Servico está nulo");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
            Alerts.showAlert("Cadastro", "Cadastro realizado com sucesso!",null , AlertType.CONFIRMATION);	
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("ERRO AO SALVAR DADOS", null, e.getMessage(), AlertType.ERROR);
		}
	}

	public void onButtonCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	private Register getFormData() {
		Register obj = new Register();
		obj.setName(textFieldName.getText());
		obj.setEmail(textFieldEmail.getText());
		obj.setPassword(passwordfieldPassword.getText());

		return obj;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	private void initializeNodes() {

		Constraints.setTextFieldMaxLength(textFieldName, 30);
		Constraints.setTextFieldMaxLength(textFieldEmail, 40);
		Constraints.setTextFieldMaxLength(passwordfieldPassword, 8);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade está nula");
		}
		textFieldId.setText(String.valueOf(entity.getId()));
		textFieldName.setText(entity.getName());
		textFieldEmail.setText(entity.getEmail());
		passwordfieldPassword.setText(entity.getPassword());
	}

}
