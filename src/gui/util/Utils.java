package gui.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Utils {
	/**
	 * Classe com métodos utilitários.
	 * 
	 * @version 1.0
	 */

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * Método que converte uma String em un Integer.
	 * 
	 * @param str - String
	 * @return Integer
	 */
	public static Integer tryParseToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Método que converte uma String em Double.
	 * 
	 * @param str - String
	 * @return Double
	 */
	public static Double tryParseToDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Método que imprime um cpf no formato: ###.###.###-##.
	 * 
	 * @param CPF - String
	 * @return String - String formatada.
	 */
	public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}

	private static void positionCaret(TextField textField) {
		Platform.runLater(() -> {
			if (textField.getText().length() != 0) {
				textField.positionCaret(textField.getText().length());
			}
		});
	}

	public static void cpfField(TextField textField) {
		Constraints.setTextFieldMaxLength(textField, 14);
		textField.lengthProperty().addListener((observableValue, number, number2) -> {
			String value = textField.getText();
			value = value.replaceAll("[^0-9]", "");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1-$2");
			try {
				textField.setText(value);
				positionCaret(textField);
			} catch (Exception ex) {
			}
		});
	}

	public static void cnpjField(TextField textField) {
		Constraints.setTextFieldMaxLength(textField, 18);
		textField.lengthProperty().addListener((observableValue, number, number2) -> {
			String value = textField.getText();
			value = value.replaceAll("[^0-9]", "");
			value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1/$2");
			value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
			textField.setText(value);
			positionCaret(textField);
		});
	}

	/**
	 * 
	 * @param textField
	 */
	public static void foneField(TextField textField) {
		Constraints.setTextFieldMaxLength(textField, 14);
		textField.lengthProperty().addListener((observableValue, number, number2) -> {
			try {
				String value = textField.getText();
				value = value.replaceAll("[^0-9]", "");
				int tam = value.length();
				value = value.replaceFirst("(\\d{2})(\\d)", "($1)$2");
				value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
				if (tam > 10) {
					value = value.replaceAll("-", "");
					value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
				}
				textField.setText(value);
				positionCaret(textField);
			} catch (Exception ex) {
			}
		});
	}

	/**
	 * Método que cria uma mascara de data para um TextField componente do JavaFx.
	 * 
	 * @param textField - TextField
	 */
	public static void dateField(final TextField textField) {
		Constraints.setTextFieldMaxLength(textField, 10);

		textField.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() < 11) {
					String value = textField.getText();
					value = value.replaceAll("[^0-9]", "");
					value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
					value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
					textField.setText(value);
					positionCaret(textField);
				}
			}
		});
	}

	/**
	 * Método que converte uma data do tipo String em um tipo java.sql.Date.
	 * 
	 * @author Adriano Queiroz
	 * @param dateString - String
	 * @return Date
	 */
	public static Date dateConverter(String dateString) {
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date date = new java.sql.Date(format.parse(dateString).getTime());
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que faz a validação de datas.
	 * 
	 * @author Adriano Queiroz
	 * @param strDate - boolean
	 * @return boolean - Valor veradeiro ou falso.
	 */
	public static boolean validDate(String strDate) {
		String dateFormat = "dd/MM/uuuu";

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
				.withResolverStyle(ResolverStyle.STRICT);
		try {
			@SuppressWarnings("unused")
			LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	/**
	 * Método que recebe uma data e retorna apenas o mês.
	 * 
	 * @author Adriano Queiroz
	 * @param dateString - int
	 * @return int - Valor do mês.
	 */
	public static int whichMonth(String dateString) {
		Date data = new Date(0);
		data = Utils.dateConverter(dateString);
		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(data);
		int mes = dataCal.get(Calendar.MONTH);
		return mes + 1;
	}

	/**
	 * Método que dada uma certa data do tipo String retorna apenas um valor inteiro
	 * relacionado ao ano.
	 * 
	 * @author Adriano Queiroz
	 * @param dateString - String
	 * @return int
	 */
	public static int whichYEAR(String dateString) {
		Date data = new Date(0);
		data = Utils.dateConverter(dateString);
		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(data);
		int year = dataCal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * Método que pega a atual data do sistema.
	 * 
	 * @author Adriano Queiroz
	 * @return String - Valor da data formatada.
	 */
	public static String dateCurrent() {
		java.util.Date date = new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		String dateString = dateFormat.format(date);
		return dateString;
	}

	/**
	 * Método que dado um valor do tipo Integer retorna o respectivo mês
	 * relacionado.
	 * 
	 * @author Adriano Queiroz
	 * @param month String
	 * @return String - Mês
	 */
	public static String month(Integer month) {
		switch (month) {
		case 1:
			return "JANEIRO";
		case 2:
			return "FEVEREIRO";
		case 3:
			return "MARÇO";
		case 4:
			return "ABRIL";
		case 5:
			return "MAIO";
		case 6:
			return "JUNHO";
		case 7:
			return "JULHO";
		case 8:
			return "AGOSTO";
		case 9:
			return "SETEMBRO";
		case 10:
			return "OUTUBRO";
		case 11:
			return "NOVEMBRO";
		case 12:
			return "DEZEMBRO";
		default:
			return null;
		}
	}

	/**
	 * Método que valida um ano. Valida apenas se tem quatro dígitos. Portanto a
	 * validação é simples.
	 * 
	 * @author Adriano Queiroz
	 * @param year Integer
	 * @return Boolean - Valor verdadeiro falso.
	 */
	public static Boolean validYEAR(Integer year) {
		if (year > 1000 && year < 9999) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que validada um mês.
	 * 
	 * @author Adriano Queiroz
	 * @param month Integer
	 * @return Boolean - Valor verdadeiro ou falso.
	 */
	public static Boolean validMonth(Integer month) {
		if (month == 1 || month == 2 || month == 3 || month == 4 || month == 5 || month == 6 || month == 7 || month == 8
				|| month == 9 || month == 10 || month == 11 || month == 12) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que faz uma série de cálculos para validar um CPF.
	 * 
	 * @param CPF String
	 * @return Boolean - Valor falso ou verdadeiro.
	 */
	public static Boolean validCPF(String CPF) {
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char digit10, digit11;
		Integer sum, i, r, num, weight;
		try {
			sum = 0;
			weight = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sum = sum + (num * weight);
				weight = weight - 1;
			}

			r = 11 - (sum % 11);
			if ((r == 10) || (r == 11))
				digit10 = '0';
			else
				digit10 = (char) (r + 48);
			sum = 0;
			weight = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sum = sum + (num * weight);
				weight = weight - 1;
			}

			r = 11 - (sum % 11);
			if ((r == 10) || (r == 11))
				digit11 = '0';
			else
				digit11 = (char) (r + 48);

			if ((digit10 == CPF.charAt(9)) && (digit11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException error) {
			return (false);
		}
	}

	/**
	 * Método que retira máscara de uma string do tipo: "###.###.###-##".
	 * 
	 * @author Adriano Queiroz
	 * @param CPF String
	 * @return String - String formatada.
	 */
	public static String withdrawMask(String CPF) {
		String strhelp = "";
		for (int x = 0; x < CPF.length(); x++) {
			if (("0123456789").indexOf(CPF.charAt(x) + "") > -1) {
				strhelp += (CPF.charAt(x) + "");
			}
		}
		return strhelp;
	}

	/**
	 * Método que adiciona uma máscara do tipo: "###.###.###-##" em um CPF.
	 * 
	 * @author Adriano Queiroz
	 * @param CPF String
	 * @return String - Retorna a string formatada.
	 */
	public static String addMaskCPF(String CPF) {
		String pattern = "###.###.###-##";
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(CPF);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Método que faz validação de email. Essa validação é baseada em um formato
	 * padrão de um email do tipo: "nome.@gmail.com'.
	 * 
	 * @author Adriano Queiroz
	 * @param email String - O email a ser validado.
	 * @return boolean - Retorna falso ou verdadeiro.
	 */
	public static boolean validEmail(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
