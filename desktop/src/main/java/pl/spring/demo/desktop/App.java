package pl.spring.demo.desktop;


import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import pl.spring.demo.service.CompanyService;
import pl.spring.demo.service.StockDailyRecordService;
import pl.spring.demo.service.impl.CompanyServiceImpl;
import pl.spring.demo.sprinLoader.SceneMaker;
import javafx.scene.Parent;

public class App  extends Application
{
	static ApplicationContext context;


	public static void main(String[] args) {
	        launch(args);

	    }

	@Override
	public void start(Stage primaryStage) throws Exception {


    	
    	Scene scene=SceneMaker.getSceneFromFXML("welcomePage");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("css/standard.css").toExternalForm());
        

	

		}




	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		App.context = context;
	}
}