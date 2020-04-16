package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
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
import model.dao.DaoFactory;
import model.dao.SalesServiceDao;
import model.entities.ServiceSales;
import model.services.SalesServiceServices;

public class FormSearchSalesServiceController implements Initializable {

	private SalesServiceServices service = null;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonUpdate;

	@FXML
	private Button buttonCancel;

	@FXML
	private Button buttonClose;

	@FXML
	private Button buttonShowData;

	@FXML
	private TableView<ServiceSales> tableViewSales;

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

	@FXML
	private Label labelId;

	@FXML
	private Label labelIdClient;

	@FXML
	private Label labelIdService;

	@FXML
	private Label labelDate;

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldDelete;

	private ObservableList<ServiceSales> obsList;

	LoadView load = new LoadView();

	public void setSalesServiceServices(SalesServiceServices service) {
		this.service = service;
	}

	public void onButtonSearchAction() {
		ServiceSales obj = search();
		if (obj == null) {
			Alerts.showAlert("Erro", "Venda não encontrada!", "Tente novamente!", AlertType.WARNING);
			textFieldId.setText("");
		} else {
			labelId.setText(String.valueOf(obj.getId()));
			labelDate.setText(obj.getDateSale());
			labelIdClient.setText(String.valueOf(obj.getIdClient()));
			labelIdService.setText(String.valueOf(obj.getIdService()));

			updateTableView(obj);
		}
	}

	private ServiceSales search() {
		ServiceSales obj = new ServiceSales();
		obj = service.search(Utils.tryParseToInt(textFieldId.getText()));
		return obj;
	}

	private void updateTableView(ServiceSales obj) {
		obsList = FXCollections.observableArrayList(obj);
		tableViewSales.setItems(obsList);
		initEditButtons();

	}

	public void onButtonUpdateAction() {
		ServiceSales obj = search();
		updateTableView(obj);
	}

	public void onButtonShowDataAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		ServiceSales obj = searchId();
		createDialogFormForDataSalesService(obj, "/gui/DataViewSalesService.fxml", parentStage, "Resumo da venda");
	}

	private ServiceSales searchId() {
		SalesServiceDao dao = DaoFactory.createSalesServiceDao();
		List<ServiceSales> list = dao.findReportsSalesService();
		ServiceSales objResult = null;

		for (ServiceSales obj : list) {
			if (obj.getId() == Utils.tryParseToInt(textFieldId.getText())) {
				objResult = obj;
			}
		}
		return objResult;
	}

	public void onButtonCancelAction() {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Tem certeza que deseja excluir?");
		if (result.get() == ButtonType.OK) {
			service.delete(Utils.tryParseToInt(textFieldDelete.getText()));
			tableViewSales.setItems(null);
			labelId.setText("");
			labelDate.setText("");
			labelIdClient.setText("");
			labelIdService.setText("");
			textFieldId.setText("");
			textFieldDelete.setText("");
		}
	}

	public void onButtonCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
		load.loadView("/gui/SalesServiceView.fxml", (SalesServiceViewController controller) -> {
			controller.setSalesServiceServices(new SalesServiceServices());
			controller.updateTableView();
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateSale"));
		tableColumnIdClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		tableColumnIdService.setCellValueFactory(new PropertyValueFactory<>("idService"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewSales.prefHeightProperty().bind(stage.heightProperty());
	}

	public void createDialogFormSales(ServiceSales obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			SalesServiceRegisterFormController controller = loader.getController();
			controller.setSalesService(obj);
			controller.updateFormData();
			controller.setSalesService(new SalesServiceServices());
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

	public void createDialogFormForDataSalesService(ServiceSales obj, String absoluteName, Stage parentStage,
			String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			DataSalesServiceController controller = loader.getController();
			controller.setEntity(obj);
			controller.showData();

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
		tableColumnEDIT.setCellFactory(param -> new TableCell<ServiceSales, ServiceSales>() {
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
