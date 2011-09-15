package com.androidsx.lottodroid.storage;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;

import com.androidsx.lottodroid.model.Bonoloto;
import com.androidsx.lottodroid.model.CuponazoOnce;
import com.androidsx.lottodroid.model.Euromillon;
import com.androidsx.lottodroid.model.GordoPrimitiva;
import com.androidsx.lottodroid.model.Loteria7_39;
import com.androidsx.lottodroid.model.LoteriaNacional;
import com.androidsx.lottodroid.model.Lototurf;
import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;
import com.androidsx.lottodroid.model.Lotto6_49;
import com.androidsx.lottodroid.model.Once;
import com.androidsx.lottodroid.model.OnceFinde;
import com.androidsx.lottodroid.model.Primitiva;
import com.androidsx.lottodroid.model.Quiniela;
import com.androidsx.lottodroid.model.Quinigol;
import com.androidsx.lottodroid.model.QuintuplePlus;

public final class LotteryCompoundDB<K extends Lottery> {
	
	public static final List<Lottery> retrieveLotteries(Context context) {
		try {
			List<Lottery> lotteries = new LinkedList<Lottery>();
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.BONOLOTO).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.ONCE).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.ONCE_FINDE).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.EUROMILLON).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.PRIMITIVA).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.LOTERIA_NACIONAL).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.QUINIELA).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.GORDO_PRIMITIVA).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.LOTERIA7_39).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.CUPONAZO_ONCE).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.LOTTO6_49).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.QUINIGOL).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.LOTOTURF).retrieveLottery().get(0));
			lotteries.add(LotteryDBFactory.newLotteryDB(context, LotteryId.QUINTUPLE_PLUS).retrieveLottery().get(0));
			return lotteries;
		} catch (Exception e) {
			return null;
		} 
	}
	
	public static void storeLotteries(Context context, List<Lottery> lotteries) {
		
		for(Lottery lottery: lotteries) {
			if(LotteryId.BONOLOTO == lottery.getId()) {
				new BonolotoDB(context).storeLottery((Bonoloto)lottery);
			} else if(LotteryId.CUPONAZO_ONCE == lottery.getId()) {
				new CuponazoOnceDB(context).storeLottery((CuponazoOnce)lottery);
			} else if(LotteryId.EUROMILLON == lottery.getId()) {
				new EuromillonDB(context).storeLottery((Euromillon)lottery);
			} else if(LotteryId.GORDO_PRIMITIVA== lottery.getId()) {
				new GordoPrimitivaDB(context).storeLottery((GordoPrimitiva)lottery);
			} else if(LotteryId.LOTERIA7_39 == lottery.getId()) {
				new Loteria7_39DB(context).storeLottery((Loteria7_39)lottery);
			} else if(LotteryId.LOTERIA_NACIONAL == lottery.getId()) {
				new LoteriaNacionalDB(context).storeLottery((LoteriaNacional)lottery);
			} else if(LotteryId.LOTOTURF == lottery.getId()) {
				new LototurfDB(context).storeLottery((Lototurf)lottery);
			} else if(LotteryId.LOTTO6_49 == lottery.getId()) {
				new Lotto6_49DB(context).storeLottery((Lotto6_49)lottery);
			} else if(LotteryId.ONCE == lottery.getId()) {
				new OnceDB(context).storeLottery((Once)lottery);
			} else if(LotteryId.ONCE_FINDE == lottery.getId()) {
				new OnceFindeDB(context).storeLottery((OnceFinde)lottery);
			} else if(LotteryId.PRIMITIVA== lottery.getId()) {
				new PrimitivaDB(context).storeLottery((Primitiva)lottery);
			} else if(LotteryId.QUINIELA == lottery.getId()) {
				new QuinielaDB(context).storeLottery((Quiniela)lottery);
			} else if(LotteryId.QUINIGOL == lottery.getId()) {
				new QuinigolDB(context).storeLottery((Quinigol)lottery);
			} else if(LotteryId.QUINTUPLE_PLUS == lottery.getId()) {
				new QuintuplePlusDB(context).storeLottery((QuintuplePlus)lottery);
			}
		}
	}

}
