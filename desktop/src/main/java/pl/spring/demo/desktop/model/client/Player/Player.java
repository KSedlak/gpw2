package pl.spring.demo.desktop.model.client.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.spring.demo.desktop.model.brokerageOffice.event.BrokerageOfficeOpened;
import pl.spring.demo.desktop.model.cantor.Cantor;
import pl.spring.demo.desktop.model.client.Person.Person;
import pl.spring.demo.desktop.model.client.Player.Wallet.Wallet;
import pl.spring.demo.desktop.model.currency.Currency;

@Component
public class Player extends Person implements ApplicationListener<BrokerageOfficeOpened>{

	private Wallet wallet;

	@Value("${player.moneyWalletBalance}")
	private double BALLANCE_EURO_TO_PLN;
	@Value("${player.moneyWalletBalanceTolerance}")
	private double BALLANCE_TOLERANCE;

	@Value("${player.lastName}")
	private  String lastName;
	@Autowired
	private Cantor cantor;




 @Autowired
	public Player(@Value("${player.firstName}") String f,
			@Value("${player.lastName}") String l) {
		super(f,l);
		wallet=new Wallet();
	}





	public double checkCurrencyBallanceMoneyInWallet(Currency a, Currency b){
		try {

		if(wallet.getWallet().containsKey(a)&&wallet.getWallet().containsKey(b)){
			double cur_A_PLN_value=cantor.getNbpRate(a)*wallet.getWallet().get(a);//value of money
			double cur_B_PLN_value=cantor.getNbpRate(b)*wallet.getWallet().get(b);
			return cur_A_PLN_value/cur_B_PLN_value;
		}

			throw new Exception("currency not exist in wallet");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return 0;

	}





	@Override
	public void onApplicationEvent(BrokerageOfficeOpened event) {
//call strategy to get transactions
//do transactions first buy then sell
//after transaction update wallet money
//do next transactions
//tell brokerage to closed
//change money
//tell cantor to closed


	}





}
