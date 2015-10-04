package pl.spring.demo.desktop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.spring.demo.desktop.sceneMaker.SceneMaker;


public class App  extends Application
{
	static ApplicationContext context;


	public static void main(String[] args) {
	        launch(args);
	    }

	@Override
	public void start(Stage primaryStage) throws Exception {

    	Scene primaryScene=SceneMaker.getSceneFromFXML("welcomePage");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        primaryScene.getStylesheets().add(getClass().getResource("css/standard.css").toExternalForm());

        context	= new ClassPathXmlApplicationContext("file:src/main/resources/pl/spring/demo/desktop/rest-config.xml");

		}




	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		App.context = context;
	}
}