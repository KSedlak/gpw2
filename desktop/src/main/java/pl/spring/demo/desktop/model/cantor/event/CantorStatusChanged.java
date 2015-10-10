package pl.spring.demo.desktop.model.cantor.event;


import org.springframework.context.ApplicationEvent;

import pl.spring.demo.desktop.model.status.Status;

public class CantorStatusChanged extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private Status CantorStatus;


	public CantorStatusChanged(Status source) {
		super(source);
		this.CantorStatus=source;
	}


	public Status getCantorStatus() {
		return CantorStatus;
	}






}