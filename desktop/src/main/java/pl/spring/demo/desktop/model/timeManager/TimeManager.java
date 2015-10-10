package pl.spring.demo.desktop.model.timeManager;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;



	public interface TimeManager extends ApplicationListener<ApplicationEvent>{

	void start();

	void makeOneDayStep();

	void end();

}
