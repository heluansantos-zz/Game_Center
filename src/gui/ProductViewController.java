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
import model.entities.Product;
import model.services.ProductServices;
import model.services.ProviderServices;

public class ProductViewController implements Initializable, DataChangeListeners {

	private ProductServices service = null;

	@FXML
	private Button buttonNew;

	@FXML
	private Button buttonComeBack;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonDelete;

	@FXML
	TableView<Product> tableViewProduct;

	@FXML
	TableColumn<Product, Integer> tableColumnId;

	@FXML
	TableColumn<Product, Integer> tableColumnIdProvider;

	@FXML
	TableColumn<Product, String> tableColumnName;

	@FXML
	TableColumn<Product, String> tableColumnBrand;

	@FXML
	TableColumn<Product, String> tableColumnModel;

	@FXML
	TableColumn<Product, Integer> tableColumnQuantity;

	@FXML
	TableColumn<Product, Double> tableColumnValue;

	@FXML
	TableColumn<Product, String> tableColumnCategory;

	@FXML
	TableColumn<Product, Product> tableColumnEDIT;

	LoadView load = new LoadView();

	private ObservableList<Product> obsList;

	public void setProductServices(ProductServices service) {
		this.service = service;
	}

	public void onButtonNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Product obj = new Product();
		createDialogFormProduct(obj, "/gui/ProductRegisterForm.fxml", parentStage, "Entre com os dados");

	}

	public void onButtonComeBackAction(ActionEvent event) {
		load.loadView3("/gui/MainView1.fxml");
	}

	public void onButtonSearchAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Product obj = new Product();
		createDialogFormForForm(obj, "/gui/FormSearchProductView.fxml", parentStage, "Entre com os dados");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnIdProvider.setCellValueFactory(new PropertyValueFactory<>("idProvider"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
		tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tableColumnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		tableColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewProduct.prefHeightProperty().bind(stage.heightProperty());
	}

	public void alert(Stage parentStage) {
		List<Product> list = service.findAll();
		for(Product obj : list) {
			if(obj.getQuantity() < 5) {
				createDialogFormAlert(obj, "/gui/AlertProduct.fxml", parentStage, "Aviso");
			}
		}
	}
	public void updatetableView() {
		if (service == null) {
			throw new IllegalStateException("Serciço está nulo");
		}

		List<Product> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewProduct.setItems(obsList);
		initEditButtons(); 
	}

	public void createDialogFormProduct(Product obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ProductRegisterFormController controller = loader.getController();
			controller.setProduct(obj);
			controller.updateFormData();
			controller.setProductServices(new ProductServices());
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

	public void createDialogFormForForm(Product obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			FormSearchProductController controller = loader.getController();
			controller.setProductServices(new ProductServices());
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
	public void createDialogFormAlert(Product obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			AlertProductController controller = loader.getController();
			controller.setEntity(obj);
			controller.showData();

			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/alert.png")));
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
		updatetableView();

	}

	public void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Product, Product>() {
			private final Button button = new Button("Atualizar");

			@Override
			protected void updateItem(Product obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogFormProduct(obj, "/gui/ProductRegisterForm.fxml",
						Utils.currentStage(event), "Alterar dados"));
			}
		});
	}
}
