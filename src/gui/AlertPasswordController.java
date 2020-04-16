package gui;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.entities.Register;

public class AlertPasswordController {
	/**
	 * Classe controller da interface gr�fica AlertPasswordView.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	Register entity;

	@FXML
	private Label labelPassword;

	@FXML
	private Label labelInformation;

	@FXML
	private Button buttonOkay;

	/**
	 * M�todo que retorna um objeto.
	 * 
	 * @return Register
	 */
	public Register getEntity() {
		return entity;
	}

	/**
	 * M�todo que seta o valor do objeto.
	 * 
	 * @param entity - Register
	 */
	public void setEntity(Register entity) {
		this.entity = entity;
	}

	/**
	 * M�todo que exibe na tela as referidas informa��es.
	 */
	public void showAlert() {
		labelInformation.setText("A sua senha e: ");
		labelPassword.setText(getEntity().getPassword());
	}

	/**
	 * M�todo que fecha o evento atual.
	 * 
	 * @param event - ActionEvent
	 */
	public void onbuttonOkayAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
}
