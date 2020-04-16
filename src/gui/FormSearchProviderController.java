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
import model.entities.Provider;
import model.services.ProviderServices;

public class FormSearchProviderController implements Initializable {

	private ProviderServices service = null;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonUpdate;

	@FXML
	private Button buttonClose;

	@FXML
	private Label labelId;

	@FXML
	private Label labelName;

	@FXML
	private Label labelAdress;

	@FXML
	private Label labelTelephone;

	@FXML
	private Label labelEmail;

	@FXML
	private Label labelCnpj;

	@FXML
	private Label labelCpf;

	@FXML
	private TextField textFieldCpf;

	@FXML
	private Label labelCategory;

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

	private ObservableList<Provider> obsList;

	LoadView load = new LoadView();

	public void setProviderServices(ProviderServices service) {
		this.service = service;
	}

	public void onButtonSearchAction() {
		Provider obj = new Provider();
		obj = service.search(textFieldCpf.getText());
		if (obj == null) {
			Alerts.showAlert("Erro", "Fornecedor não encontrado\n Ou não cadastrado!", "Tente novamente!",
					AlertType.ERROR);
			textFieldCpf.setText("");
		} else {
			labelId.setText(String.valueOf(obj.getId()));
			labelName.setText(obj.getName());
			labelAdress.setText(obj.getAdress());
			labelTelephone.setText(obj.getTelephone());
			labelEmail.setText(obj.getEmail());
			labelCnpj.setText(obj.getCnpj());
			labelCpf.setText(Utils.imprimeCPF(obj.getCpf()));
			labelCategory.setText(obj.getCategory());

			updateTableView(obj);
			initEditButtons();
		}
	}

	public void onButtonUpdateAction() {
		Provider obj = search();
		updateTableView(obj);
		
	}

	private Provider search() {
		Provider obj = new Provider();
		obj = service.search((textFieldCpf.getText()));
		return obj;
	}
	
	private void updateTableView(Provider obj) {
		obsList = FXCollections.observableArrayList(obj);
		tableViewProvider.setItems(obsList);
		initEditButtons();

	}
	public void onButtonCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
		load.loadView("/gui/ProviderView.fxml", (ProviderViewController controller) -> {
			controller.setProviderServices(new ProviderServices());
			controller.updateTableView();
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tableColumnTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableColumnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tableColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		Utils.cpfField(textFieldCpf);

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewProvider.prefHeightProperty().bind(stage.heightProperty());

	}

	public void createDialogFormProvider(Provider obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ProviderRegisterFormController controller = loader.getController();
			controller.setProvider(obj);
			controller.updateFormData();
			controller.setProviderServices(new ProviderServices());
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
