package gui;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.entities.ServiceSales;

public class DataSalesServiceController {

	private ServiceSales entity;
	
	@FXML
	private Button buttonOk;
	
	@FXML
	Label labelIdSalesService;
	
	@FXML
	Label labelNameClient;
	
	@FXML
	Label labelTelephone;
	
	@FXML
	Label labelCpf;
	
	@FXML
	Label labelDescriptionService;
	
	@FXML
	Label labelWarranty;
	
	@FXML
	Label labelValue;
	
	@FXML
	Label labelDate;

	public ServiceSales getEntity() {
		return entity;
	}

	public void setEntity(ServiceSales entity) {
		this.entity = entity;
	}
	 
	 public void showData() {
		if(getEntity() != null) {
			labelIdSalesService.setText(String.valueOf(getEntity().getId()));
			labelNameClient.setText(getEntity().getClient().getName());
			labelTelephone.setText(getEntity().getClient().getTelephone());
			labelCpf.setText(getEntity().getClient().getCpf());
			labelDescriptionService.setText(getEntity().getService().getName());
			labelWarranty.setText(getEntity().getService().getWarranty());
			labelValue.setText(String.valueOf(getEntity().getService().getValue()));
			labelDate.setText(getEntity().getDateSale());
		}
	 }
	 public void onButtonOkAction(ActionEvent event) {
		Utils.currentStage(event).close();
	 }
}
