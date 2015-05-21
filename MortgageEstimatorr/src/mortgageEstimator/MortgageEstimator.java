package mortgageEstimator;

import java.io.IOException;

import mortgageEstimator.view.MortageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MortgageEstimator extends Application {

	private Stage primaryStage;
	private AnchorPane mortageScreen;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage
				.setTitle("Buying your first house");
		showEstimatorScreen();
	}

	public void showEstimatorScreen() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MortgageEstimator.class
					.getResource("view/MortageScreen.fxml"));
			mortageScreen = (AnchorPane) loader.load();
			Scene mortageScene = new Scene(mortageScreen);
			primaryStage.setScene(mortageScene);
			MortageController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
