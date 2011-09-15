package com.androidsx.lottodroid.storage;

import java.util.List;

import com.androidsx.lottodroid.model.Lottery;

public interface LotteryDB<T extends Lottery> {
	
	public void storeLottery(final T lottery);
	public List<? extends Lottery> retrieveLottery() throws Exception;

}
