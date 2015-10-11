package pl.spring.demo.desktop.model.calendar.event;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

public class DayChanged extends ApplicationEvent {
	final static Logger logger=Logger.getLogger("DayChangedEvent");
	private static final long serialVersionUID = 1L;

	private final LocalDate currentDate;

	public DayChanged(LocalDate source) {
		super(source);
		this.currentDate = source;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}



}