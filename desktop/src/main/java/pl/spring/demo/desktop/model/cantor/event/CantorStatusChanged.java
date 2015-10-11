package pl.spring.demo.desktop.model.cantor.event;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

import pl.spring.demo.desktop.model.status.Status;

public class CantorStatusChanged extends ApplicationEvent {
	final static Logger logger=Logger.getLogger("CantorStatusChanged");
	private static final long serialVersionUID = 1L;

	private Status cantorStatus;


	public CantorStatusChanged(Status source) {
		super(source);
		this.cantorStatus=source;
		logger.info("Cantor changed status to: "+cantorStatus);
	}


	public Status getCantorStatus() {
		return cantorStatus;
	}






}