package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
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
import javafx.scene.control.ButtonType;
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
import model.entities.Client;
import model.entities.Service;
import model.services.ClientServices;
import model.services.ServiceServices;

public class FormSearchServiceController implements Initializable {

	private ServiceServices service = null;

	private ClientServices serviceC = null;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonClose;

	@FXML
	private Button buttonDelete;

	@FXML
	private Button buttonUpdate;

	@FXML
	private Button buttonDataClient;

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

	@FXML
	private TextField textFieldDelete;

	LoadView load = new LoadView();

	private ObservableList<Service> obsList;

	public void setClientServices(ClientServices serviceC) {
		this.serviceC = serviceC;
	}

	public void setServiceServices(ServiceServices service) {
		this.service = service;
	}

	public void onButtonSearchAction() {
		Service obj = new Service();
		obj = service.search(textFieldSearch.getText().toUpperCase());
		if (obj == null) {
			Alerts.showAlert("Erro", "Serviço não encontrado", "Tente novamente", AlertType.WARNING);
			textFieldSearch.setText("");
		} else {
			labelId.setText(String.valueOf(obj.getId()));
			labelIdClient.setText(String.valueOf(obj.getIdClient()));
			labelName.setText(obj.getName());
			labelDescription.setText(obj.getProblemDescription());
			labelDate.setText(obj.getDate());
			labelStatus.setText(obj.getStatus());
			labelValue.setText(String.valueOf(obj.getValue()));
			labelWarranty.setText(obj.getWarranty());

			obsList = FXCollections.observableArrayList(obj);
			tableViewService.setItems(obsList);
			initEditButtons();

		}
	}

	private Client dataResult() {
		Service obj = new Service();
		obj = service.search(textFieldSearch.getText().toUpperCase());
		Client objC = serviceC.search(obj.getIdClient());
		
		return objC;
	}
	public void onButtonDataClient(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Client obj = dataResult();	
		createDialogDataServive(obj, "/gui/ClientDataView.fxml", parentStage, "Dados do Cliente");
	}

	public void onButtonUpdateAction() {
		onButtonSearchAction();
	}

	public void onButtonDeleteAction() {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação",
				"Tem certeza que desja excluir esse serviço?");
		if (result.get() == ButtonType.OK) {
			service.delete(Utils.tryParseToInt(textFieldDelete.getText()));
		}
		tableViewService.setItems(null);
		labelId.setText("");
		labelIdClient.setText("");
		labelName.setText("");
		labelDescription.setText("");
		labelDate.setText("");
		labelStatus.setText("");
		labelValue.setText("");
		labelWarranty.setText("");
		textFieldDelete.setText("");
	}

	public void onButtonCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
		load.loadView("/gui/ServiceView.fxml", (ServiceViewController controller) -> {
			controller.setServiceServices(new ServiceServices());
			controller.updateTableView();
		});
	}

	@Override
	public void initialize(URL rb, ResourceBundle url) {
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

	public void createDialogFormService(Service obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ServiceRegisterFormCotroller controller = loader.getController();
			controller.setService(obj);
			controller.updateFormData();
			controller.setServiceServices(new ServiceServices());
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

	public void createDialogDataServive(Client obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ClientDataViewController controller = loader.getController();
			controller.setEntity(obj);
			controller.showData();
		

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
