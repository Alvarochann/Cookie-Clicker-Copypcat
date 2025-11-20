package index;

import java.math.BigDecimal;

public class Cookies {
	private BigDecimal cookies;

	public Cookies(BigDecimal cookies) {
		super();
		this.cookies = cookies;
	}

	public BigDecimal getCookies() {
		return cookies;
	}

	public BigDecimal addCookie(BigDecimal cookies)
	{
		this.cookies = this.cookies.add(cookies);
		return this.cookies;
	}
	
	public BigDecimal subtractCookie(BigDecimal cookies)
	{
		this.cookies = this.cookies.subtract(cookies);
		return this.cookies;
	}
}
