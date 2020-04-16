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
import model.services.ProductServices;

public class ProductRegisterFormController implements Initializable {

	private Product entity;

	private ProductServices service;

	private List<DataChangeListeners> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldIdProvider;

	@FXML
	private TextField textFieldName;

	@FXML
	private TextField textFieldBrand;

	@FXML
	private TextField textFieldModel;

	@FXML
	private TextField textFieldQuantity;

	@FXML
	private TextField textFieldValue;

	@FXML
	private TextField textFieldCategory;

	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonCancel;

	public void setProduct(Product entity) {
		this.entity = entity;
	}

	public void setProductServices(ProductServices service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListeners listener) {
		dataChangeListeners.add(listener);
	}

	public void onBtSaveAction(ActionEvent event) {

		if (entity == null) {
			throw new IllegalStateException("Etidade est· nula");
		}
		if (service == null) {
			throw new IllegalStateException("Servico est· nulo");
		}
		try {
			entity = getFormData();
			service.saverOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("ERRO AO SALVAR DADOS", null, e.getMessage(), AlertType.ERROR);
		}
	}

	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListeners listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	public Product getFormData() {
		Product obj = new Product();
		obj.setId(Utils.tryParseToInt(textFieldId.getText()));
		obj.setIdProvider(Utils.tryParseToInt(textFieldIdProvider.getText()));
		obj.setName(textFieldName.getText().toUpperCase());
		obj.setBrand(textFieldBrand.getText().toUpperCase());
		obj.setModel(textFieldModel.getText().toUpperCase());
		obj.setQuantity(Utils.tryParseToInt(textFieldQuantity.getText()));
		obj.setValue(Utils.tryParseToDouble(textFieldValue.getText()));
		obj.setCategory(textFieldCategory.getText().toUpperCase());
		return obj;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {

		Constraints.setTextFieldInteger(textFieldId);
		Constraints.setTextFieldInteger(textFieldIdProvider);
		Constraints.setTextFieldMaxLength(textFieldName, 20);
		Constraints.setTextFieldMaxLength(textFieldBrand, 15);
		Constraints.setTextFieldMaxLength(textFieldModel, 20);
		Constraints.setTextFieldInteger(textFieldQuantity);
		Constraints.setTextFieldDouble(textFieldValue);
		Constraints.setTextFieldMaxLength(textFieldCategory, 30);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade est√° nula");
		}
		textFieldId.setText(String.valueOf(entity.getId()) );
		textFieldIdProvider.setText(String.valueOf(entity.getIdProvider()));
		textFieldName.setText(entity.getName());
		textFieldBrand.setText(entity.getBrand());
		textFieldModel.setText(entity.getModel());
		textFieldQuantity.setText(String.valueOf(entity.getQuantity()));
		textFieldValue.setText(String.valueOf(entity.getValue()));
		textFieldCategory.setText(entity.getCategory());

	}

}
