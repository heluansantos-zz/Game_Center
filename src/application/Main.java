package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	/**
	 * Classe principal da aplicação contendo o método "main".
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	
	private static Scene mainScene;

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			mainScene = new Scene(scrollPane);

			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon1.jpg")));
			// primaryStage.setResizable(false);
			primaryStage.setScene(mainScene);
			primaryStage.setHeight(700);
			// primaryStage.setMaxHeight(700);
			// primaryStage.setMinHeight(600);
			// primaryStage.setMinWidth(990);
			// primaryStage.setMaxWidth(1300);
			primaryStage.setWidth(1200);
			primaryStage.setTitle("Eletro Games Center");
			primaryStage.getStyle().getClass().isInterface();
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Scene getMainScene() {
		return mainScene;
	}

}
