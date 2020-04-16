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
import model.entities.Client;
import model.services.ClientServices;

public class ClientRegisterFormController implements Initializable {
	/**
	 * Classe controller da interface ClientRegisterForm.
	 * 
	 */
	private Client entity;

	private ClientServices service;

	private List<DataChangeListeners> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldName;

	@FXML
	private TextField textFieldTelephone;

	@FXML
	private TextField textFieldCpf;

	@FXML
	TextField textFieldAddress;

	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonCancel;

	/**
	 * Método que seta o valor do objeto Cliente.
	 * 
	 * @param entity Client
	 */
	public void setClient(Client entity) {
		this.entity = entity;
	}

	/**
	 * Método que seta o valor do objeto.
	 * 
	 * @param service ClientServices
	 */
	public void setClientServices(ClientServices service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListeners listener) {
		dataChangeListeners.add(listener);
	}

	/**
	 * Método que salva um objeto no BD a partir do clique do butão.
	 * 
	 * @param event ActionEvent
	 */
	public void onBtSaveAction(javafx.event.ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entidade está nula");
		}
		if (service == null) {
			throw new IllegalStateException("Servico está nulo");
		}
		try {
			entity = getFormData();
			String strHelp = Utils.withdrawMask(entity.getCpf());
			Boolean ver = Utils.validCPF(strHelp);
			if (ver == true) {
				service.saverOrUpdate(entity);
				notifyDataChangeListeners();
				Utils.currentStage(event).close();
			} else {
				Alerts.showAlert("Warning", "CPF inválido!", "Informe um CPF válido e tente novamnete",
						AlertType.WARNING);
			}
		} catch (DbException e) {
			Alerts.showAlert("ERRO AO SALVAR DADOS", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListeners listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	public Client getFormData() {
		Client obj = new Client();
		obj.setId(Utils.tryParseToInt(textFieldId.getText()));
		obj.setName(textFieldName.getText().toUpperCase());
		obj.setAdress(textFieldAddress.getText().toUpperCase());
		obj.setTelephone(textFieldTelephone.getText().toUpperCase());

		obj.setCpf(textFieldCpf.getText());

		return obj;
	}

	/**
	 * Método que fecha um evento.
	 * 
	 * @param event ActionEvent
	 */
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(textFieldId);
		Constraints.setTextFieldMaxLength(textFieldName, 30);
		Constraints.setTextFieldMaxLength(textFieldAddress, 100);
		Utils.foneField(textFieldTelephone);
		Utils.cpfField(textFieldCpf);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade está nula");
		}
		textFieldId.setText(String.valueOf(entity.getId()));
		textFieldName.setText(entity.getName());
		textFieldAddress.setText(entity.getAdress());
		textFieldTelephone.setText(entity.getTelephone());
		textFieldCpf.setText(entity.getCpf());
	}
}
