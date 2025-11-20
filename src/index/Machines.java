package index;

import java.math.BigDecimal;

public abstract class Machines {
	public BigDecimal price;
	public BigDecimal count;
	public BigDecimal cookiesPerSecond;
	
	public abstract BigDecimal addCookies(Cookies cookies);

}
