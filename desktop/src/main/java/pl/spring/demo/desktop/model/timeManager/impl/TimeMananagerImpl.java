package pl.spring.demo.desktop.model.timeManager.impl;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import pl.spring.demo.desktop.model.brokerageOffice.event.BrokerageOfficeStatusChanged;
import pl.spring.demo.desktop.model.calendar.Calendar;
import pl.spring.demo.desktop.model.cantor.event.CantorStatusChanged;
import pl.spring.demo.desktop.model.status.Status;
import pl.spring.demo.desktop.model.timeManager.TimeManager;

@Component
public class TimeMananagerImpl implements TimeManager {

    private LocalDate startDate;
    private LocalDate endDate;
    @Autowired
    private Calendar calendar;

    private Status cantorStatus;
    private Status brokerageOfficeStatus;
	final static Logger logger=Logger.getLogger("TimeManager");

    public TimeMananagerImpl() {
    	super();
    	brokerageOfficeStatus=Status.Closed;
    	cantorStatus=Status.Closed;
	}
    @Override
	public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public Calendar getCalendar() {
        return calendar;
    }

	public Status getCantorStatus() {
		return cantorStatus;
	}
	public void setCantorStatus(Status cantorStatus) {
		this.cantorStatus = cantorStatus;
	}
	public Status getBrokerageOfficeStatus() {
		return brokerageOfficeStatus;
	}
	public void setBrokerageOfficeStatus(Status brokerageOfficeStatus) {
		this.brokerageOfficeStatus = brokerageOfficeStatus;
	}
	@Override
	public void start(LocalDate start) {
	logger.info("Simulation start with date: "+start);
	this.startDate=start;
	calendar.setCurrentDay(startDate);
	}
	@Override
	public void makeOneDayStep() {
		if(canMakeNewDay()){
			LocalDate current=calendar.getCurrentDay();
			calendar.setCurrentDay(current.plusDays(1));
		}
	}
	@Override
	public void end() {
		logger.info("Simulation end with date: "+calendar.getCurrentDay());

	}
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof CantorStatusChanged){
			CantorStatusChanged ev = (CantorStatusChanged) event;
			cantorStatus=ev.getCantorStatus();
		}

		if(event instanceof BrokerageOfficeStatusChanged){
			 BrokerageOfficeStatusChanged ev = (BrokerageOfficeStatusChanged) event;
			brokerageOfficeStatus=ev.getBrokerageOfficeStatus();
		}


	}

	private Boolean canMakeNewDay(){
	LocalDate nextDay=calendar.getCurrentDay().plusDays(1);

	if(nextDay.isAfter(endDate)){
		logger.info("next day will be after endDate");
		end();
		return false;
	}

	return (brokerageOfficeStatus.equals(Status.Closed) && cantorStatus.equals(Status.Closed));
	}

}
