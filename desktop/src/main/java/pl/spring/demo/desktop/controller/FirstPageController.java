package pl.spring.demo.desktop.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.spring.demo.desktop.model.client.player.Player;
import pl.spring.demo.desktop.model.client.player.event.NoMoreActionToday;
import pl.spring.demo.desktop.model.client.player.stockWallet.StockWallet;
import pl.spring.demo.desktop.model.client.player.strategy.strategies.buyCheapSellDrop.BuyCheapSellDrop;
import pl.spring.demo.desktop.model.client.player.strategy.strategies.random.RandomStrategy;
import pl.spring.demo.desktop.model.client.player.wallet.Wallet;
import pl.spring.demo.desktop.model.currency.Currency;
import pl.spring.demo.desktop.model.timeManager.TimeManager;
import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;
import pl.spring.demo.desktop.model.utils.doubleRounder.DoubleRounder;
import pl.spring.demo.desktop.view.TableRow.HistoricTableRow;
import pl.spring.demo.desktop.view.TableRow.StockWalletRow;
import pl.spring.demo.desktop.view.textAreaAppender.TextAreaAppender;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
import pl.spring.demo.service.StockMarketService;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

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

	@Resource
	Wallet wallet;

	@Resource
	StockWallet stockWallet;

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

	@FXML
	ComboBox<String> combo;

	@FXML
	TextField euroTextField;

	@FXML
	TextField plnTextField;

	@FXML
	Label timeSimulation;

	@FXML
	TableColumn<StockWalletRow, String> stockWallletStockColumn;

	@FXML
	TableColumn<StockWalletRow, Integer> stockWalletQuantity;

	@FXML
	TableColumn<StockWalletRow, Double> stockWalletValueStock;

	@FXML
	TableView<StockWalletRow> stockWalletTable;

	private ObservableList<HistoricTableRow> historic = FXCollections.observableArrayList();
	private ObservableList<StockWalletRow> stockWalletPresentation = FXCollections.observableArrayList();
	private ObservableList<String> transactionsPresentation = FXCollections.observableArrayList();
	@FXML
	ListView<String> transactions;

	@FXML
	private void initialize() {
		firstNameValue.setText(player.getFirstName());
		lastNameValue.setText(player.getLastName());

		euroValue.setText(player.howMuchMoneyHave(Currency.EURO) + "");
		plnValue.setText(player.howMuchMoneyHave(Currency.PLN) + "");
		stockWalletValue.setText(0 + "");

		picker.setValue(LocalDate.parse("2013-01-02"));
		picker1.setValue(LocalDate.parse("2013-01-10"));

		setupLogginView();

		plnTextField.setText(player.howMuchMoneyHave(Currency.PLN) + "");
		euroTextField.setText(player.howMuchMoneyHave(Currency.EURO) + "");

		dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateString());
		plnColumn.setCellValueFactory(cellData -> cellData.getValue().getPlProperty().asObject());
		euroColumn.setCellValueFactory(cellData -> cellData.getValue().getEuroProperty().asObject());
		stockColumn.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
		allColumn.setCellValueFactory(cellData -> cellData.getValue().getAllProperty().asObject());

		historyTable.setItems(historic);

		stockWallletStockColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		stockWalletQuantity.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
		stockWalletValueStock.setCellValueFactory(cellData -> cellData.getValue().getValue().asObject());

		stockWalletTable.setItems(stockWalletPresentation);
		transactions.setItems(transactionsPresentation);

		combo.getItems().add(randomStrategy.getName());
		combo.getItems().add(buyStrategy.getName());
		combo.getSelectionModel().select(0);
	}

	@FXML
	public void nextDay(ActionEvent event) {
		LocalDate current = timeManager.getEndDate();
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
		loggingView.setText("");
		historic.clear();
		wallet.clear();
		stockWallet.clear();
		transactions.getItems().clear();

		wallet.addToWallet(Currency.PLN, Double.parseDouble(plnTextField.getText()));
		wallet.addToWallet(Currency.EURO, Double.parseDouble(euroTextField.getText()));

		if (((String) combo.getSelectionModel().getSelectedItem()).equals(randomStrategy.getName())) {
			player.setCurrentStrategy(randomStrategy);
		}

		if (((String) combo.getSelectionModel().getSelectedItem()).equals(buyStrategy.getName())) {
			player.setCurrentStrategy(buyStrategy);
		}
		timeManager.setEndDate(picker1.getValue());
		timeManager.start(picker.getValue());

	}

	@Override
	public void onApplicationEvent(NoMoreActionToday event) {
		stockWalletPresentation.clear();
		Double euro = player.howMuchMoneyHave(Currency.EURO);
		Double pln = player.howMuchMoneyHave(Currency.PLN);
		Double stock = player.howMuchStockValueHave();
		Double all = player.getValueOfAllResources();

		plnValue.setText(pln + "");
		euroValue.setText(euro + "");
		stockWalletValue.setText(stock + "");

		String date = timeManager.getTodayDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
		historic.add(new HistoricTableRow(new SimpleStringProperty(date), new SimpleDoubleProperty(pln),
				new SimpleDoubleProperty(euro), new SimpleDoubleProperty(stock), new SimpleDoubleProperty(all)));

		HashMap<StockDailyRecordTo, Integer> wallet = stockWallet.showWallet();
		for (StockDailyRecordTo s : wallet.keySet()) {
			stockWalletPresentation.add(new StockWalletRow(new SimpleStringProperty(s.getCompany().getName()),
					new SimpleIntegerProperty(wallet.get(s)),
					new SimpleDoubleProperty(DoubleRounder.roundToMoney(wallet.get(s) * s.getValue()))));
		}

		List<MarketTransaction> doneTransactions = player.getTodayWork();
		transactions.getItems().add(" NEW DAY:  " + timeManager.getTodayDate());
		for (MarketTransaction m : doneTransactions) {
			transactions.getItems()
					.add("Type: " + m.getType().toString() + "         name: " + m.getStock().getCompany().getName()
							+ "    quantity: " + m.getBrokerageOfficeAcceptedNumber() + "      rate: "
							+ m.getBrokerageOfficeAcceptedRate());
		}
		transactions.getItems().add("");
		player.getTodayWork().clear();
	}

}
