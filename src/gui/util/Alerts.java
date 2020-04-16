package gui.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {
	/**
	 * Classe com métodos personalizados.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	/**
	 * Alert personalizado.
	 * 
	 * @param title   - String
	 * @param header  - String
	 * @param content - String
	 * @param type    - Alertype
	 */
	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		// alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

	/**
	 * Método que pega a comfirmação do clique do butão.
	 * 
	 * @param title   - String
	 * @param content - String
	 * @return Optional<ButtonType>
	 */
	public static Optional<ButtonType> showConfirmation(String title, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		return alert.showAndWait();
	}
}
