package pl.spring.demo.desktop.model.timeManager;

import java.time.LocalDate;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public interface TimeManager extends ApplicationListener<ApplicationEvent> {

	void start(LocalDate startDate);

	void makeOneDayStep();

	void end();

	LocalDate getStartDate();

	void setStartDate(LocalDate startDate);

	void setEndDate(LocalDate endDate);

	LocalDate getTodayDate();

	LocalDate getEndDate();

}
