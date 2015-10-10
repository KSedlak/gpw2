package pl.spring.demo.desktop.model.calendar.event;

import java.time.LocalDate;
import org.springframework.context.ApplicationEvent;

public class DayChanged extends ApplicationEvent {

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