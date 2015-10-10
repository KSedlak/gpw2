package pl.spring.demo.desktop.controller;

import java.time.LocalDate;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.spring.demo.desktop.model.calendar.Calendar;
import pl.spring.demo.desktop.model.client.player.Player;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.view.textAreaAppender.TextAreaAppender;
import pl.spring.demo.service.StockMarketService;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

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
	Calendar calendar;

	@FXML DatePicker picker;

	@FXML TextArea loggingView;




	@FXML
	private void initialize() {
			firstNameValue.setText(player.getFirstName());
			lastNameValue.setText(player.getLastName());
			euroValue.setText(player.howMuchMoneyHave(Currency.EURO)+"");
			plnValue.setText(player.howMuchMoneyHave(Currency.PLN)+"");
			calendar.setCurrentDay(LocalDate.parse("2013-01-01"));
			picker.setValue(calendar.getCurrentDay());
			setupLogginView();


	}




	@FXML public void nextDay(ActionEvent event) {


		calendar.nextDay();
		firstNameValue.setText(player.getFirstName());
		lastNameValue.setText(player.getLastName());
		euroValue.setText(player.howMuchMoneyHave(Currency.EURO)+"");
		plnValue.setText(player.howMuchMoneyHave(Currency.PLN)+"");
		picker.setValue(calendar.getCurrentDay());

	}


	 private void setupLogginView() {
		 TextAreaAppender.setTextArea(loggingView);
	        loggingView.setWrapText(true);
	        loggingView.appendText("Starting Logging");
	        loggingView.setEditable(false);
	    }


}
