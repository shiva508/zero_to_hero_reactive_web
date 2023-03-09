package com.pool.observable;

public class StockInfo {
	private final String ticker;
	private final double price;

	public StockInfo(String ticker, double price) {
		this.ticker = ticker;
		this.price = price;
	}

	@Override
	public String toString() {
		return "StockInfo [ticker=" + ticker + ", price=" + price + "]";
	}

}
