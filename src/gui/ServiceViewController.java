package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Service;
import model.services.ClientServices;
import model.services.ServiceServices;

public class ServiceViewController implements Initializable, DataChangeListeners {

	private ServiceServices service = null;

	@FXML
	private Button buttonNew;

	@FXML
	private Button buttonComeBack;

	@FXML
	private Button buttonSearch;

	@FXML
	private TableView<Service> tableViewService;

	@FXML
	private TableColumn<Service, Integer> tableColumnId;

	@FXML
	private TableColumn<Service, Integer> tableColumnIdClient;

	@FXML
	private TableColumn<Service, String> tableColumnName;

	@FXML
	private TableColumn<Service, String> tableColumnDescription;

	@FXML
	private TableColumn<Service, String> tableColumnStatus;

	@FXML
	private TableColumn<Service, Double> tableColumnValue;

	@FXML
	private TableColumn<Service, Integer> tableColumnWarranty;

	@FXML
	private TableColumn<Service, Date> tableColumnDate;

	@FXML
	private TableColumn<Service, Service> tableColumnEDIT;

	@FXML
	private Label labelId;

	@FXML
	private Label labelIdClient;

	@FXML
	private Label labelName;

	@FXML
	private Label labelDescription;

	@FXML
	private Label labelStatus;

	@FXML
	private Label labelValue;

	@FXML
	private Label labelWarranty;

	@FXML
	private Label labelDate;

	@FXML
	private TextField textFieldSearch;

	LoadView load = new LoadView();

	private ObservableList<Service> obsList;

	public void setServiceServices(ServiceServices service) {
		this.service = service;
	}

	public void onButtonNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Service obj = new Service();
		createDialogFormService(obj, "/gui/ServiceRegisterForm.fxml", parentStage, "Entre com os dados");
	}

	public void onButtonComeBackAction(ActionEvent event) {
		load.loadView3("/gui/MainView1.fxml");
	}

	public void onButtonSearchAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Service obj = new Service();
		createDialogFormForForm(obj, "/gui/FormSearchServiceView.fxml", parentStage, "Pesquisa/Excluir");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {

		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnIdClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("problemDescription"));
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tableColumnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		tableColumnWarranty.setCellValueFactory(new PropertyValueFactory<>("warranty"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewService.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serciço está nulo");
		}

		List<Service> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewService.setItems(obsList);
		initEditButtons();
	}

	public void createDialogFormService(Service obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ServiceRegisterFormCotroller controller = loader.getController();
			controller.setService(obj);
			controller.updateFormData();
			controller.setServiceServices(new ServiceServices());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

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

	public void createDialogFormForForm(Service obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			FormSearchServiceController controller = loader.getController();
			controller.setServiceServices(new ServiceServices());
			controller.setClientServices(new ClientServices());

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/search.png")));
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

	@Override
	public void onDataChange() {
		updateTableView();
	}

	public void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Service, Service>() {
			private final Button button = new Button("Atualizar");

			@Override
			protected void updateItem(Service obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogFormService(obj, "/gui/ServiceRegisterForm.fxml",
						Utils.currentStage(event), "Alterar dados"));
			}
		});
	}

}
