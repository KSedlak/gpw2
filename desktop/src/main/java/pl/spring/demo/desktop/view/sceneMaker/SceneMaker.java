package pl.spring.demo.desktop.view.sceneMaker;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import javafx.scene.*;
import pl.spring.demo.desktop.App;

public class SceneMaker {

	private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"file:src/main/resources/pl/spring/demo/desktop/spring/spring-config.xml");

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Scene getSceneFromFXML(String fxmlName) throws IOException {
		Parent root = (Parent) load(fxmlName);
		Scene scene = new Scene(root);
		return scene;
	}

	public static Object load(String name) {
		try (InputStream fxmlStream = App.class.getResource("fxml/" + name + ".fxml").openStream()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setControllerFactory(getApplicationContext()::getBean);
			return loader.load(fxmlStream);
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

}