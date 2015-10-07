package pl.spring.demo.desktop.model.Calendar;

import java.time.LocalDate;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import pl.spring.demo.desktop.model.Calendar.Events.DayChanged;

@Component
public class Calendar implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationEventPublisher eventPublisher;
	private LocalDate currentDay;
	private ApplicationContext applicationContext;

	public Calendar() {
		super();
	}
	public Calendar(LocalDate currentDay) {
		super();
		this.currentDay = currentDay;
	}

	public LocalDate getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(LocalDate currentDay) {
		this.currentDay = currentDay;
	}

	public boolean canGetNewDay() {

		return true;
	}

	public void nextDay() {
		if (canGetNewDay()) {
			currentDay = currentDay.plusDays(1);
			 applicationContext.publishEvent(new DayChanged(currentDay));
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
