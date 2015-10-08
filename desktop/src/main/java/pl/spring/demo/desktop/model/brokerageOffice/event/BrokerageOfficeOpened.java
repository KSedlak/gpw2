package pl.spring.demo.desktop.model.brokerageOffice.event;


import org.springframework.context.ApplicationEvent;

import pl.spring.demo.desktop.model.Status.Status;

public class BrokerageOfficeOpened extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private Status brokerageOfficeStatus;


	public BrokerageOfficeOpened(Status source) {
		super(source);
		this.brokerageOfficeStatus=source;
	}


	public Status getBrokerageOfficeStatus() {
		return brokerageOfficeStatus;
	}





}