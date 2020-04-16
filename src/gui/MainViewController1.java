package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.LoadView;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.services.ClientServices;
import model.services.ProductServices;
import model.services.ProviderServices;
import model.services.SalesService;
import model.services.SalesServiceServices;
import model.services.ServiceServices;

public class MainViewController1 implements Initializable {

	@FXML
	private Button buttonClient;

	@FXML
	private Button buttonService;

	@FXML
	private Button buttonProduct;

	@FXML
	private Button buttonProvider;

	@FXML
	private Button buttonClientSales;

	@FXML
	private Button buttonClientSalesService;

	@FXML
	private Button buttonReportsSalesProduct;

	@FXML
	private Button buttonLeave;

	LoadView load = new LoadView();

	public void onBtClientAction() {
		load.loadView("/gui/ClientView.fxml", (ClientViewController controller) -> {
			controller.setClientServices(new ClientServices());
			controller.updateTableView();
		});
	}

	public void onBtServiceAction() {
		load.loadView("/gui/ServiceView.fxml", (ServiceViewController controller) -> {
			controller.setServiceServices(new ServiceServices());
			controller.updateTableView();
		});
	}

	public void onBtProductAction(ActionEvent event) {
		load.loadView("/gui/ProductView.fxml", (ProductViewController controller) -> {
			controller.setProductServices(new ProductServices());
			controller.updatetableView();
			controller.alert(Utils.currentStage(event));
		});
	}

	public void onBtProviderAction() {
		load.loadView("/gui/ProviderView.fxml", (ProviderViewController controller) -> {
			controller.setProviderServices(new ProviderServices());
			controller.updateTableView();
		});
	}

	public void onBtSalesAction() {
		load.loadView("/gui/SalesView.fxml", (SalesViewController controller) -> {
			controller.setSalesServices(new SalesService());
			controller.updateTableView();
		});
	}

	public void onButtonSalesServiceAction() {
		load.loadView("/gui/SalesServiceView.fxml", (SalesServiceViewController controller) -> {
			controller.setSalesServiceServices(new SalesServiceServices());
			controller.updateTableView();
		});
	}

	public void onBtReportsSalesProductAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogFormReports("/gui/ReportsSales.fxml", parentStage, "Relatórios");

	}

	public void onBtLeaveAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void createDialogFormReports(String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/report.png")));
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
