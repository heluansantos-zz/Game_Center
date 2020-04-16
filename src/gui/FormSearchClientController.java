package gui;

import java.io.IOException;
import java.net.URL;
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
import model.services.ClientServices;

public class FormSearchClientController implements Initializable {

	/**
	 * Classe controller da interface FormsearchClientView.
	 */

	private ClientServices service = null;

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

	@FXML
	private Label labelId;

	@FXML
	private Label labelName;

	@FXML
	private Label labelAddress;

	@FXML
	private Label labelTelephone;

	@FXML
	private Label labelCpf;

	@FXML
	private TextField textFieldSearch;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonClose;

	private ObservableList<Client> obsList;

	LoadView load = new LoadView();

	/**
	 * Método que seta o valor do objeto.
	 * 
	 * @param service - ClientServices
	 */
	public void setClientServices(ClientServices service) {
		this.service = service;
	}

	/**
	 * Comforme o clique do butão, o método exibe uma janela com funcionalidades que
	 * permite pesquisar e atualizar informações de um determinado objeto.
	 */
	public void onButtonSearchClient() {
		Client obj = new Client();
		obj = service.search(textFieldSearch.getText());
		if (obj == null) {
			Alerts.showAlert("Erro", "Cliente não encontrado!", "Tente novamente!", AlertType.WARNING);
			textFieldSearch.setText("");
		} else {

			labelId.setText(String.valueOf(obj.getId()));
			labelName.setText(obj.getName());
			labelAddress.setText(obj.getAdress());
			labelTelephone.setText(obj.getTelephone());
			labelCpf.setText(obj.getCpf());

			obsList = FXCollections.observableArrayList(obj);
			tableViewClient.setItems(obsList);
			initEditButtons();
		}
	}

	/**
	 * Método que fecha um evento e atualiza informações de determinados objetos no
	 * evento subsequente.
	 * 
	 * @param event - ActionEvent
	 */
	public void onButtonCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
		load.loadView("/gui/ClientView.fxml", (ClientViewController controller) -> {
			controller.setClientServices(new ClientServices());
			controller.updateTableView();
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnAddress.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tableColumnTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		Utils.cpfField(textFieldSearch);

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewClient.prefHeightProperty().bind(stage.heightProperty());
	}

	/**
	 * Método que cria uma janela gráfica secundária.
	 * 
	 * @param obj - Client
	 * @param absoluteName - String
	 * @param parentStage - Stage
	 * @param title - String
	 */
	public void createDialogFormClient(Client obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ClientRegisterFormController controller = loader.getController();
			controller.setClient(obj);
			controller.updateFormData();
			controller.setClientServices(new ClientServices());
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
						Utils.currentStage(event), "Alterar dados"));
			}
		});
	}
}
