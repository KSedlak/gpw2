package pl.spring.demo.desktop.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.spring.demo.desktop.sceneMaker.SceneMaker;

public class WelcomeController {

	@FXML
	Button enterButton;

	@FXML
	public void enterButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) enterButton.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("firstPage"));
	}

}
