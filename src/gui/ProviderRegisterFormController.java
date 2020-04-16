package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListeners;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Provider;
import model.services.ProviderServices;

public class ProviderRegisterFormController implements Initializable {

	private Provider entity;

	private ProviderServices service;

	private List<DataChangeListeners> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldName;

	@FXML
	private TextField textFieldAdress;

	@FXML
	private TextField textFieldTelephone;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private TextField textFieldCnpj;

	@FXML
	private TextField textFieldCpf;

	@FXML
	private TextField textFieldCategory;

	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonCancel;

	public void setProvider(Provider entity) {
		this.entity = entity;
	}

	public void setProviderServices(ProviderServices service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListeners listener) {
		dataChangeListeners.add(listener);
	}

	public void onBtSaveAction(javafx.event.ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Enitade est√° nula");
		}
		if (service == null) {
			throw new IllegalStateException("Servico est√° nulo");
		}
		try {
			Boolean ver = Utils.validEmail(entity.getEmail());
			if (ver == true) {
				entity = getFormData();
				service.saverOrUpdate(entity);
				notifyDataChangeListeners();
				Utils.currentStage(event).close();
			} else {
				Alerts.showAlert("Warning", "Email inv·lido!", "Informe um email v·lido e tente novamente",
						AlertType.WARNING);
			}
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar cadastro", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListeners listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	private Provider getFormData() {
		Provider obj = new Provider();

		obj.setId(Utils.tryParseToInt(textFieldId.getText().toUpperCase()));
		obj.setName(textFieldName.getText().toUpperCase());
		obj.setAdress(textFieldAdress.getText().toUpperCase());
		obj.setTelephone(textFieldTelephone.getText().toUpperCase());
		obj.setEmail(textFieldEmail.getText().toUpperCase());
		obj.setCnpj(textFieldCnpj.getText().toUpperCase());
		obj.setCpf(textFieldCpf.getText().toUpperCase());
		obj.setCategory(textFieldCategory.getText().toUpperCase());

		return obj;
	}

	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(textFieldId);
		Constraints.setTextFieldMaxLength(textFieldName, 60);
		Constraints.setTextFieldMaxLength(textFieldAdress, 100);
		Constraints.setTextFieldMaxLength(textFieldEmail, 40);
		Utils.foneField(textFieldTelephone);
		Utils.cnpjField(textFieldCnpj);
		Utils.cpfField(textFieldCpf);
	} 

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade est· nula");
		}
		textFieldId.setText(String.valueOf(entity.getId()));
		textFieldName.setText(entity.getName());
		textFieldAdress.setText(entity.getAdress());
		textFieldTelephone.setText(entity.getTelephone());
		textFieldEmail.setText(entity.getEmail());
		textFieldCnpj.setText(entity.getCnpj());
		textFieldCpf.setText(entity.getCpf());
		textFieldCategory.setText(entity.getCategory());
	}
}
