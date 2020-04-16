package gui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import gui.util.Alerts;
import gui.util.Utils;
import gui.util.genereteReportsPDF;
import gui.util.genereteReportsTxt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.SalesDao;
import model.dao.SalesServiceDao;
import model.entities.Sales;
import model.entities.ServiceSales;

public class SalesReportsController {

	@FXML
	private TextField textFielMonthProduct;

	@FXML
	private TextField textFielYearProduct;

	@FXML
	private TextField textFielMonthService;

	@FXML
	private TextField textFielYearService;

	@FXML
	private Button buttonProductPDF;

	@FXML
	private Button buttonProductText;

	@FXML
	private Button buttonServicePDF;

	@FXML
	private Button buttonServiceText;

	private static Optional<ButtonType> result;

	private static String path = null;

	private static String m = null;

	public void onbuttonProductTextAction(ActionEvent event) {

		if (Utils.validMonth(Utils.tryParseToInt(textFielMonthProduct.getText())) == true
				&& Utils.validYEAR(Utils.tryParseToInt(textFielYearProduct.getText())) == true) {
			SalesDao dao = DaoFactory.createSalesDao();
			List<Sales> list = dao.findByReports();

			m = Utils.month(Utils.tryParseToInt(textFielMonthProduct.getText()));
			path = "C:\\Users\\adria\\eclipse-workspace\\Projeto-POO-2019.2\\Relatório - Vendas - Produtos -" + m
					+ ".txt";

			if (list == null) {
				Alerts.showAlert("Erro", "Naõ foi possível gerar relatório.", null, AlertType.ERROR);
			} else {
				genereteReportsTxt.genereteReportsSalesProductsText(path, list,
						Utils.tryParseToInt(textFielMonthProduct.getText()),
						Utils.tryParseToInt(textFielYearProduct.getText()));
				result = Alerts.showConfirmation("Confirmação",
						"Relatório gerado com sucesso!\nO arquivo foi salvo na pasta:\n" + path
								+ "\n\nDeseja abrir o arquivo?");
				if (result.get() == ButtonType.OK) {
					try {
						java.awt.Desktop.getDesktop().open(new File(path));
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						Utils.currentStage(event).close();
					}
				}
			}
		} else {
			Alerts.showAlert("Erro", "Dados inválidos", "Insira dados válidos e tente novamente", AlertType.ERROR);
		}
	}

	public void onbuttonServiceTextAction(ActionEvent event) {
		if (Utils.validMonth(Utils.tryParseToInt(textFielMonthService.getText())) == true
				&& Utils.validYEAR(Utils.tryParseToInt(textFielYearService.getText())) == true) {
			SalesServiceDao dao = DaoFactory.createSalesServiceDao();
			List<ServiceSales> list = dao.findReportsSalesService();

			m = Utils.month(Utils.tryParseToInt(textFielMonthService.getText()));
			path = "C:\\Users\\adria\\eclipse-workspace\\Projeto-POO-2019.2\\Relatório - Vendas - Serviços -" + m
					+ ".txt";

			if (list == null) {
				Alerts.showAlert("Erro", "Naõ foi possível gerar relatório.", null, AlertType.ERROR);
			} else {
				genereteReportsTxt.genereteReportsSalesServiceText(path, list,
						Utils.tryParseToInt(textFielMonthService.getText()),
						Utils.tryParseToInt(textFielYearService.getText()));
				result = Alerts.showConfirmation("Confirmação",
						"Relatório gerado com sucesso!\nO arquivo foi salvo na pasta:\n" + path
								+ "\n\nDeseja abrir o arquivo?");
				if (result.get() == ButtonType.OK) {
					try {
						java.awt.Desktop.getDesktop().open(new File(path));
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						Utils.currentStage(event).close();
					}
				}
			}
		} else {
			Alerts.showAlert("Erro", "Dados inválidos", "Insira dados válidos e tente novamente", AlertType.ERROR);
		}
	}

	public void onButtonProductPDFAction(ActionEvent event) {
		if (Utils.validMonth(Utils.tryParseToInt(textFielMonthProduct.getText())) == true
				&& Utils.validYEAR(Utils.tryParseToInt(textFielYearProduct.getText())) == true) {
			SalesDao dao = DaoFactory.createSalesDao();
			List<Sales> list = dao.findByReports();

			m = Utils.month(Utils.tryParseToInt(textFielMonthProduct.getText()));
			path = "C:\\Users\\adria\\eclipse-workspace\\Projeto-POO-2019.2\\Relatório - Vendas - Produtos -" + m
					+ ".pdf";

			if (textFielMonthProduct == null || textFielYearProduct == null) {
				Alerts.showAlert("Warning", "Informe os dados necessários", "Tente novamente!", AlertType.WARNING);
			} else {
				genereteReportsPDF.genereteReportsSalesProduct(list,
						Utils.tryParseToInt(textFielMonthProduct.getText()),
						Utils.tryParseToInt(textFielYearProduct.getText()), path);
				result = Alerts.showConfirmation("Confirmação",
						"Relatório gerado com sucesso!\nO arquivo foi salvo na pasta:\n" + path
								+ "\n\nDeseja abrir o arquivo?");
				if (result.get() == ButtonType.OK) {
					try {
						java.awt.Desktop.getDesktop().open(new File(path));
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						Utils.currentStage(event).close();
					}
				}
			}
		} else {
			Alerts.showAlert("Erro", "Dados inválidos", "Insira dados válidos e tente novamente", AlertType.ERROR);
		}
	}

	public void onButtonServicetPDFAction(ActionEvent event) {
		if (Utils.validMonth(Utils.tryParseToInt(textFielMonthService.getText())) == true
				&& Utils.validYEAR(Utils.tryParseToInt(textFielYearService.getText())) == true) {
			SalesServiceDao dao = DaoFactory.createSalesServiceDao();
			List<ServiceSales> list = dao.findReportsSalesService();

			m = Utils.month(Utils.tryParseToInt(textFielMonthService.getText()));
			path = "C:\\Users\\adria\\eclipse-workspace\\Projeto-POO-2019.2\\Relatório - Vendas - Serviços -" + m
					+ ".pdf";

			if (textFielMonthService == null || textFielYearService == null) {
				Alerts.showAlert("Warning", "Informe os dados necessários", "Tente novamente!", AlertType.WARNING);
			} else {
				genereteReportsPDF.genereteReportsSalesService(list,
						Utils.tryParseToInt(textFielMonthService.getText()),
						Utils.tryParseToInt(textFielYearService.getText()), path);
				result = Alerts.showConfirmation("Confirmação",
						"Relatório gerado com sucesso!\nO arquivo foi salvo na pasta:\n" + path
								+ "\n\nDeseja abrir o arquivo?");
				if (result.get() == ButtonType.OK) {
					try {
						java.awt.Desktop.getDesktop().open(new File(path));
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						Utils.currentStage(event).close();
					}
				}
			}
		} else {
			Alerts.showAlert("Erro", "Dados inválidos", "Insira dados válidos e tente novamente", AlertType.ERROR);
		}
	}

	public void createDialogFormReports(String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon1.jpg")));
			dialogStage.setTitle(title);
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
