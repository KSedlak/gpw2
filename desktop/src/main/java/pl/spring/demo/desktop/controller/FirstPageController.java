package pl.spring.demo.desktop.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.spring.demo.desktop.model.client.player.Player;
import pl.spring.demo.desktop.model.client.player.event.NoMoreActionToday;
import pl.spring.demo.desktop.model.client.player.strategy.strategies.buyCheapSellDrop.BuyCheapSellDrop;
import pl.spring.demo.desktop.model.client.player.strategy.strategies.random.RandomStrategy;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.timeManager.TimeManager;
import pl.spring.demo.desktop.view.TableRow.HistoricTableRow;
import pl.spring.demo.desktop.view.textAreaAppender.TextAreaAppender;
import pl.spring.demo.service.StockMarketService;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;

@Component
public class FirstPageController implements ApplicationListener<NoMoreActionToday> {

	@FXML
	Button authors;

	@Autowired
	StockMarketService stockService;

	@FXML
	Label firstNameValue;

	@FXML
	Label lastNameValue;

	@FXML
	Label plnValue;

	@FXML
	Label euroValue;

	@FXML
	Button next;

	@FXML
	Button initDate;

	@Resource
	Player player;

	@Resource
	TimeManager timeManager;

	@Resource
	BuyCheapSellDrop buyStrategy;

	@Resource
	RandomStrategy randomStrategy;

	@FXML
	DatePicker picker;

	@FXML
	TextArea loggingView;

	@FXML
	CheckBox enableLogging;

	@FXML
	Button startButton;

	@FXML
	DatePicker picker1;

	@FXML
	Label stockWalletValue;

	@FXML
	TableView<HistoricTableRow> historyTable;

	@FXML
	TableColumn<HistoricTableRow, String> dateColumn;

	@FXML
	TableColumn<HistoricTableRow, Double> plnColumn;

	@FXML
	TableColumn<HistoricTableRow, Double> euroColumn;

	@FXML
	TableColumn<HistoricTableRow, Double> stockColumn;

	@FXML
	TableColumn<HistoricTableRow, Double> allColumn;

	private ObservableList<HistoricTableRow> historic = FXCollections.observableArrayList();

	@FXML ComboBox combo;

	@FXML
	private void initialize() {
		firstNameValue.setText(player.getFirstName());
		lastNameValue.setText(player.getLastName());
		euroValue.setText(player.howMuchMoneyHave(Currency.EURO) + "");
		plnValue.setText(player.howMuchMoneyHave(Currency.PLN) + "");
		picker.setValue(LocalDate.parse("2013-01-02"));
		picker1.setValue(LocalDate.parse("2013-01-10"));
		setupLogginView();

		dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateString());
		plnColumn.setCellValueFactory(cellData -> cellData.getValue().getPlProperty().asObject());
		euroColumn.setCellValueFactory(cellData -> cellData.getValue().getEuroProperty().asObject());
		stockColumn.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
		allColumn.setCellValueFactory(cellData -> cellData.getValue().getAllProperty().asObject());

		historyTable.setItems(historic);

		combo.getItems().add(randomStrategy.getName());
		combo.getItems().add(buyStrategy.getName());
	}

	@FXML
	public void nextDay(ActionEvent event) {
		LocalDate current=timeManager.getEndDate();
		timeManager.setEndDate(current.plusDays(1));
		picker1.setValue(timeManager.getEndDate());
		timeManager.makeOneDayStep();
	}

	private void setupLogginView() {
		TextAreaAppender.setTextArea(loggingView);
		loggingView.setWrapText(true);
		loggingView.appendText("Starting Logging\n");
		loggingView.setEditable(false);
	}

	@FXML
	public void changeLoggingOption(ActionEvent event) {

		if (enableLogging.isSelected()) {
			Logger.getRootLogger().setLevel(Level.INFO);
		}
		if (!enableLogging.isSelected())
			Logger.getRootLogger().setLevel(Level.OFF);
	}

	@FXML
	public void startAction(ActionEvent event) {
		historic.clear();

		if(((String)combo.getSelectionModel().getSelectedItem()).equals(randomStrategy.getName())){
			player.setCurrentStrategy(randomStrategy);
		}

		if(((String)combo.getSelectionModel().getSelectedItem()).equals(buyStrategy.getName())){
			player.setCurrentStrategy(buyStrategy);
		}
		timeManager.setEndDate(picker1.getValue());
		timeManager.start(picker.getValue());

	}

	@Override
	public void onApplicationEvent(NoMoreActionToday event) {
		Double euro =player.howMuchMoneyHave(Currency.EURO);
		Double pln=player.howMuchMoneyHave(Currency.PLN);
		Double stock=player.howMuchStockValueHave();
		Double all= player.getValueOfAllResources();

		euroValue.setText(euro + "");
		plnValue.setText(pln + "");
		stockWalletValue.setText( stock+ "");

		String date=timeManager.getTodayDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
		historic.add(new HistoricTableRow(
				new SimpleStringProperty(date),
				new SimpleDoubleProperty(pln),
				new SimpleDoubleProperty(euro),
				new SimpleDoubleProperty(stock),
				new SimpleDoubleProperty(all)));

	}

}
