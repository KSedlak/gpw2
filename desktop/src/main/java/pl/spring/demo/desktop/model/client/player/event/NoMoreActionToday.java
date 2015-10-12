package pl.spring.demo.desktop.model.client.player.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

import pl.spring.demo.desktop.model.status.Status;

public class NoMoreActionToday extends ApplicationEvent {
	final static Logger logger = Logger.getLogger("NoMoreActionTodayEvent");
	private static final long serialVersionUID = 1L;

	private Status dayEnded;

	public NoMoreActionToday(Status source) {
		super(source);
		this.dayEnded = source;
		logger.info("NoMoreActionToday event fired");
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Status getDayEnded() {
		return dayEnded;
	}

}