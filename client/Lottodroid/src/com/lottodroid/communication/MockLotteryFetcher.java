package com.lottodroid.communication;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lottodroid.model.Bonoloto;
import com.lottodroid.model.Euromillon;
import com.lottodroid.model.LoteriaNacional;
import com.lottodroid.model.Lototurf;
import com.lottodroid.model.Lottery;
import com.lottodroid.model.Primitiva;
import com.lottodroid.model.Quiniela;
import com.lottodroid.model.Quinigol;

/**
 * Implementation for {@link LotteryFetcher} that uses in-memory, hard-coded data.
 */
class MockLotteryFetcher implements LotteryFetcher {

  /**
   * It sets the amount of time <i>in seconds</i> that we will wait before returning the data, in
   * order to simulate some latency due to the network.
   */
  public static int MOCK_DELAY = 2;

  @Override
  public List<Lottery> retrieveLastAllLotteries() {
    List<Lottery> listLottery = new LinkedList<Lottery>();
    listLottery.add(new Bonoloto(new Date(), 1, 2, 3, 4, 5, 6, 4, 3));
    listLottery.add(new Primitiva(new Date(), 1, 2, 3, 4, 5, 6, 4, 3));
    listLottery.add(new Lototurf(new Date(), 1, 2, 3, 4, 5, 6, 3, 4));
    listLottery.add(new LoteriaNacional(new Date(), 1, 2, 3, 4, 5, 6, 3));
    listLottery.add(new Euromillon(new Date(), 6, 5, 2, 3, 1, 2, 1));
    
    Quinigol quinigol2 = new Quinigol(new Date());
    quinigol2.setMatch(0, "Barcelona", "Villareal", "2", "M");
    quinigol2.setMatch(1, "Betis", "Villareal", "1", "0");
    quinigol2.setMatch(2, "R. Madrid", "Villareal", "2", "0");
    quinigol2.setMatch(3, "R. Madrid", "Villareal", "2", "0");
    quinigol2.setMatch(4, "R. Madrid", "Villareal", "2", "0");
    quinigol2.setMatch(5, "R. Madrid", "Villareal", "2", "0");
    
    listLottery.add(quinigol2);
    
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

    simulateLatency();
    
    return listLottery;
  }

  @Override
  public List<Bonoloto> retrieveLastBonolotos(int start, int limit) {
    List<Bonoloto> listBonoloto = new LinkedList<Bonoloto>();
    listBonoloto.add(new Bonoloto(new Date(), 6, 5, 2, 3, 1, 2, 1, 1));
    listBonoloto.add(new Bonoloto(new Date(), 6, 5, 2, 3, 1, 2, 1, 1));
    listBonoloto.add(new Bonoloto(new Date(), 1, 2, 3, 4, 5, 6, 3, 2));

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
  
  @Override
  public List<Primitiva> retrieveLastPrimitivas(int start, int limit) {
    List<Primitiva> listPrimitiva = new LinkedList<Primitiva>();
    listPrimitiva.add(new Primitiva(new Date(), 6, 5, 2, 3, 1, 2, 1, 1));
    listPrimitiva.add(new Primitiva(new Date(), 6, 5, 2, 3, 1, 2, 1, 1));
    listPrimitiva.add(new Primitiva(new Date(), 1, 2, 3, 4, 5, 6, 3, 2));

    simulateLatency();
    
    return listPrimitiva;
  }

  @Override
  public List<Euromillon> retrieveLastEuromillones(int start, int limit) {
      List<Euromillon> listPrimitiva = new LinkedList<Euromillon>();
      listPrimitiva.add(new Euromillon(new Date(), 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), 1, 2, 3, 4, 5, 6, 3));
      listPrimitiva.add(new Euromillon(new Date(), 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), 1, 2, 3, 4, 5, 6, 3));
      listPrimitiva.add(new Euromillon(new Date(), 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), 6, 5, 2, 3, 1, 2, 1));
      listPrimitiva.add(new Euromillon(new Date(), 1, 2, 3, 4, 5, 6, 3));

      simulateLatency();
      
      return listPrimitiva;
  }

  @Override
  public List<LoteriaNacional> retrieveLastLoteriasNacionales(int start, int limit) {
    List<LoteriaNacional> listPrimitiva = new LinkedList<LoteriaNacional>();
    listPrimitiva.add(new LoteriaNacional(new Date(), 1, 2, 3, 4, 5, 6, 3));
  
    simulateLatency();
    
    return listPrimitiva;
  }

  @Override
  public List<Lototurf> retrieveLastLototurfs(int start, int limit) {
    List<Lototurf> listPrimitiva = new LinkedList<Lototurf>();
    listPrimitiva.add(new Lototurf(new Date(), 6, 5, 2, 3, 1, 2, 1, 4));
    listPrimitiva.add(new Lototurf(new Date(), 1, 2, 3, 4, 5, 6, 3, 4));
  
    simulateLatency();
    
    return listPrimitiva;
  }

  @Override
  public List<Quinigol> retrieveLastQuinigoles(int start, int limit) {
    List<Quinigol> listQuiniela = new LinkedList<Quinigol>();

    Quinigol quiniela = new Quinigol(new Date());
    quiniela.setMatch(0, "Barcelona", "Villareal", "4", "4");
    quiniela.setMatch(1, "Betis", "Villareal", "1", "3");
    quiniela.setMatch(2, "R. Madrid", "Villareal", "2", "3");
    quiniela.setMatch(3, "R. Madrid", "Villareal", "2", "M");
    quiniela.setMatch(4, "R. Madrid", "Villareal", "2", "3");
    quiniela.setMatch(5, "R. Madrid", "Villareal", "2", "3");

    listQuiniela.add(quiniela);

    Quinigol quiniela2 = new Quinigol(new Date());
    quiniela2.setMatch(0, "Barcelona", "Villareal", "2", "M");
    quiniela2.setMatch(1, "Betis", "Villareal", "1", "0");
    quiniela2.setMatch(2, "R. Madrid", "Villareal", "2", "0");
    quiniela2.setMatch(3, "R. Madrid", "Villareal", "2", "0");
    quiniela2.setMatch(4, "R. Madrid", "Villareal", "2", "0");
    quiniela2.setMatch(5, "R. Madrid", "Villareal", "2", "0");

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
