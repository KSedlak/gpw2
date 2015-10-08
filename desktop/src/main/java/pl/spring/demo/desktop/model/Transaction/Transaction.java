package pl.spring.demo.desktop.model.Transaction;

import org.springframework.beans.factory.annotation.Value;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public abstract class Transaction {

	private StockDailyRecordTo  stock;
	private int numberOfStockRequested;
	private double brokerageOfficeAcceptedRate;
	private int brokerageOfficeAcceptedNumber;
	private StatusOfTransaction status;
	private Double upperBoundPriceRandomizer;
	private Double lowerBoundPriceRandomizer;

	@Value("${transaction.lowerBoundNumberOfStockRandomizer}")
	private Double lowerBoundWarrantedNumberOfStock;

	@Value("${transaction.upperrBoundNumberOfStockRandomizer}")
	private Double upperBoundWarrantedNumberOfStock;

	public Transaction(StockDailyRecordTo stock, int numberOfStockRequested) {
		super();
		this.stock = stock;
		this.numberOfStockRequested = numberOfStockRequested;
	}

	public StatusOfTransaction getStatus() {
		return status;
	}

	public Double getUpperBoundPriceRandomizer() {
		return upperBoundPriceRandomizer;
	}

	public Double getLowerBoundPriceRandomizer() {
		return lowerBoundPriceRandomizer;
	}

	public Double getLowerBoundWarrantedNumberOfStock() {
		return lowerBoundWarrantedNumberOfStock;
	}

	public Double getUpperBoundWarrantedNumberOfStock() {
		return upperBoundWarrantedNumberOfStock;
	}

	public void setStatus(StatusOfTransaction status) {
		this.status = status;
	}

	public StockDailyRecordTo getStock() {
		return stock;
	}
	public void setStock(StockDailyRecordTo stock) {
		this.stock = stock;
	}
	public int getNumberOfStockRequested() {
		return numberOfStockRequested;
	}
	public void setNumberOfStockRequested(int numberOfStockRequested) {
		this.numberOfStockRequested = numberOfStockRequested;
	}
	public double getBrokerageOfficeAcceptedRate() {
		return brokerageOfficeAcceptedRate;
	}
	public void setBrokerageOfficeAcceptedRate(double brokerageOfficeAcceptedRate) {
		this.brokerageOfficeAcceptedRate = brokerageOfficeAcceptedRate;
	}
	public int getBrokerageOfficeAcceptedNumber() {
		return brokerageOfficeAcceptedNumber;
	}
	public void setBrokerageOfficeAcceptedNumber(int brokerageOfficeAcceptedNumber) {
		this.brokerageOfficeAcceptedNumber = brokerageOfficeAcceptedNumber;
	}


}
