package gui;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.entities.Client;

public class ClientDataViewController {
	/**
	 * Classe controller da tela gráfica ClientDataView.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	private Client entity;

	@FXML
	private Label labelId;

	@FXML
	private Label labelName;

	@FXML
	private Label labelAdress;

	@FXML
	private Label labelTelephone;

	@FXML
	private Label labelCpf;

	@FXML
	private Button buttonClose;

	/**
	 * Método que retorna um objeto.
	 * 
	 * @return Client
	 */
	public Client getEntity() {
		return entity;
	}

	/**
	 * Método que seta o valor do objeto.
	 * 
	 * @param entity - Client
	 */
	public void setEntity(Client entity) {
		this.entity = entity;
	}

	/**
	 * Método que exibe os dados na tela.
	 */
	public void showData() {
		labelId.setText(String.valueOf(getEntity().getId()));
		labelName.setText(getEntity().getName());
		labelAdress.setText(getEntity().getAdress());
		labelTelephone.setText(getEntity().getTelephone());
		labelCpf.setText(getEntity().getCpf());
	}

	/**
	 * Método que fecha um evento.
	 * 
	 * @param event - ActionEvent
	 */
	public void onButtonCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
}
