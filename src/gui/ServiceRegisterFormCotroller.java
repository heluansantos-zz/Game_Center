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
import model.entities.Service;
import model.services.ServiceServices;

public class ServiceRegisterFormCotroller implements Initializable {

	private Service entity;

	private ServiceServices service;

	private List<DataChangeListeners> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldIdClient;

	@FXML
	private TextField textFieldName;

	@FXML
	private TextField textFieldDescription;

	@FXML
	private TextField textFieldStatus;

	@FXML
	private TextField textFieldValue;

	@FXML
	private TextField textFieldWarrant;

	@FXML
	private TextField textFieldDate;

	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonCancel;

	/**
	 * 
	 * @param entity
	 */
	public void setService(Service entity) {
		this.entity = entity;
	}

	/**
	 * 
	 * @param service
	 */
	public void setServiceServices(ServiceServices service) {
		this.service = service;
	}

	/**
	 * 
	 * @param listener
	 */
	public void subscribeDataChangeListener(DataChangeListeners listener) {
		dataChangeListeners.add(listener);
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
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("ERRO AO SALVAR DADOS", null, e.getMessage(), AlertType.ERROR);

		}
	}

	/**
	 * 
	 */
	private void notifyDataChangeListeners() {
		for (DataChangeListeners listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void onButtonCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	/**
	 * 
	 * @return {@link Service}
	 */
	public Service getFormData() {
		Service obj = new Service();

		obj.setId(Utils.tryParseToInt(textFieldId.getText()));
		obj.setIdClient(Utils.tryParseToInt(textFieldIdClient.getText()));
		obj.setName(textFieldName.getText().toUpperCase());
		obj.setProblemDescription(textFieldDescription.getText().toUpperCase());
		obj.setDate(textFieldDate.getText());
		obj.setStatus(textFieldStatus.getText().toUpperCase());
		obj.setValue(Utils.tryParseToDouble(textFieldValue.getText()));
		obj.setWarranty(textFieldWarrant.getText().toUpperCase());

		return obj;
	}
 
	/**
	 * 
	 */
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade est� nula");
		}
		textFieldId.setText(String.valueOf(entity.getId()));
		textFieldIdClient.setText(String.valueOf(entity.getIdClient()));
		textFieldName.setText(entity.getName());
		textFieldDescription.setText(entity.getProblemDescription());
		textFieldDate.setText(entity.getDate());
		textFieldStatus.setText(entity.getStatus());
		textFieldValue.setText(String.valueOf(entity.getValue()));
		textFieldWarrant.setText(entity.getWarranty());
	}

	/**
	 * 
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldInteger(textFieldId);
		Constraints.setTextFieldInteger(textFieldIdClient);
		Constraints.setTextFieldMaxLength(textFieldName, 30);
		Constraints.setTextFieldMaxLength(textFieldDescription, 30);
		Utils.dateField(textFieldDate);
		Constraints.setTextFieldDouble(textFieldValue);
	}

}
