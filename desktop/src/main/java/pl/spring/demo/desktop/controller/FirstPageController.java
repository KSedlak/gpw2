package pl.spring.demo.desktop.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.spring.demo.desktop.model.Calendar.Calendar;
import pl.spring.demo.desktop.sceneMaker.SceneMaker;
import pl.spring.demo.service.StockDailyRecordService;
import pl.spring.demo.service.StockMarketService;

@Component
public class FirstPageController {
	@FXML
	Button books;

	@FXML
	Button authors;

	@Autowired
	StockMarketService stockService;

	@Autowired
	Calendar calendar;

	@FXML
	public void booksButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) books.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("booksMenu"));
	}

	@FXML
	public void authorsButtonAction(ActionEvent event) throws IOException {


		};

}
