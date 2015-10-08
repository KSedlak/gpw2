package pl.spring.demo.desktop;


import org.springframework.beans.factory.annotation.Value;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.spring.demo.desktop.view.sceneMaker.SceneMaker;


public class App  extends Application
{

	@Value("${app.title}")
	private static String title;

	public static void main(String[] args) {
	        launch(args);

	    }

	@Override
	public void start(Stage primaryStage) throws Exception {
	    primaryStage.setTitle(title);
    	Scene scene=SceneMaker.getSceneFromFXML("welcomePage");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getStylesheets().add(getClass().getResource("css/standard.css").toExternalForm());




		}





}