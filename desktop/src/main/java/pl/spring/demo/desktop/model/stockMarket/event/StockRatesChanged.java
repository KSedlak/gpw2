package pl.spring.demo.desktop.model.stockMarket.event;


import org.springframework.context.ApplicationEvent;


public class StockRatesChanged extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private Boolean isChanged;
	public StockRatesChanged(Boolean source) {
		super(source);
		this.isChanged=source;
	}
	public Boolean getIsChanged() {
		return isChanged;
	}




}