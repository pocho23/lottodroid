package com.androidsx.lottodroid.storage;

import android.content.Context;

import com.androidsx.lottodroid.model.Lottery;
import com.androidsx.lottodroid.model.LotteryId;

public final class LotteryDBFactory {
	
	private static BonolotoDB bonolotoDB;
	private static CuponazoOnceDB cuponazoOnceDB;
	private static EuromillonDB euromillonDB;
	private static GordoPrimitivaDB gordoPrimitivaDB;
	private static Loteria7_39DB loteria7_39DB;
	private static LoteriaNacionalDB loteriaNacionalDB;
	private static LototurfDB lototurfDB;
	private static Lotto6_49DB lotto6_49DB;
	private static OnceDB onceDB;
	private static OnceFindeDB onceFindeDB;
	private static PrimitivaDB primitivaDB;
	private static QuinielaDB quinielaDB;
	private static QuinigolDB quinigolDB;
	private static QuintuplePlusDB quintuplePlusDB;
	
	private LotteryDBFactory() {
		
	}
	
	public static final LotteryDB<? extends Lottery> newLotteryDB(Context context, LotteryId lotteryId) {
		
		if(LotteryId.BONOLOTO == lotteryId) {
			if(bonolotoDB == null)
				bonolotoDB = new BonolotoDB(context);
			return bonolotoDB;
		} else if(LotteryId.CUPONAZO_ONCE == lotteryId) {
			if(cuponazoOnceDB == null)
				cuponazoOnceDB = new CuponazoOnceDB(context);
			return cuponazoOnceDB;
		} else if(LotteryId.EUROMILLON == lotteryId) {
			if(euromillonDB == null)
				euromillonDB = new EuromillonDB(context);
			return euromillonDB;
		} else if(LotteryId.GORDO_PRIMITIVA== lotteryId) {
			if(gordoPrimitivaDB == null)
				gordoPrimitivaDB = new GordoPrimitivaDB(context);
			return gordoPrimitivaDB;
		} else if(LotteryId.LOTERIA7_39 == lotteryId) {
			if(loteria7_39DB == null)
				loteria7_39DB = new Loteria7_39DB(context);
			return loteria7_39DB;
		} else if(LotteryId.LOTERIA_NACIONAL == lotteryId) {
			if(loteriaNacionalDB == null)
				loteriaNacionalDB = new LoteriaNacionalDB(context);
			return loteriaNacionalDB;
		} else if(LotteryId.LOTOTURF == lotteryId) {
			if(lototurfDB == null)
				lototurfDB = new LototurfDB(context);
			return lototurfDB;
		} else if(LotteryId.LOTTO6_49 == lotteryId) {
			if(lotto6_49DB == null)
				lotto6_49DB = new Lotto6_49DB(context);
			return lotto6_49DB;
		} else if(LotteryId.ONCE == lotteryId) {
			if(onceDB == null)
				onceDB = new OnceDB(context);
			return onceDB;
		} else if(LotteryId.ONCE_FINDE == lotteryId) {
			if(onceFindeDB == null)
				onceFindeDB = new OnceFindeDB(context);
			return onceFindeDB;
		} else if(LotteryId.PRIMITIVA== lotteryId) {
			if(primitivaDB == null)
				primitivaDB = new PrimitivaDB(context);
			return primitivaDB;
		} else if(LotteryId.QUINIELA == lotteryId) {
			if(quinielaDB == null)
				quinielaDB = new QuinielaDB(context);
			return quinielaDB;
		} else if(LotteryId.QUINIGOL == lotteryId) {
			if(quinigolDB == null)
				quinigolDB = new QuinigolDB(context);
			return quinigolDB;
		} else if(LotteryId.QUINTUPLE_PLUS == lotteryId) {
			if(quintuplePlusDB == null)
				quintuplePlusDB = new QuintuplePlusDB(context);
			return quintuplePlusDB;
		} 
		return null;
	}

}
