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
import model.entities.Product;
import model.entities.Sales;
import model.services.ProductServices;
import model.services.SalesService;

public class SalesRegisterFormController implements Initializable {

	private Sales entity;

	private ProductServices serviceP;

	private SalesService serviceS;

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
	private TextField textFieldIdProduct;

	public void setSales(Sales entity) {
		this.entity = entity;
	}

	public void setSalesService(SalesService serviceS) {
		this.serviceS = serviceS;
	}

	public void setServiceP(ProductServices serviceP) {
		this.serviceP = serviceP;
	}

	public void subscribeDataChangeListener(DataChangeListeners listener) {
		dataChangeListeners.add(listener);
	}

	public void onButtonSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Enitade est√° nula");
		}
		if (serviceS == null) {
			throw new IllegalStateException("Servico est√° nulo");
		}
		try {
			entity = getFormData();
			Boolean ver = Utils.validDate(entity.getDateSale());
			if (ver == true) {
				serviceS.saveOrUpdate(entity);
				updateQuantity(entity);
				notifyDataChangeListeners();
				Utils.currentStage(event).close();
			} else {
				Alerts.showAlert("Warning", "Data inv·lida!", "Insira uma dat v·lida e tente novamente",
						AlertType.WARNING);
			}
		} catch (DbException e) {
			Alerts.showAlert("ERRO AO SALVAR DADOS", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void updateQuantity(Sales entity) {
		Product obj = new Product();
		obj = serviceP.searchId(entity.getIdProduct());
		serviceP.updateProductQuantity(obj);
	}

	public void onButtonCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListeners listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	public Sales getFormData() {
		Sales obj = new Sales();
		obj.setId(Utils.tryParseToInt(textFieldId.getText()));
		obj.setDateSale(textFieldDate.getText());
		obj.setIdClient(Utils.tryParseToInt(textFieldIdClient.getText()));
		obj.setIdProduct(Utils.tryParseToInt(textFieldIdProduct.getText()));
		return obj;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		Constraints.setTextFieldInteger(textFieldId);
		Utils.dateField(textFieldDate);
		Constraints.setTextFieldInteger(textFieldIdClient);
		Constraints.setTextFieldInteger(textFieldIdProduct);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade nula!");
		}
		textFieldId.setText(String.valueOf(entity.getId()));
		textFieldDate.setText(entity.getDateSale());
		textFieldIdClient.setText(String.valueOf(entity.getIdClient()));
		textFieldIdProduct.setText(String.valueOf(entity.getIdProduct()));

	}

}
