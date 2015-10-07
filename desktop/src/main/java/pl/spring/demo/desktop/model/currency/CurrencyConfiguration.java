package pl.spring.demo.desktop.model.currency;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

@Configurable 
public class CurrencyConfiguration {
	
	
	 
	  @Value("${currency.default}") 
	  private String DEFAULT_CURRENCY;
	  
	  @Value("${currency.EURO.lower}") 
	  private double euroLower;
	  @Value("${currency.EURO.upper}") 
	  private double euroUpper;
	 
	  @Value("${currency.USD.lower}") 
	  private double usdLower;
	  @Value("${currency.USD.upper}") 
	  private double usdUpper;
	  
	  @PostConstruct 
	  public void postConstruct() { 
	    initializeCurrencyEnum(); 
	  } 
	 
	  private void initializeCurrencyEnum() { 
		  		  
		  
		  Currency.valueOf(DEFAULT_CURRENCY).setLower(1.0);
		  Currency.valueOf(DEFAULT_CURRENCY).setLower(1.0);
		  
		  Currency.EURO.setLower(euroLower);
		  Currency.EURO.setLower(euroUpper);
		  
		  Currency.USD.setLower(usdLower);
		  Currency.USD.setLower(usdUpper);
	  } 
	 
	
}
