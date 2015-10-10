package pl.spring.demo.desktop.model.timeManager.impl;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import pl.spring.demo.desktop.model.Calendar.Calendar;
import pl.spring.demo.desktop.model.Status.Status;
import pl.spring.demo.desktop.model.brokerageOffice.event.BrokerageOfficeStatusChanged;
import pl.spring.demo.desktop.model.cantor.event.CantorStatusChanged;
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

	}
	public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
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
	public void start() {
	logger.info("Simulation start with date: "+startDate);

	}
	@Override
	public void makeOneDayStep() {
		if(canMakeNewDay()){
		calendar.nextDay();
		}
	}
	@Override
	public void end() {


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
		end();
		return false;
	}

	return (brokerageOfficeStatus.equals(Status.Closed) && cantorStatus.equals(Status.Closed));
	}

}
