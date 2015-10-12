package pl.spring.demo.desktop;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.spring.demo.desktop.view.sceneMaker.SceneMaker;

@Component
public class App extends Application {

	@Value("${app.title}")
	private String title;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(title);
		Scene scene = SceneMaker.getSceneFromFXML("welcomePage");
		primaryStage.setScene(scene);
		primaryStage.show();

		PropertyConfigurator.configure(getClass().getResource("config/log4j.properties").openStream());
		scene.getStylesheets().add(getClass().getResource("css/standard.css").toExternalForm());
	}

}