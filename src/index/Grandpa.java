package index;

import java.math.BigDecimal;

public class Grandpa extends Machines{

	@Override
	public BigDecimal addCookies(Cookies cookies) {
		cookies.addCookie(this.count.multiply(cookiesPerSecond));
		return null;
	}

	public Grandpa() {
		super();
		this.price = new BigDecimal(100);
		this.count = BigDecimal.ZERO;
		this.cookiesPerSecond = BigDecimal.ZERO;
	}
	
}
