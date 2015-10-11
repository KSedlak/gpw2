package pl.spring.demo.desktop.view.TableRow;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class HistoricTableRow {

	private SimpleStringProperty dateString;
	private DoubleProperty plProperty;
	private DoubleProperty euroProperty;
	private DoubleProperty stockProperty;
	private DoubleProperty allProperty;



	public HistoricTableRow(SimpleStringProperty dateString, SimpleDoubleProperty plProperty, SimpleDoubleProperty euroProperty,
			SimpleDoubleProperty stockProperty, SimpleDoubleProperty allProperty) {
		super();
		this.dateString = dateString;
		this.plProperty = plProperty;
		this.euroProperty = euroProperty;
		this.stockProperty = stockProperty;
		this.allProperty = allProperty;
	}
	public SimpleStringProperty getDateString() {
		return dateString;
	}
	public void setDateString(SimpleStringProperty dateString) {
		this.dateString = dateString;
	}
	public DoubleProperty getPlProperty() {
		return plProperty;
	}
	public void setPlProperty(DoubleProperty plProperty) {
		this.plProperty = plProperty;
	}
	public DoubleProperty getEuroProperty() {
		return euroProperty;
	}
	public void setEuroProperty(DoubleProperty euroProperty) {
		this.euroProperty = euroProperty;
	}
	public DoubleProperty getStockProperty() {
		return stockProperty;
	}
	public void setStockProperty(DoubleProperty stockProperty) {
		this.stockProperty = stockProperty;
	}
	public DoubleProperty getAllProperty() {
		return allProperty;
	}
	public void setAllProperty(DoubleProperty allProperty) {
		this.allProperty = allProperty;
	}

}
