package pl.spring.demo.desktop.view.TableRow;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockWalletRow {

	private SimpleStringProperty name;

	private SimpleIntegerProperty quantity;

	private SimpleDoubleProperty value;

	public StockWalletRow(SimpleStringProperty name, SimpleIntegerProperty quantity, SimpleDoubleProperty value) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.value = value;
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public SimpleIntegerProperty getQuantity() {
		return quantity;
	}

	public void setQuantity(SimpleIntegerProperty quantity) {
		this.quantity = quantity;
	}

	public SimpleDoubleProperty getValue() {
		return value;
	}

	public void setValue(SimpleDoubleProperty value) {
		this.value = value;
	}

}
