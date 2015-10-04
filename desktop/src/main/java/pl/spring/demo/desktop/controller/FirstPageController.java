package pl.spring.demo.desktop.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.spring.demo.desktop.sceneMaker.SceneMaker;

public class FirstPageController {
	@FXML
	Button books;

	@FXML
	Button authors;

	@FXML
	public void booksButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) books.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("booksMenu"));
	}

	@FXML
	public void authorsButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) authors.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("authorsMenu"));
	}
}
