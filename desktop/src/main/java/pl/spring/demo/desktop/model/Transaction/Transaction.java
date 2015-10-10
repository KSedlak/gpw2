package pl.spring.demo.desktop.model.Transaction;


import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public abstract class Transaction {

	private StockDailyRecordTo  stock;
	private int numberOfStockRequested;
	private double brokerageOfficeAcceptedRate;
	private int brokerageOfficeAcceptedNumber;
	private StatusOfTransaction status;
	private Double upperBoundPriceRandomizer;
	private Double lowerBoundPriceRandomizer;
	private Double lowerBoundWarrantedNumberOfStock;
	private Double upperBoundWarrantedNumberOfStock;
	private Double brokerageOfficeCommission;



	public Double getBrokerageOfficeCommission() {
		return brokerageOfficeCommission;
	}


	public void setBrokerageOfficeCommission(Double brokerageOfficeCommission) {
		this.brokerageOfficeCommission = brokerageOfficeCommission;
	}


	public Transaction() {
		super();
		this.status=StatusOfTransaction.Created;
	}


	public Transaction(StockDailyRecordTo stock, int numberOfStockRequested) {
		super();
		this.stock = stock;
		this.numberOfStockRequested = numberOfStockRequested;
		this.status=StatusOfTransaction.Created;
	}


	public Transaction(StockDailyRecordTo stock, int numberOfStockRequested, Double upperBoundPriceRandomizer,
			Double lowerBoundPriceRandomizer, Double lowerBoundWarrantedNumberOfStock,
			Double upperBoundWarrantedNumberOfStock) {
			super();
			this.stock = stock;
			this.numberOfStockRequested = numberOfStockRequested;
			this.upperBoundPriceRandomizer = upperBoundPriceRandomizer;
			this.lowerBoundPriceRandomizer = lowerBoundPriceRandomizer;
			this.lowerBoundWarrantedNumberOfStock = lowerBoundWarrantedNumberOfStock;
			this.upperBoundWarrantedNumberOfStock = upperBoundWarrantedNumberOfStock;
			this.status=StatusOfTransaction.Created;
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

	public double getValueOfBrokerageOfficeOffer() {
		return brokerageOfficeAcceptedRate*brokerageOfficeAcceptedNumber;
	}
	public double getChangeValueOFTransaction() {
		double clientPropValue = getStock().getValue() * getNumberOfStockRequested();
		double brokerPropValue = getValueOfBrokerageOfficeOffer();

		double change=Math.abs(clientPropValue-brokerPropValue);
		return (change/clientPropValue)*100;
	}
}
