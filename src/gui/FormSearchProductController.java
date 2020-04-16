package gui;

import java.io.IOException;
import java.net.URL;
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
import model.entities.Product;
import model.entities.Provider;
import model.services.ProductServices;
import model.services.ProviderServices;

public class FormSearchProductController implements Initializable {

	private ProductServices service = null;

	private ProviderServices serviceP = null;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button buttonClose;

	@FXML
	private Button buttonDataProvider;

	@FXML
	private Button buttonDelete;

	@FXML
	private TextField textFieldSearch;

	@FXML
	private TextField textFieldDelete;

	@FXML
	private Label labelId;

	@FXML
	private Label labelIdProvider;

	@FXML
	private Label labelName;

	@FXML
	private Label labelBrand;

	@FXML
	private Label labelModel;

	@FXML
	private Label labelQuantity;

	@FXML
	private Label labelValue;

	@FXML
	private Label labelCategory;

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

	@FXML
	TableColumn<Product, Product> tableColumnREMOVE;

	private ObservableList<Product> obsList;

	LoadView load = new LoadView();

	public void setProductServices(ProductServices service) {
		this.service = service;
	}

	public void setProviderServices(ProviderServices serviceP) {
		this.serviceP = serviceP;
	}

	public void onButtonSearchAction() {
		Product obj = new Product();
		obj = service.search(textFieldSearch.getText());
		if (obj == null) {
			Alerts.showAlert("Erro", "Produto não encontrado!", "Tente novamente!", AlertType.WARNING);
			textFieldSearch.setText("");
		} else {
			labelId.setText(String.valueOf(obj.getId()));
			labelIdProvider.setText(String.valueOf(obj.getIdProvider()));
			labelName.setText(obj.getName());
			labelBrand.setText(obj.getBrand());
			labelModel.setText(obj.getModel());
			labelQuantity.setText(String.valueOf(obj.getQuantity()));
			labelValue.setText(String.valueOf(obj.getValue()));
			labelCategory.setText(obj.getCategory());

			obsList = FXCollections.observableArrayList(obj);
			tableViewProduct.setItems(obsList);
			initEditButtons();
		}
	}

	public void onButtonDeleteAction() {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Tem certeza que deseja excluir?");
		if (result.get() == ButtonType.OK)
			service.delete(Utils.tryParseToInt(textFieldDelete.getText()));
		tableViewProduct.setItems(null);
		labelId.setText("");
		labelIdProvider.setText("");
		labelName.setText("");
		labelBrand.setText("");
		labelModel.setText("");
		labelQuantity.setText("");
		labelValue.setText("");
		labelCategory.setText("");
		textFieldDelete.setText("");
		textFieldSearch.setText("");
	}

	public Provider dataResult() {
		Product obj = new Product();
		obj = service.search(textFieldSearch.getText());
		Provider objP = serviceP.searchId(obj.getIdProvider());
		return objP;
	}

	public void onButtonDataProviderAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Provider obj = dataResult();
		createDialogDataProvider(obj, "/gui/DataProvider.fxml", parentStage, "Dados do fornecedor");

	}

	public void onButtonCloseAction(ActionEvent event) {
		Utils.currentStage(event).close();
		load.loadView("/gui/ProductView.fxml", (ProductViewController controller) -> {
			controller.setProductServices(new ProductServices());
			controller.updatetableView();
		});
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

	public void createDialogFormProduct(Product obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ProductRegisterFormController controller = loader.getController();
			controller.setProduct(obj);
			controller.updateFormData();
			controller.setProductServices(new ProductServices());
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

	public void createDialogDataProvider(Provider obj, String absoluteName, Stage parentStage, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			DataProviderController controller = loader.getController();
			controller.setEntity(obj);
			controller.showDataProvider();

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
