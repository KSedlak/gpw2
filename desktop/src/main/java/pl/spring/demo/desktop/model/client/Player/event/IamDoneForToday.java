package pl.spring.demo.desktop.model.client.Player.event;


import org.springframework.context.ApplicationEvent;

import pl.spring.demo.desktop.model.Status.Status;


public class IamDoneForToday extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private Status dayEnded;

	public IamDoneForToday(Status source) {
		super(source);
		this.dayEnded = source;
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Status getDayEnded() {
		return dayEnded;
	}









}