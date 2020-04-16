package gui;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.entities.Provider;

public class DataProviderController {

	private Provider entity;
	@FXML
	
	private Button buttonOkay;

	@FXML
	private Label labelId;

	@FXML
	private Label labelName;

	@FXML
	private Label labelAdress;

	@FXML
	private Label labelTelephone;

	@FXML
	private Label labelEmail;

	@FXML
	private Label labelCnpj;

	@FXML
	private Label labelCpf;

	@FXML
	private Label labelCategory;

	public Provider getEntity() {
		return entity;
	}

	public void setEntity(Provider entity) {
		this.entity = entity;
	}

	public void showDataProvider() {
		labelId.setText(String.valueOf(getEntity().getId()));
		labelName.setText(getEntity().getName());
		labelAdress.setText(getEntity().getAdress());
		labelTelephone.setText(getEntity().getTelephone());
		labelEmail.setText(getEntity().getEmail());
		labelCnpj.setText(getEntity().getCnpj());
		labelCpf.setText(getEntity().getCpf());
		labelCategory.setText(getEntity().getCategory());
	}

	public void onButtonOkayAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

}
