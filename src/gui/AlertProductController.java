package gui;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.entities.Product;

public class AlertProductController {
	/**
	 * Classe controller da tela gr�fica AlertProduct.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private Product entity;

	@FXML
	private Button buttonOkay;

	@FXML
	private Label labelId;

	@FXML
	private Label labelIdProvider;

	@FXML
	private Label labelName;

	@FXML
	private Label labelBrand;

	@FXML
	private Label labelModel;

	@FXML
	private Label labelQuantity;

	@FXML
	private Label labelValue;

	@FXML
	private Label labelCategory;

	/**
	 * M�todo que fecha um evento.
	 * 
	 * @param event - ActionEvent
	 */
	public void onButtonOkayAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	/**
	 * M�todo que exibe na tela as devidas inform��es.
	 */
	public void showData() {
		labelId.setText(String.valueOf(getEntity().getId()));
		labelIdProvider.setText(String.valueOf(getEntity().getIdProvider()));
		labelBrand.setText(getEntity().getBrand());
		labelModel.setText(getEntity().getModel());
		labelName.setText(getEntity().getName());
		labelQuantity.setText(String.valueOf(getEntity().getQuantity()));
		labelValue.setText(String.valueOf(getEntity().getValue()));
		labelCategory.setText(getEntity().getCategory());
	}

	/**
	 * M�todo que retorna o objeto.
	 * 
	 * @return Product
	 */
	public Product getEntity() {
		return entity;
	}

	/**
	 * M�todo que seta o valor do objeto.
	 * 
	 * @param entity - Product
	 */
	public void setEntity(Product entity) {
		this.entity = entity;
	}

}
