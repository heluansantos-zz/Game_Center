package gui.util;

import javafx.scene.control.TextField;

public class Constraints {
	/**
	 * Classe com métodos ultilitários.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	/**
	 * Método que faz com que o TextField do JavaFx aceite apenas valores inteiros.
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
	 * Método que define um limite de caracteres para o TextField.
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
	 * Método que faz com que o textField receba apenas valores do tipo Double.
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