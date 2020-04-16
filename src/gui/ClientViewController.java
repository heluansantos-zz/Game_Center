package gui;

import java.io.IOException;
import java.net.URL;
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
import model.entities.Client;
import model.services.ClientServices;

public class ClientViewController implements Initializable, DataChangeListeners {

	private ClientServices service = null;

	@FXML
	private Button buttonNew;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonComeBack;

	@FXML
	private Button buttonUpdate;

	@FXML
	TableView<Client> tableViewClient;

	@FXML
	TableColumn<Client, Integer> tableColumnId;

	@FXML
	TableColumn<Client, String> tableColumnName;

	@FXML
	TableColumn<Client, String> tableColumnAddress;

	@FXML
	TableColumn<Client, String> tableColumnTelephone;

	@FXML
	TableColumn<Client, String> tableColumnCpf;

	@FXML
	TableColumn<Client, Client> tableColumnEDIT;

	LoadView load = new LoadView();

	private ObservableList<Client> obsList;

	private static final String imageRegister = "/image/register.png";

	private static final String imageUpdate = "/image/update.png";

	public void setClientServices(ClientServices service) {
		this.service = service;
	}

	public void onButtonNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Client obj = new Client();
		createDialogFormClient(obj, "/gui/ClientRegisterForm.fxml", parentStage, "Cadastro", imageRegister);
	}

	public void onButtonSearchAction(ActionEvent event) {
		Client obj = new Client();
		Stage parentStage = Utils.currentStage(event);
		createDialogFormForForm(obj, "/gui/FormSearchClientView.fxml", parentStage, "Pesquisa", imageUpdate);
	}

	public void onButtoncomeBackAction(ActionEvent event) {
		load.loadView3("/gui/MainView1.fxml");
	}

	public void onButtonUpdateAction(ActionEvent event) {
		updateTableView();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {

		tableViewClient.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnAddress.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tableColumnTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewClient.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serviço está nulo");
		}

		List<Client> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewClient.setItems(obsList);
		initEditButtons();
	}

	public void createDialogFormClient(Client obj, String absoluteName, Stage parentStage, String title, String image) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ClientRegisterFormController controller = loader.getController();
			controller.setClient(obj);
			controller.updateFormData();
			controller.setClientServices(new ClientServices());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream(image)));
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

	public void createDialogFormForForm(Client obj, String absoluteName, Stage parentStage, String title,
			String image) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			FormSearchClientController controller = loader.getController();
			controller.setClientServices(new ClientServices());

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream(image)));
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
		tableColumnEDIT.setCellFactory(param -> new TableCell<Client, Client>() {
			private final Button button = new Button("Atualizar");

			@Override
			protected void updateItem(Client obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogFormClient(obj, "/gui/ClientRegisterForm.fxml",
						Utils.currentStage(event), "Atualizar",imageUpdate));
			}
		});
	}
}
