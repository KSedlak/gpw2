package pl.spring.demo.desktop.controller;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.spring.demo.desktop.App;
import pl.spring.demo.desktop.model.client.Player.Player;
import pl.spring.demo.desktop.view.sceneMaker.SceneMaker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@Component
@DependsOn("player")
public class WelcomeController {

	@FXML
	Button enterButton;


	@Resource
	Player p;

	@FXML Label label;


	@FXML ImageView image;




	@FXML
	public void enterButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) enterButton.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("firstPage"));

	}

	@FXML
	private void initialize() {
			label.setText("Witaj, "+p.getFirstName()+" "+p.getLastName());
			try {
				image.setImage(new Image(App.class.getResource("images/startup.jpg").openStream())
);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}

