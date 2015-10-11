package pl.spring.demo.desktop.model.utils.doubleRounder;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleRounder {
	public static double roundToMoney(double value) {

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
