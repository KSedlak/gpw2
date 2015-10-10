package pl.spring.demo.desktop.model.brokerageOffice.event;


import org.springframework.context.ApplicationEvent;

import pl.spring.demo.desktop.model.status.Status;

public class BrokerageOfficeStatusChanged extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private Status brokerageOfficeStatus;


	public BrokerageOfficeStatusChanged(Status source) {
		super(source);
		this.brokerageOfficeStatus=source;
	}


	public Status getBrokerageOfficeStatus() {
		return brokerageOfficeStatus;
	}





}