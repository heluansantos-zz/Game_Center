package gui;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.entities.Sales;

public class DataSalesProductController {

	private Sales entity;
	
	@FXML
	private Button buttonOk;
	
	@FXML
	Label labelIdSales;
	
	@FXML
	Label labelNameClient;
	
	@FXML
	Label labelTelephone;
	
	@FXML
	Label labelCpf;
	
	@FXML
	Label labelNameProduct;
	
	@FXML
	Label labelBrand;
	
	@FXML
	Label labelValue;
	
	@FXML
	Label labelDate;

	public Sales getEntity() {
		return entity;
	}

	public void setEntity(Sales entity) {
		this.entity = entity;
	}
	 
	 public void showData() {
		if(getEntity() != null) {
			labelIdSales.setText(String.valueOf(getEntity().getId()));
			labelNameClient.setText(getEntity().getClient().getName());
			labelTelephone.setText(getEntity().getClient().getTelephone());
			labelCpf.setText(getEntity().getClient().getCpf());
			labelNameProduct.setText(getEntity().getProduct().getName());
			labelBrand.setText(getEntity().getProduct().getBrand());
			labelValue.setText(String.valueOf(getEntity().getProduct().getValue()));
			labelDate.setText(getEntity().getDateSale());
		}
	 }
	 public void onButtonOkAction(ActionEvent event) {
		Utils.currentStage(event).close();
	 }
}
