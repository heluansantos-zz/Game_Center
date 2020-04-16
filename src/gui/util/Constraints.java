package gui.util;

import javafx.scene.control.TextField;

public class Constraints {
	/**
	 * Classe com m�todos ultilit�rios.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	/**
	 * M�todo que faz com que o TextField do JavaFx aceite apenas valores inteiros.
	 * 
	 * @param txt - TextField
	 */
	public static void setTextFieldInteger(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*")) {
				txt.setText(oldValue);
			}
		});
	}

	/**
	 * M�todo que define um limite de caracteres para o TextField.
	 * 
	 * @param txt - TextField
	 * @param max - int
	 */
	public static void setTextFieldMaxLength(TextField txt, int max) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && newValue.length() > max) {
				txt.setText(oldValue);
			}
		});
	}

	/**
	 * M�todo que faz com que o textField receba apenas valores do tipo Double.
	 * 
	 * @param txt - TextField
	 */
	public static void setTextFieldDouble(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
				txt.setText(oldValue);
			}
		});
	}
}