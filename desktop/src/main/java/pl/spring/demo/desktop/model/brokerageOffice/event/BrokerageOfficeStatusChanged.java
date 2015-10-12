package pl.spring.demo.desktop.model.brokerageOffice.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

import pl.spring.demo.desktop.model.status.Status;

public class BrokerageOfficeStatusChanged extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger("BrokerageOfficeStatusChangeEvent");
	private Status brokerageOfficeStatus;

	public BrokerageOfficeStatusChanged(Status source) {
		super(source);
		this.brokerageOfficeStatus = source;
		logger.info("BrokerageOffice change status to: " + brokerageOfficeStatus.toString());
	}

	public Status getBrokerageOfficeStatus() {
		return brokerageOfficeStatus;
	}

}