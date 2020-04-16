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
import model.entities.ServiceSales;
import model.services.SalesServiceServices;

public class SalesServiceRegisterFormController implements Initializable {

	private ServiceSales entity;

	private SalesServiceServices service;

	private List<DataChangeListeners> dataChangeListeners = new ArrayList<>();

	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonCancel;

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldDate;

	@FXML
	private TextField textFieldIdClient;

	@FXML
	private TextField textFieldIdService;

	public void setSalesService(ServiceSales entity) {
		this.entity = entity;
	}

	public void setSalesService(SalesServiceServices service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListeners listener) {
		dataChangeListeners.add(listener);
	}

	public void onButtonSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entidade está nula");
		}
		if (service == null) {
			throw new IllegalStateException("Servico está nulo");
		}
		try {
			entity = getFormData();
			Boolean ver = Utils.validDate(entity.getDateSale());
			if (ver == true) {
				service.saveOrUpdate(entity);
				notifyDataChangeListeners();
				Utils.currentStage(event).close();
			} else {
				Alerts.showAlert("Warning", "Data inválida!", "Informe uma data válida e tente novamnete",
						AlertType.WARNING);
			}
		} catch (DbException e) {
			Alerts.showAlert("ERRO AO SALVAR DADOS", null, e.getMessage(), AlertType.ERROR);
		}
	}

	public void onButtonCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListeners listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	public ServiceSales getFormData() {
		ServiceSales obj = new ServiceSales();
		obj.setId(Utils.tryParseToInt(textFieldId.getText()));
		obj.setDateSale(textFieldDate.getText());
		obj.setIdClient(Utils.tryParseToInt(textFieldIdClient.getText()));
		obj.setIdService(Utils.tryParseToInt(textFieldIdService.getText()));
		return obj;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldInteger(textFieldId);
		Utils.dateField(textFieldDate);
		Constraints.setTextFieldInteger(textFieldIdClient);
		Constraints.setTextFieldInteger(textFieldIdService);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade nula!");
		}
		textFieldId.setText(String.valueOf(entity.getId()));
		textFieldDate.setText(entity.getDateSale());
		textFieldIdClient.setText(String.valueOf(entity.getIdClient()));
		textFieldIdService.setText(String.valueOf(entity.getIdService()));
	}

}
