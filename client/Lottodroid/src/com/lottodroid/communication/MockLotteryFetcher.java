package com.lottodroid.communication;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Quiniela;

/**
 * Implementation for {@link LotteryFetcher} that uses in-memory, hard-coded data.
 */
public class MockLotteryFetcher implements LotteryFetcher {

  /**
   * It sets the amount of time <i>in seconds</i> that we will wait before returning the data, in
   * order to simulate some latency due to the network.
   */
  public static int MOCK_DELAY = 2;

  @Override
  public List<Lottery> retrieveLastAllLotteries() {
    List<Lottery> listLottery = new LinkedList<Lottery>();
    listLottery.add(new Bonoloto(new Date(), "5 6 7 1 0 9", "4", "9"));
    listLottery.add(new Bonoloto(new Date(), "5 55 7 1 0 9", "3", "4"));
    listLottery.add(new Bonoloto(new Date(), "5 6 7 1 66 9", "6", "9"));

    Quiniela quiniela = new Quiniela(new Date());
    quiniela.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela.setMatch(1, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(3, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(4, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(5, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(6, "R. Madrid", "Villareal", "1");
    quiniela.setMatch(7, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(8, "R. Madrid", "Villareal", "1");
    quiniela.setMatch(9, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(10, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(11, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(12, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(13, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(14, "R. Madrid", "Villareal", "2");

    listLottery.add(quiniela);

    listLottery.add(new Bonoloto(new Date(), "1 2 3 4 5 6", "1", "0"));

    simulateLatency();
    
    return listLottery;
  }

  @Override
  public List<Bonoloto> retrieveLastBonolotos(int start, int limit) {
    List<Bonoloto> listBonoloto = new LinkedList<Bonoloto>();
    listBonoloto.add(new Bonoloto(new Date(), "5 6 7 1 0 9", "4", "9"));
    listBonoloto.add(new Bonoloto(new Date(), "5 55 7 1 0 9", "3", "4"));
    listBonoloto.add(new Bonoloto(new Date(), "5 6 7 1 66 9", "6", "9"));

    simulateLatency();
    
    return listBonoloto;
  }

  @Override
  public List<Quiniela> retrieveLastQuinielas(int start, int limit) {
    List<Quiniela> listQuiniela = new LinkedList<Quiniela>();

    Quiniela quiniela = new Quiniela(new Date());
    quiniela.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela.setMatch(1, "Betis", "Villareal", "1");
    quiniela.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(3, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(4, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(5, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(6, "R. Madrid", "Villareal", "1");
    quiniela.setMatch(7, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(8, "R. Madrid", "Villareal", "1");
    quiniela.setMatch(9, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(10, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(11, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(12, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(13, "R. Madrid", "Villareal", "2");
    quiniela.setMatch(14, "R. Madrid", "Villareal", "2");

    listQuiniela.add(quiniela);

    Quiniela quiniela2 = new Quiniela(new Date());
    quiniela2.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela2.setMatch(1, "Betis", "Villareal", "1");
    quiniela2.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(3, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(4, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(5, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(6, "R. Madrid", "Villareal", "1");
    quiniela2.setMatch(7, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(8, "R. Madrid", "Villareal", "1");
    quiniela2.setMatch(9, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(10, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(11, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(12, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(13, "R. Madrid", "Villareal", "2");
    quiniela2.setMatch(14, "R. Madrid", "Villareal", "2");

    listQuiniela.add(quiniela2);

    Quiniela quiniela3 = new Quiniela(new Date());
    quiniela3.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela3.setMatch(1, "Betis", "Villareal", "1");
    quiniela3.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(0, "Barcelona", "Villareal", "X");
    quiniela3.setMatch(1, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(2, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(3, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(4, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(5, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(6, "R. Madrid", "Villareal", "1");
    quiniela3.setMatch(7, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(8, "R. Madrid", "Villareal", "1");
    quiniela3.setMatch(9, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(10, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(11, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(12, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(13, "R. Madrid", "Villareal", "2");
    quiniela3.setMatch(14, "R. Madrid", "Villareal", "2");

    listQuiniela.add(quiniela3);

    simulateLatency();

    return listQuiniela;
  }

  private void simulateLatency() {
    try {
      Thread.sleep(1000 * MOCK_DELAY);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
