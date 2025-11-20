package index;

import java.math.BigDecimal;

public class Rats extends Machines{

	@Override
	public BigDecimal addCookies(Cookies cookies) {
		cookies.addCookie(this.count.multiply(cookiesPerSecond));
		return null;
	}

	public Rats() {
		super();
		this.price = BigDecimal.TEN;
		this.count = BigDecimal.ZERO;
		this.cookiesPerSecond = BigDecimal.ZERO;
	}
	
}
