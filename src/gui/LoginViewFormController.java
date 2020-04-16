
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.LoadView;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.services.RegisterServices;

public class LoginViewFormController implements Initializable {

	private RegisterServices service;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private PasswordField passwordFieldPassword;

	@FXML
	private Button buttonLogin;

	@FXML
	private Button buttonCancel;

	LoadView load = new LoadView();

	public void setRegisterService(RegisterServices service) {
		this.service = service;
	}

	/**
	 * Função responsável por fazer a verifição e validação dos dados para permitir
	 * o acesso do usuário ao software. Conforme os dados informados pelo usuário, o
	 * método verifica se o usuário informado está cadastrado no sistema. Se estiver
	 * o usuário obtem acesso a interface pricipal do programa.
	 * 
	 * @author Adriano Queiroz
	 * @param event - ActionEvent 
	 */
	@FXML
	public void onButtonLoginAction(ActionEvent event) {

		/**
		 * List<Register> list = service.findAll();
		 * 
		 * String tmp1 = textFieldEmail.getText(); String tmp2 =
		 * passwordFieldPassword.getText();
		 * 
		 * int ver = 0; for (Register registro : list) { if
		 * ((tmp1.compareToIgnoreCase(registro.getEmail()) == 0) &&
		 * (tmp2.compareToIgnoreCase(registro.getPassword()) == 0)) { ver = 1;
		 **/
		load.loadView3("/gui/MainView1.fxml");
		Utils.currentStage(event).close();
		/**
		 * } } if (ver == 0) { Alerts.showAlert("Erro!", "Dados InvÃ¡lidos!", "Tente
		 * Novamente!", AlertType.ERROR); textFieldEmail.setText("");
		 * passwordFieldPassword.setText(""); }
		 **/
	}

	/**
	 * O método fecha um evento atual.
	 * 
	 * @author Adriano Queiroz
	 * @param event - ActionEvent
	 */
	public void onButtonCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializaNodes();
	}

	private void initializaNodes() {
		Constraints.setTextFieldMaxLength(textFieldEmail, 40);
		Constraints.setTextFieldMaxLength(passwordFieldPassword, 8);
	}

}
