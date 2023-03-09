package com.pool.observable;

import java.util.Random;


public class StockFetcher {

	public static StockInfo fetch(String stockname) {
		return new StockInfo(stockname,new Random().nextDouble(10000));
	}

}
