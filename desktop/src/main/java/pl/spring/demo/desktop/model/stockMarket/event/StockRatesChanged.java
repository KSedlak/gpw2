package pl.spring.demo.desktop.model.stockMarket.event;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;


public class StockRatesChanged extends ApplicationEvent {
	final static Logger logger=Logger.getLogger("StockRatesChanged");
	private static final long serialVersionUID = 1L;

	private Boolean isChanged;
	public StockRatesChanged(Boolean source) {
		super(source);
		this.isChanged=source;
		logger.info("event fired");
	}
	public Boolean getIsChanged() {
		return isChanged;
	}




}