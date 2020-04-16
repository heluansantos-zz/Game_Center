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
import model.dao.SalesDao;
import model.entities.Sales;
import model.services.SalesService;

public class FormSearchSalesController implements Initializable {

	private SalesService service = null;
	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonUpdate;

	@FXML
	private Button buttonShowData;

	@FXML
	private Button buttonCancel;

	@FXML
	private Button buttonClose;

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

	@FXML
	private Label labelId;

	@FXML
	private Label labelIdClient;

	@FXML
	private Label labelIdProduct;

	@FXML
	private Label labelDate;

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldDelete;

	private ObservableList<Sales> obsList;

	LoadView load = new LoadView();

	public void setSalesServices(SalesService service) {
		this.service = service;
	}

	public void onButtonSearchAction() {
		Sales obj = search();
		if (obj == null) {
			Alerts.showAlert("Erro", "Venda não encontrada!", "Tente novamente!", AlertType.WARNING);
			textFieldId.setText("");
		} else {
			labelId.setText(String.valueOf(obj.getId()));
			labelDate.setText(obj.getDateSale());
			labelIdClient.setText(String.valueOf(obj.getIdClient()));
			labelIdProduct.setText(String.valueOf(obj.getIdProduct()));

			updateTableView(obj);
		}
	}

	private Sales search() {
		Sales obj = new Sales();
		obj = service.search(Utils.tryParseToInt(textFieldId.getText()));
		return obj;
	}

	private void updateTableView(Sales obj) {
		obsList = FXCollections.observableArrayList(obj);
		tableViewSales.setItems(obsList);
		initEditButtons();

	}

	public void onButtonUpdateAction() {
		Sales obj = search();
		updateTableView(obj);
	}

	public void onButtonCancelAction() {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Tem certeza que deseja excluir?");
		if (result.get() == ButtonType.OK) {
			service.delete(Utils.tryParseToInt(textFieldDelete.getText()));
			tableViewSales.setItems(null);
			labelId.setText("");
			labelDate.setText("");
			labelIdClient.setText("");
			labelIdProduct.setText("");
			textFieldId.setText("");
			textFieldDelete.setText("");
			Alerts.showAlert("Confirmação", "Venda cancelada com sucesso!", null, AlertType.CONFIRMATION);
		}
	}

	public void onButtonShowDataAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Sales obj = searchId();
		createDialogFormForDataSalesProduct(obj, "/gui/DataViewSalesProduct.fxml", parentStage, "Resumo da venda");
	}

	private Sales searchId() {
		SalesDao dao = DaoFactory.createSalesDao();
		List<Sales> list = dao.findByReports();
		Sales objResult = null;

		for (Sales obj : list) {
			if (obj.getId() == Utils.tryParseToInt(textFieldId.getText())) {
				objResult = obj;
			}
		}
		return objResult;
	}

	public void onButtonCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
		load.loadView("/gui/SalesView.fxml", (SalesViewController controller) -> {
			controller.setSalesServices(new SalesService());
			controller.updateTableView();
		});
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

	public void createDialogFormSales(Sales obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			SalesRegisterFormController controller = loader.getController();
			controller.setSales(obj);
			controller.updateFormData();
			controller.setSalesService(new SalesService());
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

	public void createDialogFormForDataSalesProduct(Sales obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			DataSalesProductController controller = loader.getController();
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
