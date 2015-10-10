package pl.spring.demo.desktop.model.calendar;

import java.time.LocalDate;

import org.springframework.context.ApplicationContextAware;

public interface Calendar extends ApplicationContextAware {

	LocalDate getCurrentDay();

	void setCurrentDay(LocalDate currentDay);

	void nextDay();

}
