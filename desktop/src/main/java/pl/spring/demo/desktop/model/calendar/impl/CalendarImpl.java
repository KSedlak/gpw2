package pl.spring.demo.desktop.model.calendar.impl;

import java.time.LocalDate;


import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import pl.spring.demo.desktop.model.calendar.Calendar;
import pl.spring.demo.desktop.model.calendar.event.DayChanged;


@Component
public class CalendarImpl implements Calendar {

	private LocalDate currentDay;
	private ApplicationContext applicationContext;
	final static Logger logger=Logger.getLogger("Calendar");

	public CalendarImpl() {
		super();
	}

	public CalendarImpl(LocalDate currentDay) {
		super();
		this.currentDay = currentDay;
	}
	@Override
	public LocalDate getCurrentDay() {
		return currentDay;
	}
	@Override
	public void setCurrentDay(LocalDate currentDay) {
		this.currentDay = currentDay;
		logger.info("Calendar is set ond day: "+currentDay);
		applicationContext.publishEvent(new DayChanged(currentDay));

	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
