package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListeners;
import gui.util.Alerts;
import gui.util.LoadView;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.ServiceSales;
import model.services.SalesServiceServices;

public class SalesServiceViewController implements Initializable, DataChangeListeners {

	private SalesServiceServices service = null;

	@FXML
	private Button buttonNew;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonUpdate;

	@FXML
	private Button buttonComeBack;

	@FXML
	private TableView<ServiceSales> tableViewSalesService;

	@FXML
	private TableColumn<ServiceSales, Integer> tableColumnId;

	@FXML
	private TableColumn<ServiceSales, Integer> tableColumnIdClient;

	@FXML
	private TableColumn<ServiceSales, Integer> tableColumnIdService;

	@FXML
	private TableColumn<ServiceSales, Date> tableColumnDate;

	@FXML
	private TableColumn<ServiceSales, ServiceSales> tableColumnEDIT;

	LoadView load = new LoadView();

	private ObservableList<ServiceSales> obsList;

	public void setSalesServiceServices(SalesServiceServices service) {
		this.service = service;
	}

	public void onButtonNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		ServiceSales obj = new ServiceSales();
		createDialogFormSales(obj, "/gui/SalesServiceRegisterForm.fxml", parentStage, "Cadastro");
	}

	public void onButtonSearchAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		ServiceSales obj = new ServiceSales();
		createDialogFormForForm(obj, "/gui/FormSearchSalesServiceView.fxml", parentStage, "Pesquisa");
	}

	public void onButtonUpdateAction() {

	}

	public void onButtonComeBackAction() {

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateSale"));
		tableColumnIdClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		tableColumnIdService.setCellValueFactory(new PropertyValueFactory<>("idService"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewSalesService.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serviço está nulo");
		}

		List<ServiceSales> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewSalesService.setItems(obsList);
		initEditButtons();
	}

	@Override
	public void onDataChange() {
		updateTableView();
	}

	public void createDialogFormSales(ServiceSales obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			SalesServiceRegisterFormController controller = loader.getController();
			controller.setSalesService(obj);
			controller.updateFormData();
			controller.setSalesService(new SalesServiceServices());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/add.png")));
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

	public void createDialogFormForForm(ServiceSales obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			FormSearchSalesServiceController controller = loader.getController();
			controller.setSalesServiceServices(new SalesServiceServices());

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/search1.png")));
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

	public void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<ServiceSales,ServiceSales >() {
			private final Button button = new Button("Atualizar");

			@Override
			protected void updateItem(ServiceSales obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogFormSales(obj, "/gui/SalesServiceRegisterForm.fxml",
						Utils.currentStage(event), "Atualizar"));
			}
		});
	}

}
