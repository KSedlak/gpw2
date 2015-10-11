package pl.spring.demo.desktop.controller;

import java.time.LocalDate;
import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.spring.demo.desktop.model.client.player.Player;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.timeManager.TimeManager;
import pl.spring.demo.desktop.view.textAreaAppender.TextAreaAppender;
import pl.spring.demo.service.StockMarketService;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;

@Component
public class FirstPageController {


	@FXML
	Button authors;

	@Autowired
	StockMarketService stockService;

	@FXML Label firstNameValue;

	@FXML Label lastNameValue;

	@FXML Label plnValue;

	@FXML Label euroValue;

	@FXML Button next;


	@FXML Button initDate;

	@Resource
	Player player;

	@Resource
	TimeManager timeManager;

	@FXML DatePicker picker;

	@FXML TextArea loggingView;

	@FXML CheckBox enableLogging;




	@FXML
	private void initialize() {
			firstNameValue.setText(player.getFirstName());
			lastNameValue.setText(player.getLastName());
			euroValue.setText(player.howMuchMoneyHave(Currency.EURO)+"");
			plnValue.setText(player.howMuchMoneyHave(Currency.PLN)+"");
			setupLogginView();
			timeManager.setEndDate(LocalDate.parse("2013-01-04"));
			timeManager.start(LocalDate.parse("2013-01-02"));


	}




	@FXML
	public void nextDay(ActionEvent event) {
		timeManager.makeOneDayStep();
	}


	 private void setupLogginView() {
		 TextAreaAppender.setTextArea(loggingView);
	        loggingView.setWrapText(true);
	        loggingView.appendText("Starting Logging\n");
	        loggingView.setEditable(false);
	    }




	@FXML public void changeLoggingOption(ActionEvent event) {

		if(enableLogging.isSelected()){
			Logger.getRootLogger().setLevel(Level.INFO);
		}
		if(!enableLogging.isSelected())
			Logger.getRootLogger().setLevel(Level.OFF);
	}


}
