package com.lottodroid.communication;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Quiniela;

/**
 * Implementation for {@link DataFetcher} that uses in-memory, hard-coded data. 
 */
public class MockDataFetcher implements DataFetcher {

  @Override
  public List<Lottery> retrieveLastAllLotteries() {
    List<Lottery> listLottery = new LinkedList<Lottery>();
    listLottery.add(new Bonoloto(new Date(), "5 6 7 1 0 9", "4", "9"));
    listLottery.add(new Quiniela());
    listLottery.add(new Bonoloto(new Date(), "5 55 7 1 0 9", "3", "4"));
    listLottery.add(new Bonoloto(new Date(), "5 6 7 1 66 9", "6", "9"));
    listLottery.add(new Quiniela());
    listLottery.add(new Bonoloto(new Date(), "1 2 3 4 5 6", "1", "0"));

    return listLottery;
  }

  @Override
  public List<Bonoloto> retrieveLastBonolotos(int start, int limit) {
    List<Bonoloto> listBonoloto = new LinkedList<Bonoloto>();
    listBonoloto.add(new Bonoloto(new Date(), "5 6 7 1 0 9", "4", "9"));
    listBonoloto.add(new Bonoloto(new Date(), "5 55 7 1 0 9", "3", "4"));
    listBonoloto.add(new Bonoloto(new Date(), "5 6 7 1 66 9", "6", "9"));

    return listBonoloto;
  }

  @Override
  public List<Quiniela> retrieveLastQuinielas(int start, int limit) {
    List<Quiniela> listQuiniela = new LinkedList<Quiniela>();
    listQuiniela.add(new Quiniela());
    listQuiniela.add(new Quiniela());
    listQuiniela.add(new Quiniela());

    return listQuiniela;
  }

}
