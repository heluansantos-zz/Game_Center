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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Provider;
import model.services.ProviderServices;

public class ProviderViewController implements Initializable, DataChangeListeners {

	private ProviderServices service = null;

	@FXML
	private MenuItem menuItemNew;

	@FXML
	private MenuItem menuItemComeBack;

	@FXML
	private Button buttonNew;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonComeBack;

	@FXML
	private TableView<Provider> tableViewProvider;

	@FXML
	private TableColumn<Provider, Integer> tableColumnId;

	@FXML
	private TableColumn<Provider, String> tableColumnName;

	@FXML
	private TableColumn<Provider, String> tableColumnAdress;

	@FXML
	private TableColumn<Provider, String> tableColumnTelephone;

	@FXML
	private TableColumn<Provider, String> tableColumnEmail;

	@FXML
	private TableColumn<Provider, String> tableColumnCnpj;

	@FXML
	private TableColumn<Provider, String> tableColumnCpf;

	@FXML
	private TableColumn<Provider, String> tableColumnCategory;

	@FXML
	private TableColumn<Provider, Provider> tableColumnEDIT;

	LoadView load = new LoadView();

	private ObservableList<Provider> obsList;

	public void setProviderServices(ProviderServices service) {
		this.service = service;
	}

	public void onButtonNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Provider obj = new Provider();
		createDialogFormProvider(obj, "/gui/ProviderRegisterForm.fxml", parentStage, "ENTRE COM OS DADOS");
	}

	public void onButttonSeacrhAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Provider obj = new Provider();
		createDialogFormForForm(obj, "/gui/FormSearchProviderView.fxml", parentStage, "Pesquisa");
	}

	public void onButtonComebackAction() {
		load.loadView("/gui/MainView1.fxml", x -> {
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializenodes();
	}

	private void initializenodes() {

		// tableViewProvider.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tableColumnTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableColumnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tableColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewProvider.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Serciço está nulo");
		}
		List<Provider> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewProvider.setItems(obsList);
		initEditButtons();
	}

	public void createDialogFormProvider(Provider obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ProviderRegisterFormController controller = loader.getController();
			controller.setProvider(obj);
			controller.updateFormData();
			controller.setProviderServices(new ProviderServices());
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

	public void createDialogFormForForm(Provider obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			FormSearchProviderController controller = loader.getController();
			controller.setProviderServices(new ProviderServices());

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
		tableColumnEDIT.setCellFactory(param -> new TableCell<Provider, Provider>() {
			private final Button button = new Button("Atualizar");

			@Override
			protected void updateItem(Provider obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogFormProvider(obj, "/gui/ProviderRegisterForm.fxml",
						Utils.currentStage(event), "ALTERAR DADOS"));
			}
		});
	}
}
