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
import model.entities.Sales;
import model.services.ProductServices;
import model.services.SalesService;

public class SalesViewController implements Initializable, DataChangeListeners {

	private SalesService service = null;

	@FXML
	private Button buttonNew;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonUpdate;

	@FXML
	private Button buttonComeBack;

	@FXML
	private TableView<Sales> tableViewSales;

	@FXML
	private TableColumn<Sales, Integer> tableColumnId;

	@FXML
	private TableColumn<Sales, Integer> tableColumnIdClient;

	@FXML
	private TableColumn<Sales, Integer> tableColumnIdProduct;

	@FXML
	private TableColumn<Sales, Date> tableColumnDate;

	@FXML
	private TableColumn<Sales, Sales> tableColumnEDIT;

	LoadView load = new LoadView();

	private ObservableList<Sales> obsList;

	public void setSalesServices(SalesService service) {
		this.service = service;
	}

	public void onButtonNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Sales obj = new Sales();
		createDialogFormSales(obj, "/gui/SalesRegisterForm.fxml", parentStage, "Cadastro");
	}

	public void onButtonSearchAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Sales obj = new Sales();
		createDialogFormForForm(obj, "/gui/FormSearchSalesView.fxml", parentStage, "Pesquisa");
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
		tableColumnIdProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewSales.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serviço está nulo");
		}

		List<Sales> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewSales.setItems(obsList);
		initEditButtons();
	}

	@Override
	public void onDataChange() {
		updateTableView();
	}

	public void createDialogFormSales(Sales obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			SalesRegisterFormController controller = loader.getController();
			controller.setSales(obj);
			controller.updateFormData();
			controller.setSalesService(new SalesService());
			controller.setServiceP(new ProductServices());
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

	public void createDialogFormForForm(Sales obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			FormSearchSalesController controller = loader.getController();
			controller.setSalesServices(new SalesService());

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
		tableColumnEDIT.setCellFactory(param -> new TableCell<Sales, Sales>() {
			private final Button button = new Button("Atualizar");

			@Override
			protected void updateItem(Sales obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogFormSales(obj, "/gui/SalesRegisterForm.fxml",
						Utils.currentStage(event), "Atualizar"));
			}
		});
	}

}
